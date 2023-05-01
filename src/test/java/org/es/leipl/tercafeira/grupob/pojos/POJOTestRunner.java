package org.es.leipl.tercafeira.grupob.pojos;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 A class that runs the test suite for the POJO classes.
 It uses JUnitCore to execute the tests and prints the result of each test and if all tests were successful or not.
 */
public class POJOTestRunner {

    /**
     The main method runs the POJO test suite and outputs the test results to the console.
     @param args an array of command-line arguments for the program (not used in this case).
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(POJOTestSuite.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("All tests were successfull: " + result.wasSuccessful());
    }
}
