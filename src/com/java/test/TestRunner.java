package com.java.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void test() {
      Result result = JUnitCore.runClasses(TestParkingOperations.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println("failed test : "+ failure.toString());
      }
      System.out.println("All test cases run without failure : "+ result.wasSuccessful()+"\n");
   }
}