package com.java.main.entity;

import com.java.main.interfaces.Vehicle;

/**
 * 
 * Car entity: an implementation of Vehicle Interface.
 * 
 * */
public class Car implements Vehicle{
	
	private String registrationNumber;
	private String colour;
	
	public Car(String registrationNumber,String colour) {
		this.registrationNumber=registrationNumber;
		this.colour=colour;
	}
	
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
}
