package com.javaspringprojects.securityrestful.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//Map entity class to database table
@Entity
@Table(name = "patients")

@NamedQueries({
	@NamedQuery(name="Patient.getPatientMedicationInfoById",
			query="select distinct p from Patient p " +
					"left join fetch p.medication m " + 
					" where p.id = :id"),
	@NamedQuery(name="Patient.getPatientPhysicianInfoById",
			query="select distinct p from Patient p " +
				   "left join fetch p.physician phy " +
					" where p.id = :id"),
	@NamedQuery(name="Patient.getPatientPharmacyInfoById",
			query="select distinct p from Patient p " +
					"left join fetch p.pharmacy phar " + 
					" where p.id = :id"),
	
}) 

public class Patient implements Serializable{
	
	
	//Map to appropriate column in table patients
	//name = "column name" in database
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="address")
	private String address;
	
	/****************Define relationship with medication*************************/
	
	//Mapping One to Many relationship with medications table
	@OneToMany(mappedBy="patient", cascade= CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name="patient_id")
	private Set<Medication> medications = new HashSet<>();
	
	//Setter and getter for relationship with medications table 
		public Set<Medication> getMedications() {
			return medications;
		}


		public void setMedications(Set<Medication> medications) {
			this.medications = medications;
		}
		
		public boolean addMedicationToPatient(Medication medication) {
			medication.setPatient(this);
			return getMedications().add(medication);
		}
		
		public void removeMedicationFromPatient(Medication medication) {
			getMedications().remove(medication);
		}
	
	/*******************************************************************/
	//Mapping Many To Many relationship with physicians table
	/*@ManyToMany(fetch=FetchType.EAGER,
				cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})*/
	@ManyToMany
	@JoinTable(
			name="patients_physicians",
			joinColumns=@JoinColumn(name="patient_id"),
			inverseJoinColumns=@JoinColumn(name="physician_id")
			)
	private Set<Physician> physicians = new HashSet<>();
	
	//Setter and getter for physicians


		public Set<Physician> getPhysicians() {
			return physicians;
		}


		public void setPhysicians(Set<Physician> physicians) {
			this.physicians = physicians;
		}
		
		
	
	/*****************************************************************/
	//Mapping Many To Many relationship with pharmacies table
	//No CascadeType.REMOVE since we don't want to delete a patient
	@ManyToMany(fetch=FetchType.LAZY,
				cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH	})
	@JoinTable(
			name = "patient_pharmacy",
			joinColumns=@JoinColumn(name="patient_id"),
			inverseJoinColumns=@JoinColumn(name="pharmacy_id")
			)
	private List<Pharmacy> pharmacies;
	
	//Setter and getter for pharmacies
			public List<Pharmacy> getPharmacies() {
				return pharmacies;
			}


			public void setPharmacies(List<Pharmacy> pharmacies) {
				this.pharmacies = pharmacies;
			}

	
	/*****************************************************************/
	
	
	
	//default constructor
	public Patient() {
		
	}
	
	

	//Getter and setter methods
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		if (middleName != null) {
			return middleName;
		}else 
			middleName = middleName + ' ';
		return middleName;
		
	}


	public void setMiddleName(String middleName) {
		
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
		
	
	
	//ToString method
	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", address="
				+ address + "]";
	}	
	
	//Patient full name
	public String patientFullName() {
		String fullName;
		if(middleName  != null) {
			fullName = firstName + " " + middleName + " " + lastName;
		}else {
			middleName = middleName + " ";
			fullName = firstName + " " + middleName + " " + lastName;
		}
		
		
		return fullName;
	}
	
/*	
	//Add convenience methods for bi-directional relationship
	
	public void addPhysicianToPatient(Physician tempPhysician) {
		if (physicians == null) {
			physicians = new ArrayList<>();
		}
		
		this.physicians.add(tempPhysician);
		
		tempPhysician.getPatients().add(this);
	}
	*/
	public void removePhysician(Physician tempPhysician) {
		this.physicians.remove(tempPhysician);
		tempPhysician.getPatients().remove(this);
	}
	
	
	//Add convenience methods for bi-directional relationship with medications
	public void add(Medication tempMedication) {
		if (medications == null) {
			medications = new ArrayList<>();
			
		}
		medications.add(tempMedication);
		tempMedication.setPatient(this);
	}
	//Convenience add medications method for uni-directional relationship
	public void addMedication(Medication theMedication) {
		if(medications == null) {
			medications = new ArrayList<>();
		}
		
		medications.add(theMedication);
		
	}
	

}

