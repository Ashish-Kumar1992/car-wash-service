package com.cap.car.wash.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cap.car.wash.model.Role;
import com.cap.car.wash.model.User;
import com.cap.car.wash.repository.RoleRepository;
import com.cap.car.wash.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
	}

	@Override
	public boolean isUserAlreadyPresent(User user) {

		return false;
	}
	
	
	
	
	

}
