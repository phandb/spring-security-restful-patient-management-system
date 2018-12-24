package com.javaspringprojects.securityrestful.dao;

import java.util.List;

import com.javaspringprojects.securityrestful.entity.Patient;

public interface PatientDao {
	
	public List<Patient> getAllPatients();
	public Patient getPatientById(Integer patientId);
	public Patient saveOrUpdatePatient(Patient thePatient);
	public void deletePatient(int patientId);
	public List<Patient> searchPatients(String theSearchName);

}
