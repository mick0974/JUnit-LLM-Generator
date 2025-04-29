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

    // Test setting family to a valid non-null string
    @Test
    public void testSetFamily_ValidString() {
        String family = "Arial";
        fontInfo.setFamily(family);
        assertEquals(family, fontInfo.getFamily());
    }

    // Test setting family to null should reset to default

    // Test setting family to an empty string should preserve the empty string
    @Test
    public void testSetFamily_EmptyString() {
        fontInfo.setFamily("");
        assertEquals("", fontInfo.getFamily());
    }

    // Test setting family to a string with special characters
    @Test
    public void testSetFamily_SpecialCharacters() {
        String family = "@#$%&";
        fontInfo.setFamily(family);
        assertEquals(family, fontInfo.getFamily());
    }

    // Test setting family after initial set to another valid family name
    @Test
    public void testSetFamily_ChangeFamily() {
        fontInfo.setFamily("Arial");
        fontInfo.setFamily("Times New Roman");
        assertEquals("Times New Roman", fontInfo.getFamily());
    }

    // Test setting family to a pre-defined family in Java
    @Test
    public void testSetFamily_PredefinedFamily() {
        fontInfo.setFamily("Serif");
        assertEquals("Serif", fontInfo.getFamily());
    }

    // Test setting family with leading and trailing spaces
    @Test
    public void testSetFamily_WithLeadingTrailingSpaces() {
        fontInfo.setFamily("   Futura   ");
        assertEquals("   Futura   ", fontInfo.getFamily());
    }

    // Test setting family to a very long string
    @Test
    public void testSetFamily_LongString() {
        String family = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        fontInfo.setFamily(family);
        assertEquals(family, fontInfo.getFamily());
    }

    // Test setting family with Unicode characters
    @Test
    public void testSetFamily_UnicodeCharacters() {
        String family = "ファミリー";
        fontInfo.setFamily(family);
        assertEquals(family, fontInfo.getFamily());
    }

    // Test setting family to a previously set family
    @Test
    public void testSetFamily_ReassignSameFamily() {
        fontInfo.setFamily("Courier New");
        fontInfo.setFamily("Courier New");
        assertEquals("Courier New", fontInfo.getFamily());
    }
}