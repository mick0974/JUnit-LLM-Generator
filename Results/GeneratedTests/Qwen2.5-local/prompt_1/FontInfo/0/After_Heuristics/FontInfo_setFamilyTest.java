// FontInfo_setFamilyTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setFamily(String value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setFamilyTest {
    private FontInfo fontInfo;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test(expected = NullPointerException.class)
    public void testSetFamily_null() {
        fontInfo.setFamily(null);
    }

    @Test
    public void testSetFamily_emptyString() {
        fontInfo.setFamily("");
        assertEquals("", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_validString() {
        fontInfo.setFamily("Arial");
        assertEquals("Arial", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_defaultFamily() {
        fontInfo.setFamily(null);
        assertEquals(FontInfo.DEFAULT_FAMILY, fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_sameFamily() {
        fontInfo.setFamily("Times New Roman");
        fontInfo.setFamily("Times New Roman");
        assertEquals("Times New Roman", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_caseInsensitive() {
        fontInfo.setFamily("Verdana");
        fontInfo.setFamily("verdana");
        assertEquals("Verdana", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_withSpaces() {
        fontInfo.setFamily("Courier New");
        assertEquals("Courier New", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_withSpecialCharacters() {
        fontInfo.setFamily("Symbol");
        assertEquals("Symbol", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_withNumbers() {
        fontInfo.setFamily("Arial Black");
        assertEquals("Arial Black", fontInfo.getFamily());
    }

    @Test
    public void testSetFamily_withMixedCaseAndNumbers() {
        fontInfo.setFamily("Comic Sans MS");
        assertEquals("Comic Sans MS", fontInfo.getFamily());
    }
}