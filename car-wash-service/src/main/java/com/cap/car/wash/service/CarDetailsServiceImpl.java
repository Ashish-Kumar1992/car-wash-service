package com.cap.car.wash.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.repository.CarRepository;

@Service
public class CarDetailsServiceImpl implements CarDetailsService{
	
	@Autowired
	CarRepository carRepository;
	
	
	@Override
	public CarDetails createCar(CarDetails cardetails) {
		return carRepository.save(cardetails);
	}


	@Override
	public Optional<CarDetails> getCarDetail(Long customercarid) {
		return carRepository.findById(customercarid);
	}

}
