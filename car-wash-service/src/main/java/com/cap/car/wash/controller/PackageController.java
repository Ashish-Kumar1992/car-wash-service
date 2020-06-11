package com.cap.car.wash.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.car.wash.model.AddOnDescription;
import com.cap.car.wash.model.Packages;
import com.cap.car.wash.repository.AddOnDescRepository;
import com.cap.car.wash.repository.PackageRepository;

@RestController
@RequestMapping("/api/auth")
public class PackageController {
	
	/*@Autowired
	private PackageService packageService;*/
	
	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	AddOnDescRepository repo;
	
	@GetMapping("/getPackageDetails")
	public List<Packages> getPackageDetails() {
		return packageRepository.findAll();
	}
	
	
	@GetMapping("/getPackageDetails/{id}")
	  public ResponseEntity<Packages> getPackageDetailsById(@PathVariable("id") Integer id) {
		 
		  Optional<Packages> response = packageRepository.findById(id);
		 
	    if (response.isPresent()){
	      return new ResponseEntity<>(response.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@GetMapping("/getAddOnDesc")
	public List<AddOnDescription> getAddOnDesc() {
		return repo.findAll();
	}
	/*@GetMapping("/getAddOnDesc/{packageid}")
	  public List<AddOnDescription> getAddOnDescId(@PathVariable("packageid") Integer id) {
		//ResponseEntity entity="";
		  Optional<AddOnDescription> response = packageRepository.findByPackageId(id);
		 
	    if (response.isPresent()){
	      return 
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }*/

}
