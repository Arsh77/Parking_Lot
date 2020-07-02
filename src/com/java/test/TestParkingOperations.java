package com.java.test;

import org.junit.Test;

import com.java.main.parking_operations.ParkingOperations;
import static org.junit.Assert.assertTrue;
import com.java.main.exception.*;
import org.junit.Before;

public class TestParkingOperations {
	
	private ParkingOperations po;
	private ConsoleOutputCapturer coc;
	
	@Before
	public void initialization(){
		this.po =ParkingOperations.getParkingOperations();
		this.coc=new ConsoleOutputCapturer();
	}
	/*
	create_parking_lot 6
	park KA-01-HH-1234 White
	park KA-01-HH-9999 White
	park KA-01-BB-0001 Black
	park KA-01-HH-7777 Red
	park KA-01-HH-2701 Blue
	park KA-01-HH-3141 Black
	leave 4
	status
	park KA-01-P-333 White
	park DL-12-AA-9999 White
	registration_numbers_for_cars_with_colour White
	slot_numbers_for_cars_with_colour White
	slot_number_for_registration_number KA-01-HH-3141
	slot_number_for_registration_number MH-04-AY-1111
	 * */
	
	private String stringWithAlphaNumeralOnly(String s) {
		String str="";
		for(char ch: s.toCharArray()) {
			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				str+=ch;
				}
		}
		return str;
	}

	
	/**
	 * Test : Create a parking lot
	 * 
	 * Added 6 slots to parking lot.
	 */
	@Test
	public void testCreateParkingLot(){
		coc.start();
		po.initialiseParking(6);
		String s=coc.stop();//System.out.toString();
		assertTrue(stringWithAlphaNumeralOnly(s).equalsIgnoreCase(stringWithAlphaNumeralOnly("Created a parking lot with 6 slots")));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * 
	 * Test : Park a car in parking lot
	 * 
	 * */
	@Test
	public void testAddCarToParking() {
		po.initialiseParking(6);
		coc.start();
		po.parkVehicle("KA-01-HH-1234", "White");
		String response=coc.stop();
		String expectedResponse="Allocated slot number: 1";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * 
	 * Test : Remove a car from parking lot
	 * 
	 * */
	@Test
	public void testRemoveCarFromParking() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		coc.start();
		po.removeParkedVehicle(1);
		String response=coc.stop();
		String expectedResponse="Slot number 1 is free";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * Test : Check the status of parking lot
	 * 
	 * */
	@Test
	public void testStatusOfParkingLot() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		coc.start();
		po.status();
		String response=coc.stop();
		String expectedResponse="Slot No.    Registration No       Color\r\n" + "1            KA-01-HH-1234        White\r\n" + "2            KA-01-HH-9999        White";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * 
	 * Test : Get registration number of cars of particular colour
	 * 
	 * */
	@Test
	public void testRegNumByColour() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		po.parkVehicle("KA-01-BB-0001", "Black");
		po.parkVehicle("KA-01-HH-7777", "Red");
		po.parkVehicle("KA-01-HH-2701", "Blue");
		po.parkVehicle("KA-01-HH-3141", "Black");
		coc.start();
		po.getRegistrationNumberByVehicleColour("White");
		String response=coc.stop();
		String expectedResponse="KA-01-HH-1234, KA-01-HH-9999";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	/**
	 * 
	 * Test : Check the output while getting the slot number of vehicles according to given colour.
	 * 
	 * */
	@Test
	public void testSlotNumByColour() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		po.parkVehicle("KA-01-BB-0001", "Black");
		po.parkVehicle("KA-01-HH-7777", "Red");
		po.parkVehicle("KA-01-HH-2701", "Blue");
		po.parkVehicle("KA-01-HH-3141", "Black");
		coc.start();
		po.getSlotNumberByVehicleColour("Black");
		String response=coc.stop();
		String expectedResponse="3, 6";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	/**
	 * Test : Get slot number of a car of a particular registration number
	 * */
	@Test
	public void testSlotByRegNum() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		po.parkVehicle("KA-01-BB-0001", "Black");
		po.parkVehicle("KA-01-HH-7777", "Red");
		po.parkVehicle("KA-01-HH-2701", "Blue");
		po.parkVehicle("KA-01-HH-3141", "Black");
		coc.start();
		po.getSlotNumberForVehicleWithGivenRegstrationNumber("KA-01-HH-2701");
		String response=coc.stop();
		String expectedResponse="5";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * Test : Passing invalid registration number for a vehicle
	 * */
	@Test
	public void invalidRegistrationNumber() {
		po.initialiseParking(6);
		coc.start();
		po.parkVehicle("KA-01-HH", "White");
		String response=coc.stop();
		String expectedResponse="Entered car registration number is not a valid. Please enter again";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * Test  : Add vehicle when parking lot is full
	 * */
	@Test
	public void testFullParkingLot() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		po.parkVehicle("KA-01-BB-0001", "Black");
		po.parkVehicle("KA-01-HH-7777", "Red");
		po.parkVehicle("KA-01-HH-2701", "Blue");
		po.parkVehicle("KA-01-HH-3141", "Black");
		coc.start();
		po.parkVehicle("KA-01-HH-2702","Blue");
		String response=coc.stop();
		String expectedResponse="Sorry, parking lot is full";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
	
	/**
	 * Test : Try to add vehicle with registration number same as existed
	 * */
	@Test
	public void testAddExstingRegNum() {
		po.initialiseParking(6);
		po.parkVehicle("KA-01-HH-1234", "White");
		po.parkVehicle("KA-01-HH-9999", "White");
		po.parkVehicle("KA-01-BB-0001", "Black");
		po.parkVehicle("KA-01-HH-7777", "Red");
		po.parkVehicle("KA-01-HH-2701", "Blue");
		coc.start();
		po.parkVehicle("KA-01-HH-2701","Yellow");
		String response=coc.stop();
		String expectedResponse="Car with same registration number is already present. Enter correct registration number.";
		assertTrue(stringWithAlphaNumeralOnly(response).equalsIgnoreCase(stringWithAlphaNumeralOnly(expectedResponse)));
		ParkingOperations.setParkingOperationsAsNull();
	}
}
