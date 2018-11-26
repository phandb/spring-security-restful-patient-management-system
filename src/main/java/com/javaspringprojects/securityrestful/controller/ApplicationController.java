package com.javaspringprojects.securityrestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaspringprojects.securityrestful.entity.Patient;
import com.javaspringprojects.securityrestful.service.PatientService;

@RestController
@RequestMapping("/patient")
public class ApplicationController {
	
	
	//autowire the PatientService
		@Autowired
		private PatientService patientService;
		
		//add mapping for GET /patients
		@GetMapping("/all-patient-info")
		public List<Patient> getPatients(){
			return patientService.getAllPatients();
		}
}
