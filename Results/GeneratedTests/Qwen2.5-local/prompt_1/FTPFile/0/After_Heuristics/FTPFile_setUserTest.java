// FTPFile_setUserTest.java
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
 * FTPFile#public setUser(String user) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setUserTest {
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
    public void testSetUser_withNullValue() {
        ftpFile.setUser(null);
        assertNull(ftpFile.getUser());
    }

    @Test
    public void testSetUser_withEmptyString() {
        ftpFile.setUser("");
        assertEquals("", ftpFile.getUser());
    }

    @Test
    public void testSetUser_withWhitespace() {
        ftpFile.setUser("   ");
        assertEquals("   ", ftpFile.getUser());
    }

    @Test
    public void testSetUser_withValidUsername() {
        String username = "testUser";
        ftpFile.setUser(username);
        assertEquals(username, ftpFile.getUser());
    }

    @Test
    public void testSetUser_withSpecialCharacters() {
        String username = "@#$%^&*()_+";
        ftpFile.setUser(username);
        assertEquals("@#$%^&*()_+", ftpFile.getUser());
    }

    @Test
    public void testSetUser_withUnicodeCharacters() {
        String username = "\u00E4\u00F6\u00FC"; // äöü
        ftpFile.setUser(username);
        assertEquals("\u00E4\u00F6\u00FC", ftpFile.getUser());
    }

    @Test
    public void testSetUser_afterGettingRawListing() {
        ftpFile.setRawListing("test");
        ftpFile.setUser("testUser");
        assertEquals("test", ftpFile.getRawListing());
        assertEquals("testUser", ftpFile.getUser());
    }

    @Test
    public void testSetUser_twice() {
        ftpFile.setUser("firstUser");
        ftpFile.setUser("secondUser");
        assertEquals("secondUser", ftpFile.getUser());
    }

    @Test
    public void testSetUser_afterSettingAllFields() {
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setHardLinkCount(1);
        ftpFile.setSize(1024);
        ftpFile.setGroup("group");
        ftpFile.setDate(Calendar.getInstance());
        ftpFile.setName("file.txt");
        ftpFile.setLink("link");

        ftpFile.setUser("testUser");
        assertEquals("testUser", ftpFile.getUser());
    }

    @Test
    public void testSetUser_afterParsingFailedEntry() {
        FTPFile failedEntry = new FTPFile("failed");
        failedEntry.setUser("testUser");
        assertEquals("failed", failedEntry.getRawListing());
        assertEquals("testUser", failedEntry.getUser());
    }
}