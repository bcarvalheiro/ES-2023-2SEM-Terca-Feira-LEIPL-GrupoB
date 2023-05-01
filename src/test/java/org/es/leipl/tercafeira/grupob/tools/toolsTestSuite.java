package org.es.leipl.tercafeira.grupob.tools;

import org.es.leipl.tercafeira.grupob.pojos.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 This test suite is used to run multiple test classes at once.
 The classes included in the suite are:
 CsvUtilsTest
 ImportFilesTest
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        CsvUtilsTest.class,
        ImportFilesTest.class


})

public class toolsTestSuite {


}
