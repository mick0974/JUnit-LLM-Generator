// FontInfo_setFontTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setFont(Font font) throws IllegalArgumentException  method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setFontTest {
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
	public void testSetFont_1() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_2() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_3() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_4() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_5() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_6() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_7() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_8() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_9() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_10() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_11() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_12() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_13() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_14() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_15() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_16() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_17() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_18() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_19() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_20() {
		Font font = new Font("Arial", Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_21() {
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void testSetFont_22() {
		Font font = new Font("Arial", Font.BOLD, 12);
		_fontInfo.setFont(font);
		assertEquals("Arial", _fontInfo.getFamily());
		assertEquals(12, _fontInfo.getSize());
	
}
}