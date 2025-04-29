// FontInfo_getSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public getSize() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_getSizeTest {
	private FontInfo fixture;

	@Before
	public void setUp() {
		fixture = new FontInfo(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
	}

	@After
	public void tearDown() {
		fixture = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetFontSizeWithNullFont() {
		new FontInfo(null);
	}

	@Test
	public void testGetFontSizeWithDefaultValues() {
		FontInfo info = new FontInfo();
		info.setSize(12);
		assertEquals(12, info.getSize());
	}

	@Test
	public void testGetFontSizeWithNonDefaultValues() {
		FontInfo info = new FontInfo();
		info.setSize(24);
		assertEquals(24, info.getSize());
	}

	@Test
	public void testGetFontSizeWithFontObject() {
		FontInfo info = new FontInfo(new Font("Times New Roman", Font.PLAIN, 16));
		assertEquals(16, info.getSize());
	}

	@Test
	public void testGetFontSizeWithBoldFont() {
		FontInfo info = new FontInfo(new Font("Courier New", Font.BOLD, 18));
		assertEquals(18, info.getSize());
	}

	@Test
	public void testGetFontSizeWithItalicFont() {
		FontInfo info = new FontInfo(new Font("Verdana", Font.ITALIC, 20));
		assertEquals(20, info.getSize());
	}

	@Test
	public void testGetFontSizeWithBoldAndItalicFont() {
		FontInfo info = new FontInfo(new Font("Georgia", Font.BOLD | Font.ITALIC, 22));
		assertEquals(22, info.getSize());
	}

	@Test
	public void testGetFontSizeWithZeroSize() {
		FontInfo info = new FontInfo();
		info.setSize(0);
		assertEquals(0, info.getSize());
	}

	@Test
	public void testGetFontSizeWithNegativeSize() {
		FontInfo info = new FontInfo();
		info.setSize(-5);
		assertEquals(-5, info.getSize());
	}
}