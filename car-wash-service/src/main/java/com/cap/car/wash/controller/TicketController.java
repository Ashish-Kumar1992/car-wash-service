package com.cap.car.wash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cap.car.wash.model.Ticket;
import com.cap.car.wash.service.TicketService;

@Controller
@RequestMapping("/register")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	
	/*@RequestMapping(value= { "/singup"}, method=RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("user/signup");	
		return model;
	}*/
	
	/*@RequestMapping("/")
	public String getMapp() {
		return "redirect:/registrationPage.jsp";
	}*/
	/*@RequestMapping(value= { "/registration"}, method=RequestMethod.GET)
	public ModelAndView newRegistration() {
		Ticket ticket =new Ticket();
		return new ModelAndView("registrationPage","ticket",ticket);
	}*/
	
	 @RequestMapping(value="/registration", method = RequestMethod.GET)
	    public String showLoginPage(ModelMap model){
	        return "registrationPage";
	    }
	/*@RequestMapping("/")
	public String viewHomePage(Model model) {    
	    return "registrationPage";
	}*/
	
	
	
	
	@PostMapping("/bookTickets")
	public Ticket bookTicket(@RequestBody Ticket tickets) {
		System.out.println("Controller ---------------" +tickets.getPassword());
		//dao.save(tickets);
		return ticketService.save(tickets);
	}
	
	

}
