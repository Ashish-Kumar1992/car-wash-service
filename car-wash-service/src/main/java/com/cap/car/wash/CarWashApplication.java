package com.cap.car.wash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
public class CarWashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}

}
