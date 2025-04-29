// FTPFile_isDirectoryTest.java
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
 * FTPFile#public isDirectory() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isDirectoryTest {
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
    public void testIsDirectory_validDirectory() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertTrue(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_validFile() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_validSymbolicLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_validUnknown() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_invalidEntry() {
        FTPFile invalidFtpFile = new FTPFile("Invalid Entry");
        assertFalse(invalidFtpFile.isDirectory());
    }
    
    @Test
    public void testIsDirectory_nullEntry() {
        FTPFile nullFtpFile = null;
        try {
            boolean result = nullFtpFile.isDirectory();
            fail("Expected NullPointerException but got " + result);
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testIsDirectory_defaultConstructor() {
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_noTypeSet() {
        ftpFile.setType(-1); // Invalid type
        assertFalse(ftpFile.isDirectory());
    }

    @Test
    public void testIsDirectory_typeZero() {
        ftpFile.setType(0); // Type zero
        assertFalse(ftpFile.isDirectory());
    }
}