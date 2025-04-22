// FontInfo_getSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public getSize() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_getSizeTest {
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
    public void testGetSize_defaultSize() {
        assertEquals(12, fontInfo.getSize());
    }

    @Test
    public void testGetSize_customSize() {
        fontInfo.setSize(24);
        assertEquals(24, fontInfo.getSize());
    }

    @Test
    public void testGetSize_negativeSize() {
        fontInfo.setSize(-5);
        assertEquals(12, fontInfo.getSize());
    }

    @Test
    public void testGetSize_zeroSize() {
        fontInfo.setSize(0);
        assertEquals(12, fontInfo.getSize());
    }
    
    // Add more tests as needed
}