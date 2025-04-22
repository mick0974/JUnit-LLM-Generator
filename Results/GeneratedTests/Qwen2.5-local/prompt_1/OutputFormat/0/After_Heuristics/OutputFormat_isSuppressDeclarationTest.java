// OutputFormat_isSuppressDeclarationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isSuppressDeclaration() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isSuppressDeclarationTest {
	@Before
	public void setUp() {
		outputFormat = new OutputFormat();
	}

	@After
	public void tearDown() {
		outputFormat = null;
	}

	@Test
	public void testIsSuppressDeclaration_initiallyFalse() {
		assertFalse(outputFormat.isSuppressDeclaration());
	}

	@Test
	public void testIsSuppressDeclaration_setToTrue() {
		outputFormat.setSuppressDeclaration(true);
		assertTrue(outputFormat.isSuppressDeclaration());
	}

	@Test
	public void testIsSuppressDeclaration_setToFalse() {
		outputFormat.setSuppressDeclaration(false);
		assertFalse(outputFormat.isSuppressDeclaration());
	}

	@Test
	public void testIsSuppressDeclaration_toggleTwice() {
		outputFormat.setSuppressDeclaration(true);
		outputFormat.setSuppressDeclaration(false);
		assertFalse(outputFormat.isSuppressDeclaration());
	}

	@Test
	public void testIsSuppressDeclaration_toggleManyTimes() {
		for (int i = 0; i < 10; i++) {
			outputFormat.setSuppressDeclaration(i % 2 == 0);
		}

		boolean expectedValue = outputFormat.isSuppressDeclaration();
		for (int i = 0; i < 10; i++) {
			outputFormat.setSuppressDeclaration(i % 2 == 0);
			assertEquals(expectedValue, outputFormat.isSuppressDeclaration());
		}
	}

	@Test
	public void testIsSuppressDeclaration_parseOptions() {
		String[] args = {"-suppressDeclaration"};
		int resultIndex = outputFormat.parseOptions(args, 0);
		assertTrue(resultIndex == args.length);
		assertTrue(outputFormat.isSuppressDeclaration());
	}

	@Test
	public void testIsSuppressDeclaration_parseOptions_withOtherArgs() {
		String[] args = {"-otherOption", "-suppressDeclaration", "-anotherOption"};
		int resultIndex = outputFormat.parseOptions(args, 1);
		assertTrue(resultIndex == args.length);
		assertTrue(outputFormat.isSuppressDeclaration());
	}

	@Test
	public void testIsSuppressDeclaration_parseOptions_noMatch() {
		String[] args = {"-someOtherOption"};
		int resultIndex = outputFormat.parseOptions(args, 0);
		assertTrue(resultIndex == 1);
}
}