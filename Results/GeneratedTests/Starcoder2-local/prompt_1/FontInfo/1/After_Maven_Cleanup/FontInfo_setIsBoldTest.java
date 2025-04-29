// FontInfo_setIsBoldTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setIsBold(boolean value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setIsBoldTest {
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
	public void test_02_false_value() {
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_03_true_value() {
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_04_true_value_twice() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_05_false_value_twice() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_06_true_value_then_false() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_07_false_value_then_true() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_08_true_value_then_true() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_09_false_value_then_false() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_10_true_value_then_true_then_false() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}
}