package com.cg.flight.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.flight.entity.Passenger;
@Repository
public interface PassengerDao extends JpaRepository<Passenger, Long>{

	

}
