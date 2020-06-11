package com.cap.car.wash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.car.wash.model.AddOn;
import com.cap.car.wash.model.ScheduleWash;
import com.cap.car.wash.repository.AddOnRepository;

@Service
public class AddOnServiceImpl implements AddOnService{

	@Autowired
	private AddOnRepository addOnRepository; 
	@Override
	public AddOn saveaddons(AddOn request) {
		// verifyUserAccess(userId);
		ScheduleWash user=new ScheduleWash();
				String status="PENDING";
				//request.setWorkStatus(status);
				
				return addOnRepository.save(request);
	}
}
