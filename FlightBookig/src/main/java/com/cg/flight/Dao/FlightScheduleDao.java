package com.cg.flight.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.flight.entity.ScheduledFlight;
@Repository
public interface FlightScheduleDao extends JpaRepository<ScheduledFlight,String> {
@Query("from ScheduledFlight sf where sf.scheduledFlightId=:sid")
	public ScheduledFlight viewFlightSchedule(@Param("sid") String scheduleFlightId);
}
