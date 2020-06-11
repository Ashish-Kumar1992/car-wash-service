package com.cap.car.wash.repository;

import org.springframework.data.repository.CrudRepository;

import com.cap.car.wash.model.ConfirmationToken;



public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
