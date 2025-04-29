// FTPFile_getLinkTest.java
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
 * FTPFile#public getLink() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getLinkTest {
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
    public void testGetLink_WithValidSymbolicLink() {
        ftpFile.setLink("targetfile.txt");
        assertEquals("targetfile.txt", ftpFile.getLink());
    }

    @Test
    public void testGetLink_WithNonSymbolicLink() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertNull(ftpFile.getLink());
    }

    @Test
    public void testGetLink_WithEmptyLink() {
        ftpFile.setLink("");
        assertEquals("", ftpFile.getLink());
    }

    @Test
    public void testGetLink_WithNullLink() {
        ftpFile.setLink(null);
        assertNull(ftpFile.getLink());
    }

    @Test
    public void testGetLink_WithWhitespaceLink() {
        ftpFile.setLink(" ");
        assertEquals(" ", ftpFile.getLink());
    }

    @Test
    public void testGetLink_WithInvalidLink() {
        ftpFile.setLink("invalid@link*char?");
        assertEquals("invalid@link*char?", ftpFile.getLink());
    }

    @Test
    public void testGetLink_AfterSettingOtherFields() {
        ftpFile.setName("example.txt");
        ftpFile.setSize(12345);
        ftpFile.setHardLinkCount(1);
        ftpFile.setUser("user");
        ftpFile.setGroup("group");
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink("targetfile.txt");

        assertEquals("targetfile.txt", ftpFile.getLink());
    }

    @Test
    public void testGetLink_AfterUnsettingAllFields() {
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        ftpFile.setLink(null);

        assertNull(ftpFile.getLink());
    }

    @Test
    public void testGetLink_AfterSettingInvalidEntry() {
        FTPFile invalidFtpFile = new FTPFile("invalid_entry");
        assertNull(invalidFtpFile.getLink());
    }
}