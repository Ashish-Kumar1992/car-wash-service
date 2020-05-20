package com.cap.car.wash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cap.car.wash.configuration.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class CarWashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}

}
