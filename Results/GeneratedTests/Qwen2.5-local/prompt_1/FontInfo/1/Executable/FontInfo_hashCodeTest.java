// FontInfo_hashCodeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public hashCode() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_hashCodeTest {
    private FontInfo fontInfo1;
    private FontInfo fontInfo2;

    @Before
    public void setUp() {
        fontInfo1 = new FontInfo(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
        fontInfo2 = new FontInfo(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
    }

    @After
    public void tearDown() {
        fontInfo1 = null;
        fontInfo2 = null;
    }

    @Test
    public void testHashCode_sameObject() {
        assertEquals(fontInfo1.hashCode(), fontInfo1.hashCode());
    }

    @Test
    public void testHashCode_differentObjectsSameValues() {
        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_differentFamily() {
        fontInfo2.setFamily("Times New Roman");
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_differentSize() {
        fontInfo2.setSize(14);
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_differentBoldness() {
        fontInfo2.setIsBold(!fontInfo1.isBold());
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_differentItalics() {
        fontInfo2.setIsItalic(!fontInfo1.isItalic());
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_nullFamily() {
        fontInfo1.setFamily(null);
        fontInfo2.setFamily(null);
        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_emptyFamily() {
        fontInfo1.setFamily("");
        fontInfo2.setFamily("");
        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void testHashCode_differentFamilyIgnoreCase() {
        fontInfo2.setFamily("arial");
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }
}