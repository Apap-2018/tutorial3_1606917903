package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					  @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
					  @RequestParam(value = "name", required = true) String name,
					  @RequestParam(value = "flyHour", required = true) int flyHour){
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	

	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping({"/pilot/view/license-number","pilot/view/license-number/{licenseNumber}"})
	public String viewPilot(@PathVariable (value = "licenseNumber", required = false) String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if (archive != null) {
			System.out.println(archive);
			model.addAttribute("pilot", archive);
			return "view-pilot";
		}
		else {
			String errorMessage = "";
			if(licenseNumber == null) {
				errorMessage = "Error : License number belum dimasukkan!";
			}
			else {
				errorMessage = "Error : Pilot dengan license number "+licenseNumber+" tidak ditemukan";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
	}
	
	@RequestMapping({"/pilot/update/license-number","pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}"})
	public String updateFlyHour(@PathVariable (value = "licenseNumber", required = false) String licenseNumber, 
								@PathVariable (value = "flyHour", required = false) Integer flyHour, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if (archive != null) {
			archive.setFlyHour(flyHour);
			String successMessage = "Pilot dengan license number "+licenseNumber+" berhasil diupdate";
			model.addAttribute("successMessage", successMessage);
			return "success-page";
		}
		else {
			String errorMessage = "";
			if(licenseNumber == null) {
				errorMessage = "Error : License number belum dimasukkan!";
			}
			else {
				errorMessage = "Error : Pilot dengan license number "+licenseNumber+" tidak ditemukan";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
	}
	
	@RequestMapping({"/pilot/delete/id/","pilot/delete/id/{id}"})
	public String deletePilot(@PathVariable (value = "id", required = false) String id, Model model) {
		PilotModel archive = pilotService.getPilotDetailById(id);
		if (archive != null) {
			pilotService.deletePilot(archive);
			String successMessage = "Pilot dengan id "+id+" berhasil didelete";
			model.addAttribute("successMessage", successMessage);
			return "success-page";
		}
		else {
			String errorMessage = "";
			if(id == null) {
				errorMessage = "Error : Id number belum dimasukkan!";
			}
			else {
				errorMessage = "Error : Pilot dengan id "+id+" tidak ditemukan";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "error-page";
		}
	}
}
