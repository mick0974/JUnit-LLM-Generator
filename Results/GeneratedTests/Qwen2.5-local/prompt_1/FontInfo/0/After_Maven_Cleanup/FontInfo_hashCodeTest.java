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
    private FontInfo fi1;
    private FontInfo fi2;
    private FontInfo fi3;
    
    @Before
    public void setUp() {
        fi1 = new FontInfo(new Font("Arial", Font.BOLD, 12));
        fi2 = new FontInfo(new Font("Arial", Font.BOLD, 12));
        fi3 = new FontInfo(new Font("Times New Roman", Font.ITALIC, 14));
    }
    
    @After
    public void tearDown() {
        fi1 = null;
        fi2 = null;
        fi3 = null;
    }
    
    @Test
    public void testHashCode_sameObjects() {
        assertEquals(fi1.hashCode(), fi1.hashCode());
    }

    @Test
    public void testHashCode_differentObjects() {
        assertNotEquals(fi1.hashCode(), fi3.hashCode());
    }

    @Test
    public void testHashCode_equalObjects() {
        assertEquals(fi1.hashCode(), fi2.hashCode());
    }

    @Test
    public void testHashCode_nullObject() {
        FontInfo fi = null;
        try {
            fi.hashCode();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testHashCode_differentFamily() {
        fi2.setFamily("Times New Roman");
        assertNotEquals(fi1.hashCode(), fi2.hashCode());
    }

    @Test
    public void testHashCode_differentSize() {
        fi2.setSize(14);
        assertNotEquals(fi1.hashCode(), fi2.hashCode());
    }

    @Test
    public void testHashCode_differentStyle() {
        fi2.setIsBold(false);
        assertNotEquals(fi1.hashCode(), fi2.hashCode());
    }

    @Test
    public void testHashCode_allFieldsDifferent() {
        fi2.setFamily("Times New Roman");
        fi2.setSize(14);
        fi2.setIsBold(false);
        assertNotEquals(fi1.hashCode(), fi2.hashCode());
    }

    @Test
    public void testHashCode_emptyFamily() {
        fi2.setFamily("");
        assertNotEquals(fi1.hashCode(), fi2.hashCode());
    }
}