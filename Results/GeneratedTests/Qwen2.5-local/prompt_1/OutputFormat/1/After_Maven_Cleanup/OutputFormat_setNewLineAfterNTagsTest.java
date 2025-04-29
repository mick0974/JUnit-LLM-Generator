// OutputFormat_setNewLineAfterNTagsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setNewLineAfterNTags(int tagCount) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setNewLineAfterNTagsTest {
	private OutputFormat outputFormat;

	@Before
	public void setUp() {
		outputFormat = new OutputFormat();
	}

	@After
	public void tearDown() {
		outputFormat = null;
	}

	@Test
	public void testSetNewLineAfterNTagsWithValidTagCount() {
		int tagCount = 10;
		outputFormat.setNewLineAfterNTags(tagCount);
		assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test
	public void testSetNewLineAfterNTagsWithZeroTagCount() {
		int tagCount = 0;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNewLineAfterNTagsWithNegativeTagCount() {
		int tagCount = -5;
		outputFormat.setNewLineAfterNTags(tagCount);
	}

	@Test
	public void testSetNewLineAfterNTagsWithDefaultTagCount() {
		int tagCount = 0;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test
	public void testSetNewLineAfterNTagsWithNullTagCount() {
		int tagCount = Integer.MAX_VALUE;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test
	public void testSetNewLineAfterNTagsWithOneTagCount() {
		int tagCount = 1;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test
	public void testSetNewLineAfterNTagsWithLargeTagCount() {
		int tagCount = Integer.MAX_VALUE;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test
	public void testSetNewLineAfterNTagsWithTwoTagCount() {
		int tagCount = 2;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}

	@Test
	public void testSetNewLineAfterNTagsWithThreeTagCount() {
		int tagCount = 3;
		outputFormat.setNewLineAfterNTags(tagCount);
	 assertEquals(tagCount, outputFormat.getNewLineAfterNTags());
	}
}