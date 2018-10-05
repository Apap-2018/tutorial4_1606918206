package com.apap.tutorial4.service;

import java.sql.Date;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	public FlightModel getFlightDetailByFlightNumber(String flightNumber);
	public void updateFlight(FlightModel flight, String Origin, String Destination, Date date);
	void deleteFlight(FlightModel flight);
	
}
