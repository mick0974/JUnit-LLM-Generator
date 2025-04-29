// FTPFile_getUserTest.java
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
 * FTPFile#public getUser() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getUserTest {
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
    public void testGetUserTypicalCase() {
        ftpFile.setUser("testUser");
        assertEquals("testUser", ftpFile.getUser());
    }

    @Test
    public void testGetUserEmptyString() {
        ftpFile.setUser("");
        assertEquals("", ftpFile.getUser());
    }

    @Test
    public void testGetUserNull() {
        ftpFile.setUser(null);
        assertNull(ftpFile.getUser());
    }

    @Test
    public void testGetUserWhitespace() {
        ftpFile.setUser(" ");
        assertEquals(" ", ftpFile.getUser());
    }

    @Test
    public void testGetUserSpecialCharacters() {
        ftpFile.setUser("!@#$%^&*()");
        assertEquals("!@#$%^&*()", ftpFile.getUser());
    }

    @Test
    public void testGetUserLongString() {
        String longStr = "abcdefghijklmnopqrstuvwxyz";
        ftpFile.setUser(longStr);
        assertEquals(longStr, ftpFile.getUser());
    }

    @Test
    public void testGetUserUnicodeCharacters() {
        ftpFile.setUser("\u00E4\u00F6\u00FC"); // German umlauts
        assertEquals("\u00E4\u00F6\u00FC", ftpFile.getUser());
    }

    @Test
    public void testGetUserMultipleCalls() {
        ftpFile.setUser("firstUser");
        ftpFile.setUser("secondUser");
        assertEquals("secondUser", ftpFile.getUser());
    }

    @Test
    public void testGetUserAfterClearing() {
        ftpFile.setUser("testUser");
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Clear all other fields
        assertNull(ftpFile.getUser());
    }

    @Test
    public void testGetUserWithSpacesAtEnd() {
        ftpFile.setUser("testUser   ");
        assertEquals("testUser   ", ftpFile.getUser());
    }
}