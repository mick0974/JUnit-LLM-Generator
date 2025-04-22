// FontInfo_isItalicTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isItalic() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_isItalicTest {
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
	public void test_isItalic_01() {
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_02() {
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_03() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_04() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_05() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_06() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_07() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_08() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_09() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_isItalic_10() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}
}