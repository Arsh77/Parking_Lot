package com.java.main.interfaces;

import java.util.List;

import com.java.main.exception.InvalidRegistrationNumberException;
import com.java.main.exception.NoSlotAvailableException;

public interface VehicleParking {
	
	public static final String parkingName="";
	public static final List<ParkingSlot> totalParkingSlots=null;
	public static final int numberOfFloors=0;
	public static final int emptySlot=0;
	
	public int nextEmptySpot();
	
	public void setVehicleInParkingSlot(Vehicle car) throws NoSlotAvailableException,InvalidRegistrationNumberException;
	
	public void removeVehicleFromParkingSlot(int index);
	
	public String getParkingName();

	public void setParkingName(String parkingName);

	public List<ParkingSlot> getTotalParkingSlots();

	public void setTotalParkingSlots(List<ParkingSlot> totalParkingSlots);

	public int getNumberOfFloors();

	public void setNumberOfFloors(int numberOfFloors);

	public int getEmptySlot();

	public void setEmptySlot(int emptySlot);

}
