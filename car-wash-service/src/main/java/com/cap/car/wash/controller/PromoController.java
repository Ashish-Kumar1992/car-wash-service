package com.cap.car.wash.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.car.wash.model.MessageResponse;
import com.cap.car.wash.model.PromoManagement;
import com.cap.car.wash.model.PromoRequest;
import com.cap.car.wash.service.PromoService;

@RestController
@RequestMapping("/api/auth")
public class PromoController {
	
	@Autowired
	private PromoService promoService;

	@PostMapping("/savePromoCodeDetails")
	public PromoManagement savePromoCodeDetails(@RequestBody PromoManagement promo) {
		return promoService.savePromoCodeDetails(promo);
	}
	
	@GetMapping("/getPromocodesByName/{promoname}")
	public Optional<PromoManagement> getPromo(@PathVariable("promoname") String promoname){
		return promoService.getPromoDetail(promoname);
	}
	
	
	@PutMapping("/updatePromoCodeDetails/{id}")
	public ResponseEntity<MessageResponse> updatePromoCodeDetails(@PathVariable("id") Integer id,@RequestBody PromoRequest request) {
		
		
		MessageResponse message =promoService.updatePromoCodeDetails(id,request);
		
		/*String response=id + "" +"successfully updated";
		 return new ResponseEntity<>(new MessageResponse(response),HttpStatus.OK);*/
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	

}
