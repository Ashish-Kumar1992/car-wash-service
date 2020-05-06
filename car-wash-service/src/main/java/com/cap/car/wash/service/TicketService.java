package com.cap.car.wash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.car.wash.model.Ticket;
import com.cap.car.wash.repository.RoleDao;
import com.cap.car.wash.repository.TicketDao;

@Service
public class TicketService {
	
	@Autowired
	private TicketDao dao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Ticket save(Ticket tickets) {
		tickets.setPassword(bCryptPasswordEncoder.encode(tickets.getPassword()));
		System.out.println("Service -----------: :"+bCryptPasswordEncoder.encode(tickets.getPassword()));
		return dao.save(tickets);
	}

}
