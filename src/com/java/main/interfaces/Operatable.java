package com.java.main.interfaces;

import com.java.main.entity.Parking;

public interface Operatable {
	
	Parking parking=null;

	public abstract void initialiseParking(int numberOfSlots);
	
	public abstract void parkVehicle(String registrationNumber, String colour);
	
	public abstract void status();
	
	public abstract void removeParkedVehicle(int index);
	
}
