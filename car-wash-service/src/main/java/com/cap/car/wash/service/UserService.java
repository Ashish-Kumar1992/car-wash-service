package com.cap.car.wash.service;

import com.cap.car.wash.model.User;

public interface UserService {

	public void saveUser(User user);

	public boolean isUserAlreadyPresent(User user);

}
