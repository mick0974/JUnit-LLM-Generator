// FontInfo_generateStyleTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public generateStyle() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_generateStyleTest {
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
	public void testGenerateStyle_01() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.PLAIN, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_02() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_03() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_04() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_05() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_06() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_07() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_08() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.PLAIN, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_09() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_10() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}
}