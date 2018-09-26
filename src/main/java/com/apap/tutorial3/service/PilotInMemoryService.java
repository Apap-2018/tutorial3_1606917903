package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.Service;

/**
 * PilotInMemoryService
 * @author nasya
 *
 */
@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		PilotModel tempPilot = null;
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getLicenseNumber().equals(licenseNumber)) {
				tempPilot = archivePilot.get(i);
			}
		}
		return tempPilot;
	}

	@Override
	public void addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		archivePilot.add(pilot);
		
	}

	@Override
	public List<PilotModel> getPilotList() {
		// TODO Auto-generated method stub
		return archivePilot;
	}

	@Override
	public PilotModel getPilotDetailById(String id) {
		// TODO Auto-generated method stub
		PilotModel tempPilot = null;
		for(int i = 0; i < archivePilot.size(); i++) {
			if(archivePilot.get(i).getId().equals(id)) {
				tempPilot = archivePilot.get(i);
			}
		}
		return tempPilot;
	}

	@Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		archivePilot.remove(pilot);
	}
	
}