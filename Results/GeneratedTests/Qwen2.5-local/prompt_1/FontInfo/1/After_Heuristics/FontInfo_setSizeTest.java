// FontInfo_setSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setSize(int value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setSizeTest {
	private FontInfo fontInfo;

	@Before
	public void setUp() {
		fontInfo = new FontInfo();
	}

	@After
	public void tearDown() {
		fontInfo = null;
	}

	@Test
	public void testSetSize_withValidValue() {
		fontInfo.setSize(14);
		assertEquals(14, fontInfo.getSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSize_withNullFont() {
		fontInfo.setFont(null);
	}

	@Test
	public void testSetSize_withDefaultFamily() {
		fontInfo.setFamily(null);
		fontInfo.setSize(16);
		assertEquals(16, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withBoldAndItalic() {
		fontInfo.setIsBold(true);
		fontInfo.setIsItalic(true);
		fontInfo.setSize(20);
		assertEquals(20, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withEmptyStringFamily() {
		fontInfo.setFamily("");
		fontInfo.setSize(18);
		assertEquals(18, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withNegativeValue() {
		fontInfo.setSize(-5);
		assertEquals(12, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withZeroValue() {
		fontInfo.setSize(0);
		assertEquals(12, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withLargePositiveValue() {
		fontInfo.setSize(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withSmallPositiveValue() {
		fontInfo.setSize(Integer.MIN_VALUE);
		assertEquals(12, fontInfo.getSize());
	}

	@Test
	public void testSetSize_withSameValue() {
		fontInfo.setSize(12);
		fontInfo.setSize(12);
		assertEquals(12, fontInfo.getSize());
	}
}