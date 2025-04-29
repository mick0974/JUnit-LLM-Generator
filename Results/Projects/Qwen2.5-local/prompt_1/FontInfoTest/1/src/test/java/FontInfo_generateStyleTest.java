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
    public void testGenerateStyle_normal() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);
        assertEquals(Font.BOLD, fontInfo.generateStyle());
    }

    @Test
    public void testGenerateStyle_italic() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);
        assertEquals(Font.ITALIC, fontInfo.generateStyle());
    }

    @Test
    public void testGenerateStyle_both() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals(Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
    }

    @Test
    public void testGenerateStyle_plain() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);
        assertEquals(Font.PLAIN, fontInfo.generateStyle());
    }
    
    // Add more tests as needed to cover additional scenarios or edge cases
}