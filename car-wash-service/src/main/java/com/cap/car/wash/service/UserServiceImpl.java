package com.cap.car.wash.service;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cap.car.wash.model.CarDetails;
import com.cap.car.wash.model.Role;
import com.cap.car.wash.model.User;
import com.cap.car.wash.repository.RoleRepository;
import com.cap.car.wash.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	public static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
	
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

	@Override
	public String saveCarDetail(CarDetails cardetails) {
		LOGGER.info("----- CarDetails Service Impl ---------" +cardetails);
		return null;
	}

	/*@Override
	public ResponseEntity loginUser(Login login) {
		LOGGER.info("----- registerUser Service Impl ---------");
		LOGGER.info("registerUser Service Impl --" +login.toString());
		
		Optional<User> user = userRepository.findByEmail(login.getEmail());
		if (user.isPresent()) {
			LOGGER.info("---Login Successful---");
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} else {
			LOGGER.info("---Login Unsuccessful ---");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	
	
	
	

}
