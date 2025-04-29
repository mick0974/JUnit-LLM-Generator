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
        assertTrue("Should be true", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_false() {
        fontInfo.setIsItalic(false);
        assertFalse("Should be false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_null() {
        try {
            fontInfo.setIsItalic(null);
        } catch (Exception e) {
            fail("Expected no exception to be thrown");
        }
    }

    @Test
    public void testSetIsItalic_initialValue() {
        assertFalse("Initial value should be false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_sameValue() {
        fontInfo.setIsItalic(true);
        fontInfo.setIsItalic(true);
        assertTrue("Should still be true", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_changeValue() {
        fontInfo.setIsItalic(true);
        fontInfo.setIsItalic(false);
        assertFalse("Should now be false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_noChange() {
        fontInfo.setIsItalic(false);
        fontInfo.setIsItalic(false);
        assertFalse("Should still be false", fontInfo.isItalic());
    }

    @Test
    public void testSetIsItalic_emptyString() {
        try {
            fontInfo.setIsItalic("");
        } catch (Exception e) {
            fail("Expected no exception to be thrown");
        }
    }

    @Test
    public void testSetIsItalic_spaceString() {
        try {
            fontInfo.setIsItalic(" ");
        } catch (Exception e) {
            fail("Expected no exception to be thrown");
        }
    }

    @Test
    public void testSetIsItalic_tabString() {
        try {
            fontInfo.setIsItalic("\t");
        } catch (Exception e) {
            fail("Expected no exception to be thrown");
        }
    }
}