// FontInfo_equalsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_equalsTest {
    private FontInfo fontInfo1;
    private FontInfo fontInfo2;
    private FontInfo fontInfo3;

    @Before
    public void setUp() {
        fontInfo1 = new FontInfo();
        fontInfo2 = new FontInfo(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        fontInfo3 = new FontInfo(new Font("SansSerif", Font.PLAIN, 16));
    }

    @After
    public void tearDown() {
        fontInfo1 = null;
        fontInfo2 = null;
        fontInfo3 = null;
    }

    @Test
    public void testEquals_sameObject() {
        assertTrue(fontInfo1.equals(fontInfo1));
    }

    @Test
    public void testEquals_nullObject() {
        assertFalse(fontInfo1.equals(null));
    }

    @Test
    public void testEquals_differentClass() {
        assertFalse(fontInfo1.equals(new Object()));
    }

    @Test
    public void testEquals_identicalObjects() {
        FontInfo fontInfoCopy = new FontInfo();
        assertTrue(fontInfo1.equals(fontInfoCopy));
    }

    @Test
    public void testEquals_differentFamily() {
        fontInfo2.setFamily("Arial");
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_differentSize() {
        fontInfo2.setSize(15);
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_differentStyle() {
        fontInfo2.setIsBold(true);
        fontInfo2.setIsItalic(false);
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_differentAllProperties() {
        fontInfo2.setFamily("Arial");
        fontInfo2.setSize(15);
        fontInfo2.setIsBold(true);
        fontInfo2.setIsItalic(false);
        assertFalse(fontInfo1.equals(fontInfo2));
    }
}