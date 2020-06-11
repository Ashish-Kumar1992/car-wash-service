package com.cap.car.wash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import com.cap.car.wash.model.CarwashError;

@ControllerAdvice
public class ExceptionAdvice {
	
	/*@ExceptionHandler(CarWashServiceException.class)
	public ResponseEntity<CarwashError> mapException(CarWashServiceException ex){
		CarwashError error = new CarwashError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<CarwashError>(error, HttpStatus.INTERNAL_SERVER_ERROR );
	}*/
	
	

}
