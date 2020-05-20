package com.cap.car.wash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.car.wash.model.CarDetails;

public interface CarRepository extends JpaRepository<CarDetails, Integer> {
	

}
