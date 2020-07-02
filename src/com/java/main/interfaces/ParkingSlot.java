package com.java.main.interfaces;

import java.util.Date;

import com.java.main.entity.Car;

public interface ParkingSlot {
	
	public static final int slotNumber=0;
	public static final boolean isEmpty=true;
	public static final Vehicle parkedVehicle=null;
	public static final Date date=null;
	
	public abstract int getSlotNumber();
	public abstract void setSlotNumber(int slotNumber);
	public abstract boolean isEmpty();
	public abstract void setEmpty(boolean isEmpty);
	public abstract Vehicle getParkedVehicle();
	public abstract void setParkedVehicle(Vehicle parkedVehicle);
	public abstract void removeParkedVehicle();
	public abstract Date getDate();
	public abstract void setDate(Date date);
	
}
