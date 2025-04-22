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
	private FontInfo fixture;

	@Before
	public void setUp() {
		fixture = new FontInfo();
	}

	@After
	public void tearDown() {
		fixture = null;
	}

	@Test
	public void testSetSize_withValidPositiveNumber() {
		fixture.setSize(15);
		assertEquals(15, fixture.getSize());
	}

	@Test
	public void testSetSize_withZero() {
		fixture.setSize(0);
		assertEquals(0, fixture.getSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSize_withNegativeNumber() {
		fixture.setSize(-1);
	}

	@Test
	public void testSetSize_withMaxValue() {
		fixture.setSize(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, fixture.getSize());
	}

	@Test
	public void testSetSize_withMinValue() {
		fixture.setSize(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, fixture.getSize());
	}

	@Test
	public void testSetSize_withSameValue() {
		int originalSize = fixture.getSize();
		fixture.setSize(originalSize);
		assertEquals(originalSize, fixture.getSize());
	}

	@Test
	public void testSetSize_withDifferentValue() {
		int originalSize = fixture.getSize();
		fixture.setSize(originalSize + 1);
		assertNotEquals(originalSize, fixture.getSize());
	}

	@Test
	public void testSetSize_withDefaultFamily() {
		fixture.setFamily(null); // Should default to "Monospaced"
		fixture.setSize(15);
	 assertEquals("Monospaced", fixture.getFamily());
		assertEquals(15, fixture.getSize());
	}

	@Test
	public void testSetSize_withNullFamily() {
		fixture.setFamily(null);
		fixture.setSize(15);
		assertEquals("Monospaced", fixture.getFamily());
		assertEquals(15, fixture.getSize());
	}

	@Test
	public void testSetSize_withEmptyFamily() {
		fixture.setFamily("");
		fixture.setSize(15);
		assertEquals("", fixture.getFamily());
		assertEquals(15, fixture.getSize());
	}
}