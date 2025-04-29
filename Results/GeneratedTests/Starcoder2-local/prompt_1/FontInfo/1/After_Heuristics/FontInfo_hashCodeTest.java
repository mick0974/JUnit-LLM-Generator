// FontInfo_hashCodeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public hashCode() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_hashCodeTest {
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
	public void testHashCode_1() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		int expected = 1237;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_2() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_3() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setIsItalic(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_4() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		int expected = 1237;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_5() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_6() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsItalic(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_7() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_8() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		_fontInfo.setSize(12);
		int expected = 1237;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_9() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}

	@Test
	public void testHashCode_10() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		_fontInfo.setSize(12);
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		int expected = 1231;
		int actual = _fontInfo.hashCode();
		assertEquals(expected, actual);
	}
}