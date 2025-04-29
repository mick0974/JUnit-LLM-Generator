// FontInfo_equalsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_equalsTest {
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










}