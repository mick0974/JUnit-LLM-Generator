// OutputFormat_setIndentTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setIndent(String indent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setIndentTest {
	private OutputFormat format;

	@Before
	public void setUp() {
		format = new OutputFormat();
	}

	@After
	public void tearDown() {
		format = null;
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = null.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_1() throws Exception {
		format.setIndent(null);
		assertEquals(null, format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_2() throws Exception {
		format.setIndent("");
		assertEquals(null, format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = " ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_3() throws Exception {
		format.setIndent(" ");
		assertEquals(null, format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "  ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_4() throws Exception {
		format.setIndent("  ");
		assertEquals("  ", format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "   ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_5() throws Exception {
		format.setIndent("   ");
		assertEquals("   ", format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "    ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_6() throws Exception {
		format.setIndent("    ");
		assertEquals("    ", format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "     ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_7() throws Exception {
		format.setIndent("     ");
		assertEquals("     ", format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "      ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_8() throws Exception {
		format.setIndent("      ");
		assertEquals("      ", format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "       ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_9() throws Exception {
		format.setIndent("       ");
		assertEquals("       ", format.getIndent());
	}

	/**
	 * Test the OutputFormat#public setIndent(String indent) method with
	 * indent = "        ".
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetIndent_10() throws Exception {
		format.setIndent("        ");
		assertEquals("        ", format.getIndent());
	}
}