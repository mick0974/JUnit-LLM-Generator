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
    public void testGetUser_typical() {
        ftpFile.setUser("testUser");
        assertEquals("testUser", ftpFile.getUser());
    }

    @Test
    public void testGetUser_empty() {
        assertEquals("", ftpFile.getUser());
    }

    @Test
    public void testGetUser_null() {
        ftpFile.setUser(null);
        assertNull(ftpFile.getUser());
    }

    @Test
    public void testGetUser_whitespace() {
        ftpFile.setUser(" ");
        assertEquals(" ", ftpFile.getUser());
    }

    @Test
    public void testGetUser_specialCharacters() {
        ftpFile.setUser("!@#$%^&*()");
        assertEquals("!@#$%^&*()", ftpFile.getUser());
    }

    @Test
    public void testGetUser_maxLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            sb.append('a');
        }
        ftpFile.setUser(sb.toString());
        assertEquals(sb.toString(), ftpFile.getUser());
    }

    @Test
    public void testGetUser_boundaryValue() {
        ftpFile.setUser("a");
        assertEquals("a", ftpFile.getUser());
    }

    @Test
    public void testGetUser_afterSetToNull() {
        ftpFile.setUser("testUser");
        ftpFile.setUser(null);
        assertNull(ftpFile.getUser());
    }

    @Test
    public void testGetUser_afterSetToEmpty() {
        ftpFile.setUser("testUser");
        ftpFile.setUser("");
        assertEquals("", ftpFile.getUser());
    }

    @Test
    public void testGetUser_afterSetToWhitespace() {
        ftpFile.setUser("testUser");
        ftpFile.setUser(" ");
        assertEquals(" ", ftpFile.getUser());
    }
}