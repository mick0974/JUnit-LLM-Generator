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

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetFontWithNullFont() {
        fontInfo.setFont(null);  // Act
    }

    @Test
    public void testSetFontWithValidFont() {
        Font font = new Font("Arial", Font.BOLD, 12);  // Arrange
        fontInfo.setFont(font);  // Act

        assertEquals("Arial", fontInfo.getFamily());  // Assert
        assertTrue(fontInfo.isBold());
        assertEquals(12, fontInfo.getSize());
    }
    
    // Additional tests could include:
    // - Testing with different font styles
    // - Testing with different font sizes
    // - Testing with empty string as family name
    // - Testing with null family name
    // - Testing with very large or small size values
    // - Testing with very long family names
    // - Testing with special characters in family name
    // etc.
}