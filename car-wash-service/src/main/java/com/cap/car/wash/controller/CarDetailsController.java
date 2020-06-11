package com.cap.car.wash.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cap.car.wash.model.AppResponse;
import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.payload.CarDetailsRequest;
import com.cap.car.wash.repository.UserRepository;
import com.cap.car.wash.service.CarDetailsService;
import com.cap.car.wash.service.FileStorageService;
import com.cap.car.wash.util.AppConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/auth")
public class CarDetailsController {

	public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	CarDetailsService carDetailsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AuthenticationController authenticationController;

	ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 
	 * @param empJson
	 * @param file
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */

	@RequestMapping(value = AppConstants.EMPLOYEE_URI, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AppResponse createEmployee(
			@RequestParam(value = AppConstants.EMPLOYEE_JSON_PARAM, required = true) String empJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) MultipartFile file)
			throws JsonParseException, JsonMappingException, IOException {

		LOGGER.info("----- create image Controller ---------" + empJson.toString());
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
				.path(fileName).toUriString();
		CarDetails cardetail = objectMapper.readValue(empJson, CarDetails.class);
		cardetail.setProfilePicPath(fileDownloadUri);
		LocalDate ldObj = LocalDate.now();
		cardetail.setCreatedDate(ldObj);
		cardetail.setUpdatedDate(ldObj);
		carDetailsService.createCar(cardetail);
		return new AppResponse(AppConstants.SUCCESS_CODE, AppConstants.SUCCESS_MSG);
	}

	/**
	 * 
	 * @param customercarid
	 * @return
	 */

	@GetMapping("/getCarDetails/{customercarid}")
	public Optional<CarDetails> getCarDetails(@PathVariable("customercarid") Long customercarid) {
		return carDetailsService.getCarDetail(customercarid);
	}
	
	@PutMapping("/updateCarDetails/{customercarid}")
	  public ResponseEntity<CarDetails> updateCarDetails(@PathVariable("customercarid") String customercarid, @RequestBody CarDetailsRequest carDetailsRequest) {
	    Optional<CarDetails> response = userRepository.findById(customercarid);
	    
	    System.out.println("response : : " +response);

	    if (response.isPresent()) {
	    	CarDetails cardetails = response.get();
	    	cardetails.setCustomercarid(carDetailsRequest.getCustomercarid());
	    	cardetails.setCarcolor(carDetailsRequest.getCarcolor());
	    	userRepository.save(cardetails);
	    	
	      return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
