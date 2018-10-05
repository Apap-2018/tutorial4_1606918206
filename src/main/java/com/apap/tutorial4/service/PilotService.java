package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
	void deletePilot(PilotModel pilot);
	void addPilot(PilotModel pilot);
	void updatePilot(PilotModel pilot, String nama, Integer flyour);
}	
