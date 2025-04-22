// FontInfo_doesFontMatchTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public doesFontMatch(Font font) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_doesFontMatchTest {
	private FontInfo _fontInfo;
	private Font _font;

	@Before
	public void setUp() {
		_fontInfo = new FontInfo();
		_font = new Font(_fontInfo.getFamily(), _fontInfo.generateStyle(), _fontInfo.getSize());
	}

	@After
	public void tearDown() {
		_fontInfo = null;
		_font = null;
	}

	@Test
	public void testDoesFontMatch_1() {
		_fontInfo.setFont(_font);
		assertTrue(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_2() {
		_fontInfo.setFont(_font);
		_fontInfo.setFamily("Arial");
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_3() {
		_fontInfo.setFont(_font);
		_fontInfo.setSize(10);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_4() {
		_fontInfo.setFont(_font);
		_fontInfo.setIsBold(true);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_5() {
		_fontInfo.setFont(_font);
		_fontInfo.setIsItalic(true);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_6() {
		_fontInfo.setFont(_font);
		_fontInfo.setFamily("Arial");
		_fontInfo.setIsBold(true);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_7() {
		_fontInfo.setFont(_font);
		_fontInfo.setFamily("Arial");
		_fontInfo.setIsItalic(true);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_8() {
		_fontInfo.setFont(_font);
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(10);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

	@Test
	public void testDoesFontMatch_9() {
		_fontInfo.setFont(_font);
		_fontInfo.setFamily("Arial");
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		_fontInfo.setSize(10);
		assertFalse(_fontInfo.doesFontMatch(_font));
	}

}