package com.java.main.interfaces;

public interface Vehicle {
	
	public static final String registrationNumber="";
	public static final String colour="";
	
	public abstract String getColour();
	
	public abstract void setColour(String colour);
	
	public abstract String getRegistrationNumber();
	
	public abstract void setRegistrationNumber(String registrationNumber);
}
