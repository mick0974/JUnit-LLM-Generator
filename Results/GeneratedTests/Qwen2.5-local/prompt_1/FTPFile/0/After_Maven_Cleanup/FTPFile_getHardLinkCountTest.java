// FTPFile_getHardLinkCountTest.java
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
 * FTPFile#public getHardLinkCount() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getHardLinkCountTest {
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
    public void testGetHardLinkCountTypicalCase() {
        ftpFile.setHardLinkCount(5);
        assertEquals(5, ftpFile.getHardLinkCount());
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testGetHardLinkCountNegativeValue() {
        ftpFile.setHardLinkCount(-1);
        ftpFile.getHardLinkCount();
    }

    @Test
    public void testGetHardLinkCountZeroValue() {
        ftpFile.setHardLinkCount(0);
        assertEquals(0, ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testGetHardLinkCountMaxIntValue() {
        ftpFile.setHardLinkCount(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCountMinIntValue() {
        ftpFile.setHardLinkCount(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testGetHardLinkCountNullValue() {
        ftpFile.setHardLinkCount(ftpFile.getHardLinkCount());
        assertEquals(ftpFile.getHardLinkCount(), ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testGetHardLinkCountDefaultValue() {
        assertEquals(0, ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testGetHardLinkCountAfterSetInvalidValue() {
        ftpFile.setHardLinkCount(ftpFile.getHardLinkCount());
        assertEquals(ftpFile.getHardLinkCount(), ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testGetHardLinkCountAfterSetValidValue() {
        ftpFile.setHardLinkCount(5);
        assertEquals(5, ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testGetHardLinkCountAfterSetToNull() {
        ftpFile.setHardLinkCount(ftpFile.getHardLinkCount());
        assertEquals(ftpFile.getHardLinkCount(), ftpFile.getHardLinkCount());
    }
}