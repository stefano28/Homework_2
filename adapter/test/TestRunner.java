package adapter.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Class for running test case classes
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SetAdapterTest.class, MapAdapterTest.class);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        System.out.println(result.wasSuccessful());
    }
}
