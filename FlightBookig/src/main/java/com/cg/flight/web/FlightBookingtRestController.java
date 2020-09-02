package com.cg.flight.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.Dto.BookingDto;
import com.cg.flight.Dto.FlightMessage;
import com.cg.flight.exception.InvalidBookingException;
import com.cg.flight.service.FlightBookingService;
import com.cg.flight.util.BookingConstants;

@RestController
public class FlightBookingtRestController {

	@Autowired
	private FlightBookingService service;
	
	@CrossOrigin
	@PostMapping("/addbooking")
	public FlightMessage addBooking(@RequestBody BookingDto bookingForm) throws InvalidBookingException {
		//After implementing the login, the uncomment it
		//if(!(boolean)req.getAttribute("authFlag"))throw new UserException(FlightConstants.NOT_AUTHENTICATED);
		String bookingID =service.addBooking(bookingForm);
		FlightMessage msg = new FlightMessage();
		msg.setMessage(bookingID);
		return msg;
	}
	
	
}
