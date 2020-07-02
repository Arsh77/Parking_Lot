package com.java.main.entity;

import com.java.main.interfaces.Vehicle;
import java.util.Date;
import com.java.main.interfaces.ParkingSlot;


/**
 * 
 * Class with details of activity taking place in slot. 
 * 
 * */
public class CarParkingSlot implements ParkingSlot{
	
	private int slotNumber;
	private boolean isEmpty;
	private Vehicle parkedVehicle;
	private Date date;
	 
	public CarParkingSlot(int slotNo) {
		
		this.slotNumber = slotNo;
		this.isEmpty = true;
		//If payment facilities added.
		this.setDate(new Date());
	}
	
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	public Vehicle getParkedVehicle() {
		return parkedVehicle;
	}
	public void setParkedVehicle(Vehicle parkedVehicle) {
		this.parkedVehicle = parkedVehicle;
		setEmpty(false);
	}
	public void removeParkedVehicle() {
		this.parkedVehicle=null;
		setEmpty(true);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
