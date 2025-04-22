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
    public void testIsBold_defaultValue() {
        assertFalse("Default value should be false", fontInfo.isBold());
    }

    @Test
    public void testIsBold_setTrue() {
        fontInfo.setIsBold(true);
        assertTrue("Value should be true after setting", fontInfo.isBold());
    }

    @Test
    public void testIsBold_setFalse() {
        fontInfo.setIsBold(false);
        assertFalse("Value should be false after setting", fontInfo.isBold());
    }

    @Test
    public void testIsBold_fontConstructor() {
        Font font = new Font("Serif", Font.BOLD, 12);
        FontInfo fromFont = new FontInfo(font);
        assertTrue("Font constructor should set to true", fromFont.isBold());
    }
    
    // Additional tests could include:
    // - Testing with null Font in constructor
    // - Testing with different styles in Font constructor
    // - Testing serialization/deserialization behavior
    // - Testing thread safety
}