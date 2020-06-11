package com.cap.car.wash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cap.car.wash.model.AppResponse;
import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.model.ConfirmationToken;
import com.cap.car.wash.model.ERole;
import com.cap.car.wash.model.ForgotPassword;
import com.cap.car.wash.model.JwtResponse;
import com.cap.car.wash.model.LoginRequest;
import com.cap.car.wash.model.MessageResponse;
import com.cap.car.wash.model.Role;
import com.cap.car.wash.model.SignupRequest;
import com.cap.car.wash.model.User;
import com.cap.car.wash.repository.ConfirmationTokenRepository;
import com.cap.car.wash.repository.RoleRepository;
import com.cap.car.wash.repository.UserRepository;
import com.cap.car.wash.service.CarDetailsService;
import com.cap.car.wash.service.EmailSenderService;
import com.cap.car.wash.service.FileStorageService;
import com.cap.car.wash.service.UserDetailsImpl;
import com.cap.car.wash.util.AppConstants;
import com.cap.car.wash.util.JwtUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	public static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	/*@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	CarDetailsService carDetailsService;*/
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	
	/**
	 * 
	 * @param loginRequest
	 * @return
	 */

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("loginRequest :" +loginRequest.toString());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	/**
	 * 
	 * @param signUpRequest
	 * @return
	 */

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	/**
	 * 
	 * @param forgotpassword
	 * @return
	 */
	
	@PutMapping(value="/forgotpassword")
	public ResponseEntity<?> forgotPassword (@RequestBody ForgotPassword forgotpassword) {
		LOGGER.info("----- forgotPassword Controller ---------");
			
		String email = forgotpassword.getEmail();
		String pass = encoder.encode(forgotpassword.getPassword());
		
		Boolean forgotPassword = userRepository.existsByEmail(forgotpassword.getEmail());
				
		if (forgotPassword) {
			userRepository.updateName(pass, email);
			return ResponseEntity.ok(new MessageResponse("Password Updated successfully!"));
		} else {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Password not updated Successfully !"));
		}
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	
	
	
	/**
	 * 
	 * @param empJson
	 * @param file
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	/*@RequestMapping(value = AppConstants.EMPLOYEE_URI, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AppResponse createEmployee(@RequestParam(value = AppConstants.EMPLOYEE_JSON_PARAM, required = true) String empJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) MultipartFile file) throws JsonParseException, JsonMappingException, IOException {
		
		LOGGER.info("----- create image Controller ---------" +empJson.toString());
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
		CarDetails cardetail = objectMapper.readValue(empJson, CarDetails.class);
		cardetail.setProfilePicPath(fileDownloadUri);
		carDetailsService.createCar(cardetail);
		return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
	}*/
	
	/**
	 * 
	 * @param fileName
	 * @param request
	 * @return
	 */
	
	/*@RequestMapping(value = AppConstants.DOWNLOAD_URI, method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (contentType == null) {
			contentType = AppConstants.DEFAULT_CONTENT_TYPE;
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
				.body(resource);
	}*/
	
	/**
	 * Display the forgot password page and form
	 */
	/*@RequestMapping(value="/forgot-password", method=RequestMethod.GET)
	public ModelAndView displayResetPassword(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("forgotPassword");
		return modelAndView;
	}*/

	/**
	 * Receive email of the user, create token and send it via email to the user
	 */
	@RequestMapping(value="/forgot-password", method=RequestMethod.POST)
	public ResponseEntity<?> forgotUserPassword(@RequestBody User user) {
		//User existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmailId());
		User existingUser =userRepository.findByEmailIgnoreCase(user.getEmail());
		if(existingUser != null) {
			// create token
			ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
			
			// save it
			confirmationTokenRepository.save(confirmationToken);
			
			// create the email
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(existingUser.getEmail());
			mailMessage.setSubject("Complete Password Reset!");
			mailMessage.setFrom("nairobley@gmail.com");
			mailMessage.setText("To complete the password reset process, please click here: "
			//+"http://localhost:8082/confirm-reset?token="+confirmationToken.getConfirmationToken());
			+"http://localhost:8585/confirm-reset?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);

			//modelAndView.addObject("message", "Request to reset password received. Check your inbox for the reset link.");
			//modelAndView.setViewName("successForgotPassword");
			
			return ResponseEntity.ok(new MessageResponse("Request to reset password received. Check your inbox for the reset link !"));
		
		} else {	
			//modelAndView.addObject("message", "This email does not exist!");
			//modelAndView.setViewName("error");
			return ResponseEntity.badRequest().body(new MessageResponse(" This email does not exist ! "));
		}
		
		//return modelAndView;
	}


	/*@RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> validateResetToken(@RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null) {
			User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setEnabled(true);
			userRepository.save(user);
			modelAndView.addObject("user", user);
			modelAndView.addObject("emailId", user.getEmailId());
			modelAndView.setViewName("resetPassword");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}	
*/
	/**
	 * Receive the token from the link sent via email and display form to reset password
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ResponseEntity<?> resetUserPassword(@RequestBody User user) {
		// ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(user.getEmail() != null) {
			// use email to find user
			User tokenUser = userRepository.findByEmailIgnoreCase(user.getEmail());
			tokenUser.setEnabled(true);
			tokenUser.setPassword(encoder.encode(user.getPassword()));
			// System.out.println(tokenUser.getPassword());
			userRepository.save(tokenUser);
			//modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
			return ResponseEntity.ok(new MessageResponse("Password successfully reset. You can now log in with the new credentials."));
			
			//modelAndView.setViewName("successResetPassword");
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" The link is invalid or broken! "));
			//modelAndView.addObject("message","The link is invalid or broken!");
			//modelAndView.setViewName("error");
		}
		
		//return modelAndView;
	}
	
	
}	