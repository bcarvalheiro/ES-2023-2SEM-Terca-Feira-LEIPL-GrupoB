package org.es.leipl.tercafeira.grupob.pojos;

import org.es.leipl.tercafeira.grupob.pojos.*;
import org.es.leipl.tercafeira.grupob.tools.ImportFilesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 This class represents the test suite for the POJO classes. It uses JUnit's @RunWith annotation to indicate that it
 is a suite, and @Suite.SuiteClasses annotation to specify the test classes to be included in the suite.
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
        SalaTest.class,
        UCTest.class,
        TurnoTest.class,
        AulaTest.class,
        BlocoTest.class,
        HorarioTest.class,
        ImportFilesTest.class


})
/**
 This class only serves as a container for the @SuiteClasses annotation and therefore has no implementation.
*/
public class POJOTestSuite {


}
