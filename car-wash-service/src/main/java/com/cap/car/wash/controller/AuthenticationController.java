package com.cap.car.wash.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cap.car.wash.model.ForgotPassword;
import com.cap.car.wash.model.Login;
import com.cap.car.wash.model.User;
import com.cap.car.wash.repository.UserRepository;
import com.cap.car.wash.service.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository; 
	
	private Facebook facebook;

	private ConnectionRepository connectionRepository;
	
	public AuthenticationController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}
	
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
	
	@RequestMapping(value="/forgotpassword", method=RequestMethod.PUT)
	public ResponseEntity forgotPassword (@RequestBody ForgotPassword forgotpassword) {
		System.out.println("forgotpassword Controller------- " +forgotpassword.toString());
		
		String email = forgotpassword.getEmail();
		String name = forgotpassword.getName();
		String pass = encoder.encode(forgotpassword.getPassword());
		
	
		
		User forgotPassword = userRepository.findByEmailAndPassword(forgotpassword.getEmail());
		//if(forgotPassword!=" " &&for)
		String email1 = forgotPassword.getEmail();
		
		System.out.println("forgot ---  : " +email1.toString());
		
		if (email.equals(email1)) {
			userRepository.updateName(pass, email);
			System.out.println("Updated successful");
			return new ResponseEntity<User>(HttpStatus.CREATED);
		} else {
			System.out.println("update  unsuccessful");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	@RequestMapping(value="/facebookController", method=RequestMethod.POST)
	public String getfacebookFeeds(Model model) {
		
		System.out.println("=====Controller ======");
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}
		PagedList<Post> posts = facebook.feedOperations().getPosts();
		model.addAttribute("profileName", posts.get(0).getFrom().getName());
		model.addAttribute("posts", posts);
		return "profile";
	}
	
	
	
	
	
	
	
	
	
	}
	
