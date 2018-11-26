package com.javaspringprojects.securityrestful.service;

import java.util.List;

import com.javaspringprojects.securityrestful.entity.Patient;

public interface PatientService {

	
	public List<Patient> getAllPatients();
	public Patient getPatientById(Integer patientId);
	public Patient saveOrUpdatePatient(Patient thePatient);
	public void deletePatient(Patient thePatient);
	public List<Patient> searchPatients(String theSearchName);


}
