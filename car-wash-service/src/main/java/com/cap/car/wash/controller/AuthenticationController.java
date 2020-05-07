package com.cap.car.wash.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cap.car.wash.model.Login;
import com.cap.car.wash.model.User;
import com.cap.car.wash.repository.UserRepository;
import com.cap.car.wash.service.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserRepository userRepository; 
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity loginUser(@RequestBody Login login, BindingResult bindingResult, ModelMap modelMap) {
		System.out.println("----- user login Controller -----: " + login.toString());
	
		ModelAndView modelAndView = new ModelAndView();
		// Checking for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		Optional<User> user = userRepository.findByEmail(login.getEmail());
		if (user.isPresent()) {
			System.out.println("login successful");
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} else {
			System.out.println("login successful");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity registerUser (@RequestBody User user, BindingResult bindingResult, ModelMap modelMap) {
		System.out.println("user Controller :::: " +user.toString());
		ModelAndView modelAndView = new ModelAndView();
		// Checking for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userService.isUserAlreadyPresent(user)){
			System.out.println("user Controller 2 :::: " +user.toString());
			modelAndView.addObject("successMessage", "user already exists!");			
		}
		// we will save the user if, no binding errors
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is registered successfully!");
		}
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	}
	
