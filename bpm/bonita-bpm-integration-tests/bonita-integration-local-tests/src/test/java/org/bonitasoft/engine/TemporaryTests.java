package org.bonitasoft.engine;

import org.bonitasoft.engine.identity.OrganizationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        OrganizationTest.class
})
public class TemporaryTests extends LocalIntegrationTests {

}
