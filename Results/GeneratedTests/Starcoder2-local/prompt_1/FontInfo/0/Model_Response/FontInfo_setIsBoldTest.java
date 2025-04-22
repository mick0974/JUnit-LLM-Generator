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
	public void test_01_null_value() {
		_fontInfo.setIsBold(null);
		assertFalse(_fontInfo.isBold());
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
	public void test_04_true_value_twice() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_05_false_value_twice() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_06_true_value_then_false() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_07_false_value_then_true() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_08_true_value_then_true() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		assertTrue(_fontInfo.isItalic());
	}

	@Test
	public void test_09_false_value_then_false() {
		_fontInfo.setIsItalic(false);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}

	@Test
	public void test_10_true_value_then_true_then_false() {
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(true);
		_fontInfo.setIsItalic(false);
		assertFalse(_fontInfo.isItalic());
	}
}

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
	public void test_01_null_value() {
		_fontInfo.setFamily(null);
		assertEquals(_fontInfo.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_02_empty_value() {
		_fontInfo.setFamily("");
		assertEquals(_fontInfo.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_03_valid_value() {
		_fontInfo.setFamily("Monospaced");
		assertEquals(_fontInfo.getFamily(), "Monospaced");
	}

	@Test
	public void test_04_valid_value_twice() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Monospaced");
		assertEquals(_fontInfo.getFamily(), "Monospaced");
	}

	@Test
	public void test_05_valid_value_then_null() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily(null);
		assertEquals(_fontInfo.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_06_valid_value_then_empty() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("");
		assertEquals(_fontInfo.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_07_valid_value_then_valid() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Serif");
		assertEquals(_fontInfo.getFamily(), "Serif");
	}

	@Test
	public void test_08_valid_value_then_valid_twice() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Serif");
		_fontInfo.setFamily("Serif");
		assertEquals(_fontInfo.getFamily(), "Serif");
	}

	@Test
	public void test_09_valid_value_then_valid_then_null() {
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Serif");
		_fontInfo.setFamily(null