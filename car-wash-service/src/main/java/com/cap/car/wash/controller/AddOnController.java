package com.cap.car.wash.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.car.wash.model.AddOn;
import com.cap.car.wash.model.AddOnRequest;
import com.cap.car.wash.model.MessageResponse;
import com.cap.car.wash.repository.AddOnRepository;
import com.cap.car.wash.service.AddOnService;


@RestController
@RequestMapping("/api/auth")
public class AddOnController {
	
	@Autowired
	private AddOnService addOnService;
	
	@Autowired
	private AddOnRepository addOnRepository;

	@PostMapping("/saveaddons")
	public ResponseEntity<MessageResponse> saveaddons(@RequestBody AddOn request) {
		try {

			AddOn response = addOnService.saveaddons(request);
			Integer id = response.getId();
			String message = id + "" + " Successfully created ";
			return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/getaddon/{id}")
	public ResponseEntity<AddOn> getaddon(@PathVariable("id") Integer id) {

		Optional<AddOn> response = addOnRepository.findById(id);
		//ScheduleWash userprofile = response.get();

		if (response.isPresent()) {
			return new ResponseEntity<>(response.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteaddon/{id}")
	public ResponseEntity<MessageResponse> deleteaddon(@PathVariable("id") Integer id) {
		try {
			// UserProfile response =profileService.deleteProfile(id);
			Optional<AddOn> response = addOnRepository.findById(id);

			addOnRepository.deleteById(id);

			// String message= id + "successfully deactivated";

			String message = "successfully deleted";
			return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/updateaddon/{id}")
  public ResponseEntity<AddOn> updateaddon(@PathVariable("id") Integer id, @RequestBody AddOnRequest request) {
    Optional<AddOn> response = addOnRepository.findById(id);

    if (response.isPresent()) {
    	AddOn addon = response.get();
    	addon.setDescriptionid(request.getDescriptionid());
    	addon.setScheduleid(request.getScheduleid());
    	
    	
      return new ResponseEntity<>(addOnRepository.save(addon), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

	
	

}
