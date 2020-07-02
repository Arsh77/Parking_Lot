package com.java.main;

import java.util.Scanner;

import com.java.main.command.Command;
import com.java.main.exception.EmptyInputException;
import com.java.main.interfaces.Executables;
import com.java.main.parking_operations.ParkingOperations;
import com.java.main.validator.*;
import com.java.test.TestRunner;

public class RunMain {
	/**
	 * 
	 * Main function: provide user interaction from console.
	 * 
	 * */
	public static void main(String[] args) {
		/**
		 * Run JUnit test before running the application.
		 * */
		TestRunner.test();
		System.out.println("***********************************Test Finish***********************************\n");
		
		printCommands();
		
		String inputCommand;
        Scanner scanner = new Scanner(System.in);
        Executables function;
        ParkingOperations.setParkingOperationsAsNull();
        
        while(true) {
        	inputCommand = scanner.nextLine();
        	if(inputCommand.equalsIgnoreCase("quit") ||inputCommand.equalsIgnoreCase("exit")) {
        		ParkingOperations.setParkingOperationsAsNull();
        		break;
        	}
        	try {
        		if(Validator.isValidInput(inputCommand)) {
        			String[] inputCmdArr= inputCommand.strip().split(" ");
        			function=Command.valueOf(inputCmdArr[0]);
        			function.runSyntax(inputCmdArr);
        		}
        	}catch(EmptyInputException ei) {
        		System.out.println(ei.getMessage());
        	}catch (Exception e) {
                System.out.println(inputCommand+" is not valid. Please provide valid command....");
            }
        }
        scanner.close();
	}
	
	private static void printCommands() {
		
		System.out.println("********Welcome to Parking Lot*******\n");
		System.out.println("Following are the supported commands : ");
		System.out.println(">> create_parking_lot {Enter Number of Parking Slot}");
		System.out.println(">> park {Registration Number} {Colour}");
		System.out.println(">> leave {slot number}");
		System.out.println(">> status");
		System.out.println(">> registration_numbers_for_cars_with_colour {Colour}");
		System.out.println(">> slot_numbers_for_cars_with_colour {Colour}");
		System.out.println(">> slot_number_for_registration_number {Registration Number}\n");
		
	}

}
