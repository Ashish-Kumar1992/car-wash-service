package com.cap.car.wash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cap.car.wash.model.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long>{

}
