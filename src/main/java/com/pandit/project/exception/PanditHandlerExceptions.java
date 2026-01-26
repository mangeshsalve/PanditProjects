package com.pandit.project.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PanditHandlerExceptions {
	
	@ExceptionHandler(value = UserExceptions.class)
	public UserExceptions getUserExceptions(String message) {
		return new UserExceptions(message);
	}

}
