package com.cap.car.wash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cap.car.wash.controller.TicketController;

@SpringBootApplication
public class CarWash1Application {

	public static void main(String[] args) {
		SpringApplication.run(CarWash1Application.class, args);
	}

}
