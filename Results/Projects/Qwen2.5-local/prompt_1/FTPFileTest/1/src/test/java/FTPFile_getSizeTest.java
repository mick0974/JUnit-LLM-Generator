// FTPFile_getSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class of FTPFile.
 * It contains 10 unit test cases for the
 * FTPFile#public getSize() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getSizeTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testGetSize_withValidSize() {
        long expectedSize = 123456789L;
        ftpFile.setSize(expectedSize);
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testGetSize_withNegativeSize() {
        long expectedSize = -123456789L;
        ftpFile.setSize(expectedSize);
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testGetSize_withZeroSize() {
        long expectedSize = 0L;
        ftpFile.setSize(expectedSize);
        assertEquals(expectedSize, ftpFile.getSize());
    }

    @Test
    public void testGetSize_withDefaultSize() {
        long defaultSize = -1L;
        assertEquals(defaultSize, ftpFile.getSize());
    }

    @Test
    public void testGetSize_afterSetToNull() {
        long defaultSize = -1L;
        ftpFile.setSize(123456789L);
        ftpFile.setSize(-1L);
        assertEquals(defaultSize, ftpFile.getSize());
    }
    
    @Test
    public void testGetSize_withLargePositiveSize() {
        long largeSize = Long.MAX_VALUE;
        ftpFile.setSize(largeSize);
        assertEquals(largeSize, ftpFile.getSize());
    }
    
    @Test
    public void testGetSize_withLargeNegativeSize() {
        long largeSize = Long.MIN_VALUE;
        ftpFile.setSize(largeSize);
        assertEquals(largeSize, ftpFile.getSize());
    }
    
    @Test
    public void testGetSize_withMaxIntValue() {
        int maxIntValue = Integer.MAX_VALUE;
        long expectedSize = (long)maxIntValue + 1;
        ftpFile.setSize(expectedSize);
        assertEquals(expectedSize, ftpFile.getSize());
    }
    
    @Test
    public void testGetSize_withMinIntValue() {
        int minIntValue = Integer.MIN_VALUE;
        long expectedSize = (long)minIntValue - 1;
        ftpFile.setSize(expectedSize);
        assertEquals(expectedSize, ftpFile.getSize());
    }
}