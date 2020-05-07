package com.cap.car.wash.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class FacebookController {
	
	private Facebook facebook;

	private ConnectionRepository connectionRepository;



	public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@GetMapping
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
