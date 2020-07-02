package com.java.main.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.java.main.exception.InvalidRegistrationNumberException;
import com.java.main.exception.NoSlotAvailableException;
import com.java.main.interfaces.ParkingSlot;
import com.java.main.interfaces.Vehicle;
import com.java.main.interfaces.VehicleParking;

/**
 * Class Parking: containing all slots and cars.  
 * 
 * Contains function interacting with cars and slots object.
 * 
 * Functionality implementation of VehicleParking
 * 
 * */
public class Parking implements VehicleParking{
	
	private String parkingName;
	private List<ParkingSlot> totalParkingSlots;
	private int numberOfFloors;
	private int emptySlot;
	private HashMap<String,Integer> byRegistrationNumber;
	private HashMap<String, List<ParkingSlot>> byColourRegistrationNumber;

	public Parking() {
		
		this.parkingName="Default Parking";
		this.totalParkingSlots=new ArrayList<ParkingSlot>(100);
		this.numberOfFloors=1;
		this.emptySlot=0;
		byRegistrationNumber=new HashMap<String,Integer>();
		byColourRegistrationNumber=new HashMap<String,List<ParkingSlot>>();
		initializeParking(100);
	}
	
	public Parking(int totalParkingSlots) {
		this.parkingName="Default Parking";
		this.totalParkingSlots=new ArrayList<ParkingSlot>(totalParkingSlots);
		this.numberOfFloors=1;
		this.emptySlot=0;
		byRegistrationNumber=new HashMap<String,Integer>();
		byColourRegistrationNumber=new HashMap<String,List<ParkingSlot>>();
		initializeParking(totalParkingSlots);
	}
	
	public Parking(String name,int totalParkingSlots, int numberOfFloors) {
		this.parkingName=name;
		this.totalParkingSlots=new ArrayList<ParkingSlot>(totalParkingSlots);
		this.numberOfFloors=numberOfFloors;
		this.emptySlot=0;
		byRegistrationNumber=new HashMap<String,Integer>();
		byColourRegistrationNumber=new HashMap<String,List<ParkingSlot>>();
		initializeParking(totalParkingSlots);
	}
	
	/**
	 * Initialize parking slots
	 * */
	private void initializeParking(int totalParkingSlots) {
		for(int i=0; i<totalParkingSlots;i++) {
			ParkingSlot p=new CarParkingSlot(i);
			this.totalParkingSlots.add(i, p);
		}
	}
	
	/**
	 * Find next empty slot
	 * */
	public int nextEmptySpot() {
		
		for(int i=this.emptySlot+1; i<this.totalParkingSlots.size(); i++) {
			if(this.totalParkingSlots.get(i).getParkedVehicle()==null) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Set vehicles in parking slot
	 * 
	 * @param: car
	 * */
	public void setVehicleInParkingSlot(Vehicle car) throws NoSlotAvailableException,InvalidRegistrationNumberException{
		
		if(this.emptySlot!=-1 && this.totalParkingSlots.get(this.emptySlot).getParkedVehicle()==null) {

			if(!this.byRegistrationNumber.containsKey(car.getRegistrationNumber())) {
				this.totalParkingSlots.get(this.emptySlot).setParkedVehicle(car);
				this.byRegistrationNumber.put(car.getRegistrationNumber(), this.emptySlot);
				
				if(this.byColourRegistrationNumber.containsKey(car.getColour())) {
					this.byColourRegistrationNumber.get(car.getColour()).add(this.totalParkingSlots.get(this.emptySlot));
				}else {
					this.byColourRegistrationNumber.put(car.getColour(), new ArrayList<ParkingSlot>());
					this.byColourRegistrationNumber.get(car.getColour()).add(this.totalParkingSlots.get(this.emptySlot));

				}
				this.emptySlot=nextEmptySpot();
				return;
				}
			else {
				throw new InvalidRegistrationNumberException("Car with same registration number is already present. Enter correct registration number.");
				}
			}
		else{
			this.emptySlot=nextEmptySpot();
			if(this.emptySlot==-1) {
				throw new NoSlotAvailableException("Sorry, parking lot is full");
			}else {
				try {
					setVehicleInParkingSlot(car);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	/**
	 * Remove vehicle from parking slot.
	 * @param: int index
	 * */
	public void removeVehicleFromParkingSlot(int index) {
		this.byColourRegistrationNumber.get(this.totalParkingSlots.get(index).getParkedVehicle().getColour()).remove(this.totalParkingSlots.get(index));
		this.byRegistrationNumber.remove(this.totalParkingSlots.get(index).getParkedVehicle().getRegistrationNumber());
		this.totalParkingSlots.get(index).removeParkedVehicle();
		if(this.emptySlot==-1) {
			this.emptySlot=index;
		}
		else if(index<this.emptySlot) {
			this.emptySlot=index;
		}	
	}
	
	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public List<ParkingSlot> getTotalParkingSlots() {
		return totalParkingSlots;
	}

	public void setTotalParkingSlots(List<ParkingSlot> totalParkingSlots) {
		this.totalParkingSlots = totalParkingSlots;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public int getEmptySlot() {
		return emptySlot;
	}

	public void setEmptySlot(int emptySlot) {
		this.emptySlot = emptySlot;
	}

	public HashMap<String, Integer> getByRegistrationNumber() {
		return byRegistrationNumber;
	}

	public void setByRegistrationNumber(HashMap<String, Integer> byRegistrationNumber) {
		this.byRegistrationNumber = byRegistrationNumber;
	}

	public HashMap<String, List<ParkingSlot>> getByColourRegistrationNumber() {
		return byColourRegistrationNumber;
	}

	public void setByColourRegistrationNumber(HashMap<String, List<ParkingSlot>> byColourRegistrationNumber) {
		this.byColourRegistrationNumber = byColourRegistrationNumber;
	}
	
	
	
}
