package com.javaspringprojects.securityrestful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaspringprojects.securityrestful.dao.PatientDao;
import com.javaspringprojects.securityrestful.entity.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	//Need to inject patient Dao
	@Autowired
	private PatientDao patientDao;
	
	@Override
	@Transactional
	public List<Patient> getAllPatients(){
		
		return patientDao.getAllPatients();
	}
	
	@Override
	@Transactional
	public Patient getPatientById(Integer patientId) {
		
		return patientDao.getPatientById(patientId);
		
	}
	
	@Override
	@Transactional
	public Patient saveOrUpdatePatient(Patient thePatient) {
		
		return patientDao.saveOrUpdatePatient(thePatient);
		
	}
	
	@Override
	@Transactional
	public void deletePatient(Patient thePatient) {
		
		patientDao.deletePatient(thePatient);
	}
	
	@Override
	@Transactional
	public List<Patient> searchPatients(String theSearchName){
		
		return patientDao.searchPatients(theSearchName);
	}


}
