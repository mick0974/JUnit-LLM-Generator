// FontInfo_toStringTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_toStringTest {
    private FontInfo fontInfo;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
        fontInfo.setFamily("Arial");
        fontInfo.setSize(14);
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testToString() {
        assertEquals("Arial, 14, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithAllValues() {
        fontInfo.setFamily("Times New Roman");
        fontInfo.setSize(20);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);
        assertEquals("Times New Roman, 20, italic", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithNoBoldAndItalic() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);
        assertEquals("Arial, 14", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithNullFamily() {
        fontInfo.setFamily(null);
        assertEquals("null, 14, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithEmptyFamily() {
        fontInfo.setFamily("");
        assertEquals(", 14, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithNegativeSize() {
        fontInfo.setSize(-5);
        assertEquals("Arial, -5, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithZeroSize() {
        fontInfo.setSize(0);
        assertEquals("Arial, 0, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithLargeSize() {
        fontInfo.setSize(Integer.MAX_VALUE);
        assertEquals("Arial, 2147483647, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithVerySmallSize() {
        fontInfo.setSize(Integer.MIN_VALUE);
        assertEquals("Arial, -2147483648, bold", fontInfo.toString());
    }
    
    @Test
    public void testToStringWithMixedCaseFamily() {
        fontInfo.setFamily("MiXeD CaSe");
        assertEquals("MiXeD CaSe, 14, bold", fontInfo.toString());
    }
}