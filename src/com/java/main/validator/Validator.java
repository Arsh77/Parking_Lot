package com.java.main.validator;

import com.java.main.exception.*;

import java.util.regex.Pattern;

/**
 * 
 * Data validators
 * 
 * */
public interface Validator{
	
    public static boolean isValidInput(String input) throws EmptyInputException{
        if (input.isEmpty()) {
            throw new EmptyInputException("Input is empty. Please enter valid input.");
        }
        return true;
    }
    
    public static boolean isValidRegistrationNumber(String registrationNumber)throws InvalidRegistrationNumberException{
		Pattern p = Pattern.compile("^(([A-Za-z]){2,3}(|-)(?:[0-9]){1,2}(|-)(?:[A-Za-z]){1,2}(|-)([0-9]){3,4})$");
		boolean match = p.matcher(registrationNumber).matches();
		if(!match) {
			throw new InvalidRegistrationNumberException("Entered car registration number is not a valid. Please enter again");
		}
		return match;
    }

}
