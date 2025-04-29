// FontInfo_setSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setSize(int value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setSizeTest {
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
	public void testSetSize_01() {
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_02() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(12);
		assertEquals(12, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_03() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(13);
		assertEquals(13, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_04() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(14);
		assertEquals(14, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_05() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(15);
		assertEquals(15, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_06() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(16);
		assertEquals(16, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_07() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(17);
		assertEquals(17, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_08() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(18);
		assertEquals(18, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_09() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(19);
		assertEquals(19, _fontInfo.getSize());
	}

	@Test
	public void testSetSize_10() {
		_fontInfo.setSize(12);
		_fontInfo.setSize(20);
		assertEquals(20, _fontInfo.getSize());
	}
}