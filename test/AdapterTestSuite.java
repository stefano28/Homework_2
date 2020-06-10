package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SetAdapterTest.class, ListAdapterTest.class, ListIteratorTest.class, ListAdapterSublistTest.class, MapAdapterTest.class
})

/**
 * @safe.testsuiteexecutionrecords Questa suite è basata su JUnit 4.12, affinchè si possa garantire il corretto funzionamento dei test bisogna aggiungere all classpath
 * le dipendenze da hamcrest-core-1.3.jar e junit-4.12.jar
 * @safe.executionvariables Bisogna indicare nel classpath le posizioni di hamcrest-core-1.3.jar e junit-4.12.jar
 */
public class AdapterTestSuite {

}
