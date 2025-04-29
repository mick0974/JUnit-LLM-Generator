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
    public void testIsSymbolicLinkWhenTypeIsSymbolicLink() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertTrue("File should be a symbolic link", ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkWhenTypeIsFile() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse("File should not be a symbolic link", ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkWhenTypeIsDirectory() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        assertFalse("Directory should not be a symbolic link", ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkWhenTypeIsUnknown() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        assertFalse("Unknown type should not be a symbolic link", ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkWithInitialType() {
        assertTrue("Initially, type should be unknown", ftpFile.isUnknown());
        assertFalse("Initially, file should not be a symbolic link", ftpFile.isSymbolicLink());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTypeWithInvalidTypeThrowsException() {
        ftpFile.setType(4); // Invalid type, should be within 0-3
    }

    @Test
    public void testIsSymbolicLinkAfterSettingName() {
        ftpFile.setName("testLink");
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertTrue("File should still be a symbolic link after setting a name", ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkAfterSettingSize() {
        ftpFile.setSize(2048L);
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        assertTrue("File should still be a symbolic link after setting a size", ftpFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkWhenInvalidEntry() {
        FTPFile invalidFile = new FTPFile("invalid");
        assertFalse("Invalid entry should not be a symbolic link", invalidFile.isSymbolicLink());
    }

    @Test
    public void testIsSymbolicLinkWithMixedAttributes() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setName("linkName");
        ftpFile.setSize(1024L);
        ftpFile.setUser("ownerUser");
        ftpFile.setGroup("ownerGroup");
        assertTrue("File should be a symbolic link with mixed attributes set", ftpFile.isSymbolicLink());
    }
}