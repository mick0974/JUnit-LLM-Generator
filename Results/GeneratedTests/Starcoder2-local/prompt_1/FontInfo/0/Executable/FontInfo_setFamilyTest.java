// FontInfo_setFamilyTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setFamily(String value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setFamilyTest {
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
	public void testSetFamily_1() {
		_fontInfo.setFamily("Monospaced");
		assertEquals("Monospaced", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_2() {
		_fontInfo.setFamily("Serif");
		assertEquals("Serif", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_3() {
		_fontInfo.setFamily("SansSerif");
		assertEquals("SansSerif", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_4() {
		_fontInfo.setFamily("Dialog");
		assertEquals("Dialog", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_5() {
		_fontInfo.setFamily("DialogInput");
		assertEquals("DialogInput", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_6() {
		_fontInfo.setFamily("DialogInput");
		assertEquals("DialogInput", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_7() {
		_fontInfo.setFamily("Dialog");
		assertEquals("Dialog", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_8() {
		_fontInfo.setFamily("Dialog");
		assertEquals("Dialog", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_9() {
		_fontInfo.setFamily("Dialog");
		assertEquals("Dialog", _fontInfo.getFamily());
	}

	@Test
	public void testSetFamily_10() {
		_fontInfo.setFamily("Dialog");
		assertEquals("Dialog", _fontInfo.getFamily());
	}
}