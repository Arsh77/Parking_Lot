package com.java.main.interfaces;

import com.java.main.exception.TransactionFailedException;

public interface Payment {
	
	public abstract float calculateCost(float hours);
	
	public abstract boolean recievedPayment(float cost);
	
	public abstract void paymentfailed() throws TransactionFailedException;
}
