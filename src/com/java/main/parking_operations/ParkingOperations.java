package com.java.main.parking_operations;

import java.util.List;
import com.java.main.entity.*;
import com.java.main.exception.InvalidRegistrationNumberException;
import com.java.main.exception.NoSlotAvailableException;
import com.java.main.interfaces.Operatable;
import com.java.main.interfaces.ParkingSlot;
import com.java.main.interfaces.Vehicle;
import com.java.main.validator.Validator;

/**
 * 
 * This Class contains all the functionalities performed by the parking lot.
 * 
 * */
public class ParkingOperations implements Operatable{
	
	private static ParkingOperations po;
	Parking parking;
	
	/**
	 * Singleton: So that only once instance of ParkingOperation is available at any given time.
	 * */ 
	private ParkingOperations() {
	}
	
	/**
	 * Get object!
	 * */
	public static ParkingOperations getParkingOperations() {
		if(po==null) {
			po=new ParkingOperations();
		}
		return po;
	}
	
	/**
	 * Set object as null.
	 * */
	public static void setParkingOperationsAsNull() {
		po=null;
	}
	
	/**
	 * Initialize Parking: Create total number of slots in Array<ParkingSlots> 
	 * and initialize each slot with a slot number, isEmpty True and vehicle as null. 
	 * 
	 * @param numberOfSlots
	 * */
	public void initialiseParking(int numberOfSlots) {
		
		this.parking = new Parking(numberOfSlots);
		System.out.println("Created a parking lot with "+numberOfSlots+" slots");

	}
	
	
	/**
	 * Add a vehicle to parking lot in the nearest empty slot from entrance.
	 * 
	 * @param registrationNumber
	 * @param colour
	 * */
	public void parkVehicle(String registrationNumber, String colour) {
		try {
			Validator.isValidRegistrationNumber(registrationNumber);
			Vehicle car=new Car(registrationNumber,colour);
			int index=this.parking.getEmptySlot();
			this.parking.setVehicleInParkingSlot(car);
			System.out.println("Allocated slot number: "+(index+1));
		} 
		catch (NoSlotAvailableException e) {
			System.out.println(e.getMessage());
			
		}
		catch(InvalidRegistrationNumberException ex) {
			System.out.println(ex.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("First initialize the parking lot");
		}
		
	}
	
	/**
	 * Shows the status of all the filled slots with information about vehicles (in this case cars) 
	 * parked in those slots as well.
	 *  
	 * */
	public void status() {
		System.out.format("%1s%19s%13s","Slot No.","Registration No","Color\n");
		for(ParkingSlot p : this.parking.getTotalParkingSlots()) {
			if(!p.isEmpty()) {
				System.out.format("%1d%25s%14s",(p.getSlotNumber()+1),p.getParkedVehicle().getRegistrationNumber(),p.getParkedVehicle().getColour()+"\n");
				}
		}
	}

	/**
	 * Remove a vehicle from given parking slot.
	 * 
	 * @param index
	 * */
	public void removeParkedVehicle(int index) {
		parking.removeVehicleFromParkingSlot(index-1);
		System.out.println("Slot number "+ index +" is free");
	}
	
	/**
	 * Shows cars registration number based on the colour provided.
	 * 
	 * @param colour
	 * */
	public void getRegistrationNumberByVehicleColour(String colour) {
		
		List<ParkingSlot> listOfvehicle= parking.getByColourRegistrationNumber().get(colour);
		int len=listOfvehicle.size();
		for(int i=0; i<len;i++) {
			if(i<len-1) {
				System.out.print(listOfvehicle.get(i).getParkedVehicle().getRegistrationNumber()+", ");
			}
			else {
				System.out.print(listOfvehicle.get(i).getParkedVehicle().getRegistrationNumber());
			}
		}
		System.out.println();
		
	}
	
	/**
	 * Shows cars slot number based on the colour provided.
	 * 
	 * @param colour
	 * */
	public void getSlotNumberByVehicleColour(String colour) {
		List<ParkingSlot> listOfvehicle= parking.getByColourRegistrationNumber().get(colour);
		int len=listOfvehicle.size();
		for(int i=0; i<len;i++) {
			if(i<len-1) {
				System.out.print((listOfvehicle.get(i).getSlotNumber()+1)+", ");
			}
			else {
				System.out.print((listOfvehicle.get(i).getSlotNumber()+1));
			}
		}
		System.out.println();
	}
	
	/**
	 * Shows the slot number of car with given registration number.
	 * 
	 * @param registrationNumber
	 * */
	public void getSlotNumberForVehicleWithGivenRegstrationNumber(String registrationNumber) {
		try {
			Validator.isValidRegistrationNumber(registrationNumber);
			if(parking.getByRegistrationNumber().containsKey(registrationNumber)) {
				System.out.println(parking.getByRegistrationNumber().get(registrationNumber)+1);
			}
			else {
				System.out.println("Not found");
			}
		}catch(InvalidRegistrationNumberException ex){
			System.out.println(ex.getMessage());
		}
	}
	
}
