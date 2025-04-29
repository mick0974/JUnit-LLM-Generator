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

private FontInfo fontInfo;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testSetIsBold_True() {
        // Arrange
        boolean expectedValue = true;

        // Act
        fontInfo.setIsBold(expectedValue);

        // Assert
        assertTrue("Font should be bold", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_False() {
        // Arrange
        boolean expectedValue = false;

        // Act
        fontInfo.setIsBold(expectedValue);

        // Assert
        assertFalse("Font should not be bold", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_TrueAfterFalse() {
        // Arrange
        fontInfo.setIsBold(false);

        // Act
        fontInfo.setIsBold(true);

        // Assert
        assertTrue("Font should be bold after being set to true", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_FalseAfterTrue() {
        // Arrange
        fontInfo.setIsBold(true);

        // Act
        fontInfo.setIsBold(false);

        // Assert
        assertFalse("Font should not be bold after being set to false", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_DoesNotAffectItalic() {
        // Arrange
        fontInfo.setIsItalic(true);

        // Act
        fontInfo.setIsBold(true);

        // Assert
        assertTrue("Font should remain italic", fontInfo.isItalic());
    }

    @Test
    public void testSetIsBold_DefaultIsNotBold() {
        // Assert
        assertFalse("Default font should not be bold", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_CloneEffect() throws CloneNotSupportedException {
        // Arrange
        fontInfo.setIsBold(true);

        // Act
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();

        // Assert
        assertTrue("Cloned font should be bold", clonedFontInfo.isBold());
        assertTrue("Original font should remain bold", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_WithFontSettingBold() {
        // Arrange
        Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        fontInfo.setFont(boldFont);

        // Act
        fontInfo.setIsBold(false);

        // Assert
        assertFalse("Font should not be bold after setting isBold to false", fontInfo.isBold());
        assertFalse("Font does not match boldFont after setting isBold to false", fontInfo.doesFontMatch(boldFont));
    }

    @Test
    public void testSetIsBold_WithFontResettingStyle() {
        // Arrange
        Font boldItalicFont = new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 12);
        fontInfo.setFont(boldItalicFont);

        // Act
        fontInfo.setIsBold(false);

        // Assert
        Font expectedFont = new Font(Font.MONOSPACED, Font.ITALIC, 12);
        assertEquals("Font should match expected italic font", expectedFont, fontInfo.createFont());
    }

    @Test
    public void testSetIsBold_CheckHashCodeIntegrity() {
        // Arrange
        fontInfo.setIsBold(false);
        int initialHashCode = fontInfo.hashCode();

        // Act
        fontInfo.setIsBold(true);
        int alteredHashCode = fontInfo.hashCode();

        // Assert
        assertNotEquals("Hash codes should differ when the bold property changes", initialHashCode, alteredHashCode);
    }
}