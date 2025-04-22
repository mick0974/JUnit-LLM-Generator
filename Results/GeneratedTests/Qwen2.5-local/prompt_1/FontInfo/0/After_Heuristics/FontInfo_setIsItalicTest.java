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
    public void testSetIsItalic_true() {
        fontInfo.setIsItalic(true);
        assertTrue("Expected to be true", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_false() {
        fontInfo.setIsItalic(false);
        assertFalse("Expected to be false", fontInfo.isItalic());
    }
    
    @Test
    public void testSetIsItalic_initialValue() {
        assertEquals("Initial value should be false", false, fontInfo.isItalic());
    }
    
    @Test
    public void testSetIsItalic_sameValue() {
        fontInfo.setIsItalic(true);
        fontInfo.setIsItalic(true);
        assertTrue("Should still be true after setting again", fontInfo.isItalic());
        
        fontInfo.setIsItalic(false);
        fontInfo.setIsItalic(false);
        assertFalse("Should still be false after setting again", fontInfo.isItalic());
    }
    
    @Test
    public void testSetIsItalic_afterCreateFont() {
        fontInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        fontInfo.setIsItalic(true);
        assertTrue("Expected to be true after creating font", fontInfo.isItalic());
        
        fontInfo.setIsItalic(false);
        assertFalse("Expected to be false after creating font", fontInfo.isItalic());
    }
    
    @Test
    public void testSetIsItalic_nullFont() {
        try {
            fontInfo.setFont(null);
        } catch (IllegalArgumentException e) {
            fail("Expected no exception when passing null Font");
        }
    }
    
    @Test
    public void testSetIsItalic_styleChanged() {
        fontInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        fontInfo.setIsItalic(true);
        assertEquals("Expected style to be ITALIC", Font.ITALIC, fontInfo.generateStyle());

        fontInfo.setIsItalic(false);
        assertEquals("Expected style to be PLAIN", Font.PLAIN, fontInfo.generateStyle());
    }
    
    @Test
    public void testSetIsItalic_clone() {
        FontInfo original = new FontInfo();
        original.setIsItalic(true);
        
        FontInfo cloned = (FontInfo) original.clone();
        assertEquals("Cloned font info should have same isItalic state", true, cloned.isItalic());
        
        cloned.setIsItalic(false);
        assertNotEquals("Original font info should not have changed after cloning", true, original.isItalic());
    }
    
    @Test
    public void testSetIsItalic_toString() {
        fontInfo.setIsItalic(true);
        assertTrue("toString should include 'italic'", fontInfo.toString().contains("italic"));
        
        fontInfo.setIsItalic(false);
        assertFalse("toString should not include 'italic'", fontInfo.toString().contains("italic"));
    }
}