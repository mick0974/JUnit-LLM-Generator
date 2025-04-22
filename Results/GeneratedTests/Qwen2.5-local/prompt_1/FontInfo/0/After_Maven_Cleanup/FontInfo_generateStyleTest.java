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
    public void testGenerateStyle_PlainFont() {
        // Arrange
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.PLAIN, style);
    }

    @Test
    public void testGenerateStyle_BoldFont() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.BOLD, style);
    }

}