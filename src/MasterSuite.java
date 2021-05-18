import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Run many suites at once
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value={AllTests.class, ParameterizedTest.class})
public class MasterSuite {
}
