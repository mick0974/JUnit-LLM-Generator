// FontInfo_toStringTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_toStringTest {
	private FontInfo _fontInfo;

	@Before
	public void setUp() {
		_fontInfo = new FontInfo();
	}

	@After
	public void tearDown() {
		_fontInfo = null;
	}

	@Test
	public void testToString_1() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		String expected = "Monospaced, 12";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_2() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		String expected = "Monospaced, 12, bold";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_3() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		String expected = "Monospaced, 12, italic";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_4() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		String expected = "Monospaced, 12, bold, italic";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_5() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		String expected = "Monospaced, 12, bold";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_6() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		String expected = "Monospaced, 12, italic";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_7() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		String expected = "Monospaced, 12, bold, italic";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_8() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		String expected = "Monospaced, 12";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_9() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		String expected = "Monospaced, 12, bold";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testToString_10() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		String expected = "Monospaced, 12, italic";
		String actual = _fontInfo.toString();
		assertEquals(expected, actual);
	}
}