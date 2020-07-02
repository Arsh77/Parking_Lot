package com.java.main.command;

import com.java.main.interfaces.Executables;
import com.java.main.parking_operations.ParkingOperations;

/**
 * 
 * Enum to handle input commands and run corresponding function.
 * 
 * */
public enum Command implements Executables{	
	
	create_parking_lot {
        
        public void runSyntax(String[] inputCommands) {
            po.initialiseParking(Integer.parseInt(inputCommands[1]));
        }
    },

    park {
        public void runSyntax(String[] inputCommands) {
        	po.parkVehicle(inputCommands[1], inputCommands[2]);
        }
    },
    leave {
        public void runSyntax(String[] inputCommands) {
        	po.removeParkedVehicle(Integer.parseInt(inputCommands[1]));
        }
    },
    status {
        public void runSyntax(String[] inputCommands) {
            po.status();
        }
    },
    registration_numbers_for_cars_with_colour {
        public void runSyntax(String[] inputCommands) {
            po.getRegistrationNumberByVehicleColour(inputCommands[1]);
        }
    },
    slot_numbers_for_cars_with_colour {
        public void runSyntax(String[] inputCommands) {
            po.getSlotNumberByVehicleColour(inputCommands[1]);
        }
    },
    slot_number_for_registration_number {
        public void runSyntax(String[] inputCommands) {
            po.getSlotNumberForVehicleWithGivenRegstrationNumber(inputCommands[1]);
        }
    },
    quit {
        public void runSyntax(String[] inputCommands) {

        }
    };
	ParkingOperations po=ParkingOperations.getParkingOperations();
}

