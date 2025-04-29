// FTPFile_getTypeTest.java
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
 * FTPFile#public getType() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getTypeTest {
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
    public void testGetTypeInitiallyUnknown() {
        assertEquals(FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }

    @Test
    public void testGetTypeAfterSettingFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertEquals(FTPFile.FILE_TYPE, ftpFile.getType());
    }

    @Test
    public void testGetTypeAfterSettingDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
    }

    @Test
    public void testGetTypeAfterSettingSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertEquals(FTPFile.SYMBOLIC_LINK_TYPE, ftpFile.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeWithNegativeValue() {
        ftpFile.setType(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTypeWithLargeValue() {
        ftpFile.setType(Integer.MAX_VALUE + 1);
    }
}