package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.*;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value= "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
		
	}
	
	@RequestMapping(value="/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
	private String view(@RequestParam("flightNumber") String flightNumber, Model model) {
		FlightModel archive = flightService.getFlightDetailByFlightNumber(flightNumber);
		PilotModel archiveP = archive.getPilot();
		model.addAttribute("flight", archive);
		model.addAttribute("pilot", archiveP);
		
		
		return "view-flight";
	}
	
	@RequestMapping(value="/flight/update/{flightNumber}", method = RequestMethod.POST)
	private String update(@PathVariable(value="flightNumber") String flightNumber, @RequestParam(value="origin") String origin, @RequestParam(value="destination") String destination, @RequestParam(value="time") Date time) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		flightService.updateFlight(flight, origin, destination, time);
		
		return "add";	
	}
	
	@RequestMapping(value="/flight/update/{flightNumber}", method = RequestMethod.GET)
	private String updatePilotSubmit(@PathVariable(value= "flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		
		return "update-flight";
	}

	@RequestMapping(value = "/flight/delete/{flightNumber}", method = RequestMethod.GET)
	private String delete(@PathVariable(value= "flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		flightService.deleteFlight(flight);
		
		return "delete-pilot";
	}

	/*@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String delete(@PathVariable(value= "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.deletePilot(pilot);
		
		return "delete-pilot";
	}
	*/
	

}
