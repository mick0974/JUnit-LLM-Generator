// FontInfo.java
import java.awt.Font;
import java.io.Serializable;

public class FontInfo implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	public interface IPropertyNames {
		String FAMILY = "family";
		String IS_BOLD = "isBold";
		String IS_ITALIC = "isItalic";
		String SIZE = "size";
	}

	private static String DEFAULT_FAMILY = "Monospaced";

	private String _familyName;
	private boolean _isBold;
	private boolean _isItalic;
	private int _size;

	public FontInfo() {
		super();
		setFamily(DEFAULT_FAMILY);
		setSize(12);
	}

	public FontInfo(Font font) {
		super();
		if (font == null) {
			throw new IllegalArgumentException("Null Font passed");
		}
		setFont(font);
	}

	/**
	 * Return a copy of this object.
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new InternalError(ex.getMessage()); // Impossible.
		}
	}

	public String getFamily() {
		return _familyName;
	}

	public void setFamily(String value) {
		_familyName = value != null ? value : DEFAULT_FAMILY;
	}

	public boolean isBold() {
		return _isBold;
	}

	public void setIsBold(boolean value) {
		_isBold = value;
	}

	public boolean isItalic() {
		return _isItalic;
	}

	public void setIsItalic(boolean value) {
		_isItalic = value;
	}

	public int getSize() {
		return _size;
	}

	public void setSize(int value) {
		_size = value;
	}

	public void setFont(Font font) throws IllegalArgumentException {
		if (font == null) {
			throw new IllegalArgumentException("Null Font passed");
		}
		_familyName = font.getFamily();
		_isBold = font.isBold();
		_isItalic = font.isItalic();
		_size = font.getSize();
	}

	public boolean doesFontMatch(Font font) {
		if (font == null) {
			return false;
		}
		return font.getFamily().equals(_familyName)
				&& font.getSize() == getSize()
				&& font.getStyle() == generateStyle();
	}

	public int generateStyle() {
		int style = 0;
		if (!_isBold && !_isItalic) {
			style = Font.PLAIN;
		} else {
			if (_isBold) {
				style |= Font.BOLD;
			}
			if (_isItalic) {
				style |= Font.ITALIC;
			}
		}
		return style;
	}

	public Font createFont() {
		return new Font(_familyName, generateStyle(), _size);
	}

	// i18n ? What is this used for?
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(_familyName).append(", " + _size);
		if (_isBold) {
			buf.append(", bold");
		}
		if (_isItalic) {
			buf.append(", italic");
		}
		return buf.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((_familyName == null) ? 0 : _familyName.hashCode());
		result = PRIME * result + (_isBold ? 1231 : 1237);
		result = PRIME * result + (_isItalic ? 1231 : 1237);
		result = PRIME * result + _size;
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FontInfo other = (FontInfo) obj;
		if (_familyName == null) {
			if (other._familyName != null)
				return false;
		} else if (!_familyName.equals(other._familyName))
			return false;
		if (_isBold != other._isBold)
			return false;
		if (_isItalic != other._isItalic)
			return false;
		if (_size != other._size)
			return false;
		return true;
	}
}

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

// FontInfo_generateStyleTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public generateStyle() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_generateStyleTest {
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
	public void test_generateStyle_01() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_02() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_03() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_04() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.PLAIN, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_05() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_06() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_07() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_08() {
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.PLAIN, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_09() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		assertEquals(Font.BOLD | Font.ITALIC, _fontInfo.generateStyle());
	}

	@Test
	public void test_generateStyle_10() {
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		assertEquals(Font.BOLD, _fontInfo.generateStyle());
	}
}

// FontInfo_doesFontMatchTest.java
import org.junit.Before;
import org.junit.After;
import org