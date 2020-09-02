package com.cg.flight.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.flight.Dao.BookingDao;
import com.cg.flight.Dao.FlightScheduleDao;
import com.cg.flight.Dao.PassengerDao;
import com.cg.flight.Dao.UserDao;
import com.cg.flight.Dto.BookingDto;
import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Passenger;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.entity.User;
import com.cg.flight.exception.InvalidBookingException;
import com.cg.flight.util.BookingConstants;

@Service
@Transactional
public class FlightBookingServiceImpl implements FlightBookingService {

	@Autowired
	private BookingDao dao;
	
@Autowired
	private FlightScheduleDao schdao;

@Autowired
private UserDao userdao;

@Autowired
private PassengerDao passengerdao;

	@Transactional
	@Override
	public String addBooking(BookingDto bookingForm) throws InvalidBookingException {
       ScheduledFlight schflight =  schdao.viewFlightSchedule(bookingForm.getScheduleFlightId());
       User user = userdao.viewUser(bookingForm.getContactNo());
       Booking booking = null;
       String bookingId="";
       if(schflight.getAvailableSeats() >= bookingForm.getTkts()) {
    	   booking = new Booking();
    	   booking.setBookingDate(LocalDate.now());
    	   bookingId = bookingForm.getScheduleFlightId()+generateBookingId(bookingForm.getScheduleFlightId());
    	   booking.setBookingId(bookingId);
    	   booking.setFare(schflight.getDynamicPrice()* bookingForm.getTkts());
    	   booking.setNoOfSeats(bookingForm.getTkts());
    	   booking.setSchedule(schflight);
    	   booking.setUser(user);
    	   dao.save(booking);
    	   schflight.setAvailableSeats(schflight.getAvailableSeats() - bookingForm.getTkts());
    	  schdao.save(schflight);
    	  schdao.flush();
    	  addPassenger(bookingForm.getPassengers(), booking);
    	   return bookingId;
       }
		throw new InvalidBookingException(BookingConstants.BOOKING_NOT_AVAILABLE);
		
	}

	
	private int generateBookingId(String scheduleFlightId) {
	int count=dao.countBookingbyschedule(scheduleFlightId)+1;
		return count;
	}


	public boolean addPassenger(List<Passenger> passengers, Booking booking) {
      passengers.forEach(passenger->{passenger.setBooking(booking);passengerdao.save(passenger);});
		
		return true;
	}
	
}