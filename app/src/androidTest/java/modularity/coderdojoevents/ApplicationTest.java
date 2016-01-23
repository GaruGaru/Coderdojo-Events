package modularity.coderdojoevents;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestResult;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() throws Exception {
        super(Application.class);
        createApplication();
        setUp();
    }


}