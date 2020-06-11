package com.cap.car.wash.service;

import java.util.Optional;

import com.cap.car.wash.model.CarDetails;

public interface CarDetailsService {
	
	CarDetails createCar(CarDetails cardetails);
	
	Optional<CarDetails> getCarDetail(Long customercarid);

}
