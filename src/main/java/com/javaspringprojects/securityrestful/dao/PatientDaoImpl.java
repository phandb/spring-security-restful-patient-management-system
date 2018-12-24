package com.javaspringprojects.securityrestful.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaspringprojects.securityrestful.entity.Patient;

@Repository("patientDao")
public class PatientDaoImpl implements PatientDao {
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional is managed in Service 
	public List<Patient> getAllPatients(){
		
		// get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query...and sort by last name
		Query<Patient> theQuery = currentSession.createQuery("from Patient order by last_name", Patient.class);
		
		//execute query and get result list
		List<Patient> patients = theQuery.getResultList();
		
		//return the results
		return patients;
	}
	
	@Override
	//@Transactional is managed in Service 
	public Patient getPatientById(Integer patientId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read from database using the primary key
		Patient thePatient = currentSession.get(Patient.class,  patientId);
		
		return thePatient;
		
	}
	
	@Override
	//@Transactional is managed in Service 
	public Patient saveOrUpdatePatient(Patient thePatient) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer
		//save method INSERT new record; update method UPDATE existing record
		//currentSession.save(thePatient);  
		
		//the saveOrUpdate will either save new record or update existing record.  
		currentSession.saveOrUpdate(thePatient);	
		return null;
	}
	
	@Override
	//@Transactional is managed in Service 
	public void deletePatient(int patientId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete object with primary key
		Query<Patient> theQuery = currentSession.createQuery("delete from Patient where id=:patientId", Patient.class);
		
		theQuery.setParameter("patientId", patientId);
		
		theQuery.executeUpdate();		
	}
	
	@Override
	public List<Patient> searchPatients(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Patient> theQuery = null;
		
		//Only search by name if theSearchName is not empty
		if(theSearchName != null && theSearchName.trim().length() > 0) {
			//search for first name or last name....case insensitive
			theQuery = currentSession.createQuery("from Patient where lower(firstName) like :theName "
					+ " or lower(lastName) like :theName ", Patient.class);
			theQuery.setParameter("theName","%" + theSearchName.toLowerCase() + "%");
			
		}
		else {
			//theSearchName is empty...so just get all patients
			theQuery = currentSession.createQuery("from Patient", Patient.class);
			
		}
		
		//execute query and get result list
		List<Patient> patients = theQuery.getResultList();
		
		//return the results
		return patients;
	}
	

}
