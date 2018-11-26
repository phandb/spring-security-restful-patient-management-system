package com.javaspringprojects.securityrestful.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="medications")
public class Medication implements Serializable {
	//annotate the class as an entity and map to database table

		//define the fields
		//annotate the fields with db column names
		/***
		 * Set up relationship with patients table using FK patient_id
		 */
		//create constructors
		//generate getter/setter methods
		//generate toString() method
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="medication_name")
	private String medicationName;
	
	@Column(name="medication_strength")
	private String medicationStrength;
	
	@Column(name="medication_dosage")
	private String medicationDosage;
	
	/***********************************************************************************/
	//Setup many to one relationship with patient table
	//one patient can have many medications
	@ManyToOne  
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	public Patient getPatient() {
		return this.patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	/*********************************************************************************/
	
	//Constructors
	public Medication() {
		
	}
	
	/**
	 * @param medicationName
	 * @param medicationStrength
	 * @param medicationDosage
	 */
	public Medication(String medicationName, String medicationStrength, String medicationDosage ) {
		this.medicationName = medicationName;
		this.medicationStrength = medicationStrength;
		this.medicationDosage = medicationDosage;
	}


	public int getId() {
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMedicationName() {
		return medicationName;
	}


	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}


	public String getMedicationStrength() {
		return medicationStrength;
	}


	public void setMedicationStrength(String medicationStrength) {
		this.medicationStrength = medicationStrength;
	}


	public String getMedicationDosage() {
		return medicationDosage;
	}


	public void setMedicationDosage(String medicationDosage) {
		this.medicationDosage = medicationDosage;
	}
	
	
	

}
