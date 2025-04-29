// FontInfo_isBoldTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isBold() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_isBoldTest {
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
	public void test_isBold_01() {
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_02() {
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_03() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_04() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_05() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_06() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_07() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_08() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_09() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsBold(true);
		assertTrue(_fontInfo.isBold());
	}

	@Test
	public void test_isBold_10() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsBold(false);
		assertFalse(_fontInfo.isBold());
	}
}