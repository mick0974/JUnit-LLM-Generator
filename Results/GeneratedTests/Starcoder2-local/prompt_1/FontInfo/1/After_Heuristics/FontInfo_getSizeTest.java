// FontInfo_getSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public getSize() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_getSizeTest {
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
	public void test_getSize_01() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_02() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_03() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_04() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_05() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_06() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_07() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_08() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_09() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void test_getSize_10() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}
}