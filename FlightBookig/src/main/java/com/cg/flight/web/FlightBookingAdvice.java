package com.cg.flight.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.flight.Dto.FlightMessage;
import com.cg.flight.exception.InvalidBookingException;

@RestControllerAdvice
public class FlightBookingAdvice {

@ExceptionHandler(InvalidBookingException.class)
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public FlightMessage handleExecption(InvalidBookingException ex) {
	return new FlightMessage(ex.getMessage());
}

}
