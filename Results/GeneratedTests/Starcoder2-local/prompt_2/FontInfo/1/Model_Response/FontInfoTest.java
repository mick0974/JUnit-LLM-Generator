Generate unit tests in Java for FontInfo to achieve
100% line coverage for this class.
Dont use @Before and @After test methods.
Make tests as atomic as possible.
All tests should be for JUnit 4.
Name all methods according to the template -
[MethodUnderTest][Scenario]Test, and use only
English letters.
The source code of class under test is as follows:
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


The test class is as follows:
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Font;

import org.junit.Test;

public class FontInfoTest {

	@Test
	public void testFontInfo() {
		FontInfo fontInfo = new FontInfo();
		assertEquals(FontInfo.DEFAULT_FAMILY, fontInfo.getFamily());
		assertEquals(12, fontInfo.getSize());
		assertFalse(fontInfo.isBold());
		assertFalse(fontInfo.isItalic());
	}

	@Test
	public void testFontInfoFont() {
		Font font = new Font("Arial", Font.BOLD, 12);
		FontInfo fontInfo = new FontInfo(font);
		assertEquals(font.getFamily(), fontInfo.getFamily());
		assertEquals(font.getSize(), fontInfo.getSize());
		assertEquals(font.getStyle(), fontInfo.generateStyle());
		assertTrue(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testSetFont() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 12);
		fontInfo.setFont(font);
		assertEquals(font.getFamily(), fontInfo.getFamily());
		assertEquals(font.getSize(), fontInfo.getSize());
		assertEquals(font.getStyle(), fontInfo.generateStyle());
		assertTrue(fontInfo.doesFontMatch(font));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetFontNull() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setFont(null);
	}

	@Test
	public void testDoesFontMatch() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 12);
		assertTrue(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testDoesFontMatchNull() {
		FontInfo fontInfo = new FontInfo();
		assertFalse(fontInfo.doesFontMatch(null));
	}

	@Test
	public void testDoesFontMatchDifferentFamily() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Courier", Font.BOLD, 12);
		assertFalse(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testDoesFontMatchDifferentSize() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 10);
		assertFalse(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testDoesFontMatchDifferentStyle() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		assertFalse(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testCreateFont() {
		FontInfo fontInfo = new FontInfo();
		Font font = fontInfo.createFont();
		assertEquals(fontInfo.getFamily(), font.getFamily());
		assertEquals(fontInfo.getSize(), font.getSize());
		assertEquals(fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFontNull() {
		FontInfo fontInfo = new FontInfo();
		Font font = fontInfo.createFont();
		assertEquals(fontInfo.getFamily(), font.getFamily());
		assertEquals(fontInfo.getSize(), font.getSize());
		assertEquals(fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testToString() {
		FontInfo fontInfo = new FontInfo();
		assertEquals("Monospaced, 12", fontInfo.toString());
		fontInfo.setFamily("Arial");
		assertEquals("Arial, 12", fontInfo.toString());
		fontInfo.setIsBold(true);
		assertEquals("Arial, 12, bold", fontInfo.toString());
		fontInfo.setIsItalic(true);
		assertEquals("Arial, 12, bold, italic", fontInfo.toString());
	}

	@Test
	public void testHashCode() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 12);
		fontInfo.setFont(font);
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(