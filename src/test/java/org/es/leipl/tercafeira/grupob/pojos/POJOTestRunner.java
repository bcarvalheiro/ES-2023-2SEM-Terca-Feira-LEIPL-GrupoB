package org.es.leipl.tercafeira.grupob.pojos;

import org.es.leipl.tercafeira.grupob.pojos.POJOTestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class POJOTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(POJOTestSuite.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("All tests were successfull: " + result.wasSuccessful());
    }
}
