package com.cg.flight.service;

import com.cg.flight.Dto.BookingDto;
import com.cg.flight.entity.Passenger;
import com.cg.flight.exception.InvalidBookingException;


public interface FlightBookingService {
	public String addBooking(BookingDto bookingForm)throws InvalidBookingException;
	
}