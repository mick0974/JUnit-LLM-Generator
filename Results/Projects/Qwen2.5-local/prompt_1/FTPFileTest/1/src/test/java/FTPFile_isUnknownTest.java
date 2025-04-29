// FTPFile_isUnknownTest.java
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
 * FTPFile#public isUnknown() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isUnknownTest {
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
    public void testIsUnknown_TypeIsFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse("Should return false when type is FILE_TYPE", ftpFile.isUnknown());
    }

    @Test
    public void testIsUnknown_TypeIsDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse("Should return false when type is DIRECTORY_TYPE", ftpFile.isUnknown());
    }

    @Test
    public void testIsUnknown_TypeIsSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse("Should return false when type is SYMBOLIC_LINK_TYPE", ftpFile.isUnknown());
    }

    @Test
    public void testIsUnknown_TypeIsUnknownType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertTrue("Should return true when type is UNKNOWN_TYPE", ftpFile.isUnknown());
    }
}