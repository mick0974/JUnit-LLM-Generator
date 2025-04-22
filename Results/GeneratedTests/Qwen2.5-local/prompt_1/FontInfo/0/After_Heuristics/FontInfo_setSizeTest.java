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
	private FontInfo fixture;

	@Before
	public void setUp() {
		fixture = new FontInfo();
	}

	@After
	public void tearDown() {
		fixture = null;
	}

	@Test
	public void testSetSize_withValidPositiveNumber() {
		fixture.setSize(15);
		assertEquals(15, fixture.getSize());
	}

	@Test
	public void testSetSize_withZero() {
		fixture.setSize(0);
		assertEquals(0, fixture.getSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSize_withNegativeNumber() {
		fixture.setSize(-1);
	}

	@Test
	public void testSetSize_withMaxValue() {
		fixture.setSize(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, fixture.getSize());
	}

	@Test
	public void testSetSize_withMinValue() {
		fixture.setSize(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, fixture.getSize());
	}

	@Test
	public void testSetSize_withSameValue() {
		int originalSize = fixture.getSize();
		fixture.setSize(originalSize);
		assertEquals(originalSize, fixture.getSize());
	}

	@Test
	public void testSetSize_withDifferentValue() {
		int originalSize = fixture.getSize();
		fixture.setSize(originalSize + 1);
		assertNotEquals(originalSize, fixture.getSize());
	}

	@Test
	public void testSetSize_withDefaultFamily() {
		fixture.setFamily(null); // Should default to "Monospaced"
		fixture.setSize(15);
	 assertEquals("Monospaced", fixture.getFamily());
		assertEquals(15, fixture.getSize());
	}

	@Test
	public void testSetSize_withNullFamily() {
		fixture.setFamily(null);
		fixture.setSize(15);
		assertEquals("Monospaced", fixture.getFamily());
		assertEquals(15, fixture.getSize());
	}

	@Test
	public void testSetSize_withEmptyFamily() {
		fixture.setFamily("");
		fixture.setSize(15);
		assertEquals("", fixture.getFamily());
		assertEquals(15, fixture.getSize());
	}
}