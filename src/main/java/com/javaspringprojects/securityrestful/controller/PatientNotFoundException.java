package com.javaspringprojects.securityrestful.controller;

public class PatientNotFoundException extends RuntimeException {
	
	public PatientNotFoundException() {
		
	}
	
	public PatientNotFoundException(String message) {
		super(message);
	}
	
	public PatientNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public PatientNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	public PatientNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writeableStackTrace) {
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
