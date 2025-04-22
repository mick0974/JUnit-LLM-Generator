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
	public void test_04_clone_is_same() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		assertEquals(clone.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_05_clone_is_not_same() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		assertNotEquals(clone.getFamily(), "Monospaced");
	}

	@Test
	public void test_06_clone_is_same_after_set_family() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Monospaced");
		assertEquals(clone.getFamily(), "Monospaced");
	}

	@Test
	public void test_07_clone_is_not_same_after_set_family() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Monospaced");
		assertNotEquals(clone.getFamily(), "Monospaced");
	}

	@Test
	public void test_08_clone_is_same_after_set_family_to_default() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily(FontInfo.DEFAULT_FAMILY);
		assertEquals(clone.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_09_clone_is_not_same_after_set_family_to_default() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily(FontInfo.DEFAULT_FAMILY);
		assertNotEquals(clone.getFamily(), FontInfo.DEFAULT_FAMILY);
	}

	@Test
	public void test_10_clone_is_same_after_set_family_to_valid_value() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Monospaced");
		assertEquals(clone.getFamily(), "Monospaced");
	}

	@Test
	public void test_11_clone_is_not_same_after_set_family_to_valid_value() {
		FontInfo clone = (FontInfo) _fontInfo.clone();
		_fontInfo.setFamily("Monospaced");
		_fontInfo.setFamily("Monospaced");
		assertNotEquals(clone.getFamily(), "Monospaced");
	}
}

// FontInfo_setFontTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setFont(Font font) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setFontTest {
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
		try {
			_fontInfo.setFont(null);
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException ex) {
			// expected
		}
	}

	@Test
	