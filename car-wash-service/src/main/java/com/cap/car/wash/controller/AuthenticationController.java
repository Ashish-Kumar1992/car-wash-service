package com.cap.car.wash.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cap.car.wash.appconstants.AppConstants;
import com.cap.car.wash.model.AppResponse;
import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.model.ForgotPassword;
import com.cap.car.wash.model.Login;
import com.cap.car.wash.model.User;
import com.cap.car.wash.repository.UserRepository;
import com.cap.car.wash.service.CarDetailsService;
import com.cap.car.wash.service.FileStorageService;
import com.cap.car.wash.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	public static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	CarDetailsService carDetailsService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	private Facebook facebook;

	private ConnectionRepository connectionRepository;
	
	public AuthenticationController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}
	
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity loginUser(@RequestBody Login login) {
		LOGGER.info("----- loginUser Controller ---------");
		return userService.loginUser(login);

		
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity loginUser(@RequestBody Login login) {
		LOGGER.info("----- loginUser Controller ---------");
		LOGGER.info("loginUser Controller --" +login.toString());
		Optional<User> user = userRepository.findByEmail(login.getEmail());
		if (user.isPresent()) {
			LOGGER.info("---Login Successful---");
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} else {
			LOGGER.info("---Login Unsuccessful ---");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	

	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity registerUser (@RequestBody User user) {
		LOGGER.info("----- registerUser Controller ---------");
		LOGGER.info("registerUser Controller --" +user.toString());
		if(userService.isUserAlreadyPresent(user)){
			LOGGER.info("---registerUser---" +user.toString());			
		}
		else {
			userService.saveUser(user);
		}
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
	
	/*@RequestMapping(value="/forgotpassword", method=RequestMethod.PUT)
	public ResponseEntity forgotPassword (@RequestBody ForgotPassword forgotpassword) {
		LOGGER.info("----- forgotPassword Controller ---------");
		LOGGER.info("forgotPassword Controller --" +forgotpassword.toString());
			
		String email = forgotpassword.getEmail();
		String pass = encoder.encode(forgotpassword.getPassword());
		
		User forgotPassword = userRepository.findByEmailAndPassword(forgotpassword.getEmail());
		String email1 = forgotPassword.getEmail();		
		if (email.equals(email1)) {
			userRepository.updateName(pass, email);
			System.out.println("Updated successful");
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} else {
			System.out.println("update  unsuccessful");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}	
	*/
	
	@RequestMapping(value="/facebookController", method=RequestMethod.POST)
	public String getfacebookFeeds(Model model) {
		LOGGER.info("----- Facebook Controller ---------");
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}
		PagedList<Post> posts = facebook.feedOperations().getPosts();
		model.addAttribute("profileName", posts.get(0).getFrom().getName());
		model.addAttribute("posts", posts);
		return "profile";
	}
	
	
	@RequestMapping(value="/cardetails", method=RequestMethod.POST)
	public String carDetails (@RequestBody CarDetails cardetails) {
		LOGGER.info("----- CarDetails Controller ---------" +cardetails.toString());
			return userService.saveCarDetail(cardetails);
		}
	
	
	@RequestMapping(value = AppConstants.DOWNLOAD_URI, method = RequestMethod.GET)
	//@RequestMapping("")
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
	}
	
	
	@RequestMapping(value = AppConstants.EMPLOYEE_URI, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AppResponse createEmployee(
			@RequestParam(value = AppConstants.EMPLOYEE_JSON_PARAM, required = true) String empJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) MultipartFile file)
			throws JsonParseException, JsonMappingException, IOException {
		
		LOGGER.info("----- create image Controller ---------");
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
				.path(fileName).toUriString();

		CarDetails cardetail = objectMapper.readValue(empJson, CarDetails.class);
		cardetail.setProfilePicPath(fileDownloadUri);
		carDetailsService.createCar(cardetail);

		return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
	}
	
	
	
	
}	