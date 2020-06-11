package com.cap.car.wash.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.car.wash.model.MessageResponse;
import com.cap.car.wash.model.PromoManagement;
import com.cap.car.wash.model.PromoRequest;
import com.cap.car.wash.repository.PromoRepository;

@Service
public class PromoServiceImpl implements PromoService {

	@Autowired
	private PromoRepository promoRepository;
	
	
	@Override
	public PromoManagement savePromoCodeDetails(PromoManagement promo) {
		LocalDate ldObj = LocalDate.now();
		promo.setCreatedDate(ldObj);
		promo.setUpdatedDate(ldObj);
		return promoRepository.save(promo);
	}


	@Override
	public Optional<PromoManagement> getPromoDetail(String promoname) {
		return promoRepository.findByPromoName(promoname);
	}


	@Override
	public MessageResponse updatePromoCodeDetails(Integer id,PromoRequest request) {
		MessageResponse message=new MessageResponse(" Successfully updated");
		Optional<PromoManagement> response = promoRepository.findById(id);
	    if (response.isPresent()) {
	    	PromoManagement promoManagement = response.get();
	    	promoManagement.setPromoName(request.getPromoName());
	    	promoManagement.setMinimumAmount(request.getMinimumAmount());
	    	promoRepository.save(promoManagement);	
	    }
	    else new MessageResponse("id not found");
		return message;
	}

}
