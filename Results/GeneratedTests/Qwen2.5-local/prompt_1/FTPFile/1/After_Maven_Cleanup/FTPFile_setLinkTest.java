// FTPFile_setLinkTest.java
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
 * FTPFile#public setLink(String link) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setLinkTest {
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    /**
     * Test setting link when the link is not null
     */
    @Test
    public void testSetLinkNotNull() {
        String link = "test.txt";
        ftpFile.setLink(link);
        assertEquals(link, ftpFile.getLink());
    }

    /**
     * Test setting link when the link is null
     */
    @Test
    public void testSetLinkNull() {
        ftpFile.setLink(null);
        assertNull(ftpFile.getLink());
    }

    /**
     * Test setting link when the current link is already set
     */
    @Test
    public void testSetLinkWithExistingLink() {
        String link1 = "test1.txt";
        String link2 = "test2.txt";
        ftpFile.setLink(link1);
        ftpFile.setLink(link2);
        assertEquals(link2, ftpFile.getLink());
    }
    
    /**
     * Test setting link when the FTPFile is invalid
     */
    
    /**
     * Test setting link when the FTPFile is of unknown type
     */
    @Test
    public void testSetLinkOnUnknownFTPFileType() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        ftpFile.setLink("test.txt");
        assertEquals("test.txt", ftpFile.getLink());
    }
    
    /**
     * Test setting link when the FTPFile is of directory type
     */
    @Test
    public void testSetLinkOnDirectoryFTPFileType() {
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setLink("test.txt");
        assertEquals("test.txt", ftpFile.getLink());
    }
    
    /**
     * Test setting link when the FTPFile is of symbolic link type
     */
    @Test
    public void testSetLinkOnSymbolicLinkFTPFileType() {
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink("test.txt");
        assertEquals("test.txt", ftpFile.getLink());
    }
    
    /**
     * Test setting link when the FTPFile is of file type
     */
    @Test
    public void testSetLinkOnFileType() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setLink("test.txt");
        assertEquals("test.txt", ftpFile.getLink());
    }
    
    /**
     * Test setting link when the FTPFile's name is already set
     */
    @Test
    public void testSetLinkWithNameAlreadySet() {
        String name = "test.txt";
        String link = "test.txt";
        ftpFile.setName(name);
        ftpFile.setLink(link);
        assertEquals(link, ftpFile.getLink());
    }
    
    /**
     * Test setting link when the FTPFile's name is null
     */
    @Test
    public void testSetLinkWithNameNull() {
        String link = "test.txt";
        ftpFile.setName(null);
        ftpFile.setLink(link);
        assertEquals(link, ftpFile.getLink());
    }
}