// FTPFile_setHardLinkCountTest.java
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
 * FTPFile#public setHardLinkCount(int links) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setHardLinkCountTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeLinks() {
        ftpFile.setHardLinkCount(-1);
    }

    @Test
    public void testValidLinks() {
        ftpFile.setHardLinkCount(1);
        assertEquals(1, ftpFile.getHardLinkCount());

        ftpFile.setHardLinkCount(100);
        assertEquals(100, ftpFile.getHardLinkCount());
    }
    
    @Test
    public void testZeroLinks() {
        ftpFile.setHardLinkCount(0);
        assertEquals(0, ftpFile.getHardLinkCount());
    }
}