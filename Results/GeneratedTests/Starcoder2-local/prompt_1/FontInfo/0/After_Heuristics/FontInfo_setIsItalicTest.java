// FontInfo_setIsItalicTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setIsItalic(boolean value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setIsItalicTest {
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
	public void test_01_null_value() {
		_fontInfo.setIsItalic(null);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_02_false_value() {
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_03_true_value() {
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_04_clone_is_same() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		assertFalse(clone.isItalic());
	}

	@Test
	public void test_05_clone_is_not_same() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		assertTrue(clone.isItalic());
	}

	@Test
	public void test_06_clone_is_same_after_set_is_italic() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertFalse(clone.isItalic());
	}

	@Test
	public void test_07_clone_is_not_same_after_set_is_italic() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertTrue(clone.isItalic());
	}

	@Test
	public void test_08_clone_is_same_after_set_is_italic_to_true() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertTrue(clone.isItalic());
	}

	@Test
	public void test_09_clone_is_not_same_after_set_is_italic_to_true() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertFalse(clone.isItalic());
	}

	@Test
	public void test_10_clone_is_same_after_set_is_italic_to_false() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertFalse(clone.isItalic());
	}

	@Test
	public void test_11_clone_is_not_same_after_set_is_italic_to_false() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertTrue(clone.isItalic());
	}
}