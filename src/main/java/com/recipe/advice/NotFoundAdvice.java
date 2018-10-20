package com.recipe.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.recipe.exceptions.NotFoundException;

@ControllerAdvice
public class NotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorMsg notFoundHandler(NotFoundException nfe) {
		return new ErrorMsg(HttpStatus.NOT_FOUND.toString(), nfe.getMessage());
	}
}
