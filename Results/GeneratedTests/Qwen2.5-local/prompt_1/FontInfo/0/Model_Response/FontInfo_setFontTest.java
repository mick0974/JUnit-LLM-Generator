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
 * FontInfo#public setFont(Font font) throws IllegalArgumentException  method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setFontTest {
    private FontInfo fontInfo;
    private Font font;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
        font = new Font("Serif", Font.BOLD, 14);
    }

    @After
    public void tearDown() {
        fontInfo = null;
        font = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetFont_nullFont() {
        fontInfo.setFont(null);
    }

    @Test
    public void testSetFont_validFont() {
        fontInfo.setFont(font);
        assertEquals("Serif", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertEquals(14, fontInfo.getSize());
    }

    @Test
    public void testSetFont_sameFont() {
        fontInfo.setFont(font);
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();
        assertNotSame(clonedFontInfo, fontInfo);
        assertEquals(fontInfo, clonedFontInfo);
    }
    
    @Test
    public void testSetFont_differentFont() {
        Font anotherFont = new Font("SansSerif", Font.ITALIC, 16);
        fontInfo.setFont(anotherFont);
        assertEquals("SansSerif", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(16, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithNoStyle() {
        Font noStyleFont = new Font("Monospaced", Font.PLAIN, 12);
        fontInfo.setFont(noStyleFont);
        assertEquals("Monospaced", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(12, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithAllStyles() {
        Font allStylesFont = new Font("Dialog", Font.BOLD | Font.ITALIC, 18);
        fontInfo.setFont(allStylesFont);
        assertEquals("Dialog", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(18, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithMixedStyles() {
        Font mixedStylesFont = new Font("Lucida Sans Unicode", Font.BOLD | Font.ITALIC | Font.ROMAN_BASED, 20);
        fontInfo.setFont(mixedStylesFont);
        assertEquals("Lucida Sans Unicode", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(20, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithUnknownFamily() {
        Font unknownFamilyFont = new Font("NonExistentFont", Font.PLAIN, 10);
        fontInfo.setFont(unknownFamilyFont);
        assertEquals("NonExistentFont", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(10, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithLargeSize() {
        Font largeSizeFont = new Font("Arial", Font.PLAIN, 50);
        fontInfo.setFont(largeSizeFont);
        assertEquals("Arial", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(50, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithSmallSize() {
        Font smallSizeFont = new Font("Verdana", Font.PLAIN, 8);
        fontInfo.setFont(smallSizeFont);
        assertEquals("Verdana", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(8, fontInfo.getSize());
    }
}