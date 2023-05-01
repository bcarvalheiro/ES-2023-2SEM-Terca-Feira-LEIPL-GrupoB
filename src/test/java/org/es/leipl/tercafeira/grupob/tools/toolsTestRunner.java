package org.es.leipl.tercafeira.grupob.tools;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 A test runner class for running the test suite "toolsTestSuite".
 */
public class toolsTestRunner {
    /**
     Main method that runs the test suite and outputs the results to the console.
     @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(toolsTestSuite.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("All tests were successfull: " + result.wasSuccessful());
    }
}
