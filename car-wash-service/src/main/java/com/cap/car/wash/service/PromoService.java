package com.cap.car.wash.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.model.MessageResponse;
import com.cap.car.wash.model.PromoManagement;
import com.cap.car.wash.model.PromoRequest;

public interface PromoService {

	PromoManagement savePromoCodeDetails(PromoManagement promo);

	Optional<PromoManagement> getPromoDetail(String promoname);

	MessageResponse updatePromoCodeDetails(Integer id, PromoRequest request);
	
	
	

}
