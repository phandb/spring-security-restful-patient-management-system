package com.javaspringprojects.securityrestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaspringprojects.securityrestful.entity.Patient;
import com.javaspringprojects.securityrestful.service.PatientService;

public class ApplicationController {

	
	
	//autowire the PatientService
		@Autowired
		private PatientService patientService;
		
		
		//-------------------Retrieve All Patients--------------------------------------------------------		
		//add mapping for GET /patients
		@GetMapping("/all-patient-info")
		public List<Patient> listAllPatients(){
			return patientService.getAllPatients();
		}
		
		//--------------------Retrieve patient by id  ----------------------
		@GetMapping("/patients/{patientId}")
		public Patient getPatientById(@PathVariable int patientId){
			
			Patient thePatient = patientService.getPatientById(patientId);
			
			if (thePatient == null) {
				throw new PatientNotFoundException("Patient ID not found - " + patientId);
			}
			
			
			return thePatient;
		}
		
		//Add mapping for POST /patients --- Add new patient
		@PostMapping("/patients")
		public Patient addPatient(@RequestBody Patient thePatient) {
			//Also just incase the pass an id in JSON...set id to 0
			//This is force save of a new item...instead of update
			
			thePatient.setId(0);
			
			patientService.saveOrUpdatePatient(thePatient);
			
			return thePatient;
		}
		
		//Add mapping for PUT /patients -- update existing patient
		@PutMapping("/patients")
		public Patient updatePatient(@RequestBody Patient thePatient) {
			
			patientService.saveOrUpdatePatient(thePatient);
			
			return thePatient;
			
		}
		
		//Add mapping for DELETE /patient/{patientId} -- delete a patient
		@DeleteMapping("/patients/{patentid}")
		public String deletePatient(@PathVariable int patientId) {
			
			Patient tempPatient = patientService.getPatientById(patientId);
			
			//throw exception if null
			
			if (tempPatient == null) {
				throw new PatientNotFoundException("Patient id  not found -" + patientId);
				
			}
			
			patientService.deletePatient(patientId);
			
			return "Delete patient id = " + patientId;
		}
}
