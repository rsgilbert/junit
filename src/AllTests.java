import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Run many test cases in a single suite.
 * A test suite is a group of tests
 * A runner runs test suites
 */
@RunWith(value= Suite.class)
@Suite.SuiteClasses(value={CalculatorTest.class, ParameterizedTest.class})
public class AllTests {
}
