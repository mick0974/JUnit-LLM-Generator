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
    public void testGetHardLinkCount_defaultValue() {
        assertEquals(0, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCount_setToNegativeNumber() {
        ftpFile.setHardLinkCount(-1);
        assertEquals(-1, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCount_setToZero() {
        ftpFile.setHardLinkCount(0);
        assertEquals(0, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCount_setToOne() {
        ftpFile.setHardLinkCount(1);
        assertEquals(1, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCount_setToPositiveNumber() {
        ftpFile.setHardLinkCount(10);
        assertEquals(10, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCount_setToLargeNumber() {
        ftpFile.setHardLinkCount(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, ftpFile.getHardLinkCount());
    }

    @Test
    public void testGetHardLinkCount_setToSmallNumber() {
        ftpFile.setHardLinkCount(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, ftpFile.getHardLinkCount());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetHardLinkCount_invalidInstance() {
        FTPFile invalidFtpFile = null;
        invalidFtpFile.getHardLinkCount();
    }

    @Test
    public void testGetHardLinkCount_afterSettingOtherProperties() {
        ftpFile.setName("test.txt");
        ftpFile.setSize(1024);
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setHardLinkCount(5);

        assertEquals(5, ftpFile.getHardLinkCount());
    }
}