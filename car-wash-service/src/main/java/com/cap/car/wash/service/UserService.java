package com.cap.car.wash.service;

import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.model.User;

public interface UserService {
	
	//public ResponseEntity loginUser(Login login);

	public void saveUser(User user);

	public boolean isUserAlreadyPresent(User user);
	
	public String saveCarDetail(CarDetails cardetails);

}
