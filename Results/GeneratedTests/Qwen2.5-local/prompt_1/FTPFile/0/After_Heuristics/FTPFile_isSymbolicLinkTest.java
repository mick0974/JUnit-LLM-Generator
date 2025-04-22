// FTPFile_isSymbolicLinkTest.java
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
 * FTPFile#public isSymbolicLink() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_isSymbolicLinkTest {
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
    public void testIsSymbolicLinkFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkDirectoryType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkSymbolicLinkType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertTrue(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkUnknownType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkNullType() {
        ftpFile.setType(0); // Assuming 0 is not a valid type
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkNegativeType() {
        ftpFile.setType(-1); // Assuming negative types are not valid
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkPositiveType() {
        ftpFile.setType(5); // Assuming positive types are not valid
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkValidSymbolicLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink("target_file");
        assertTrue(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkInvalidSymbolicLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink("");
        assertFalse(ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkNoLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(null);
        assertFalse(ftpFile.isSymbolicLink());
    }
}