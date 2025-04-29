// FTPFile_getGroupTest.java
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
 * FTPFile#public getGroup() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getGroupTest {
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
    public void testGetGroupTypical() {
        ftpFile.setGroup("testGroup");
        assertEquals("testGroup", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupEmpty() {
        ftpFile.setGroup("");
        assertEquals("", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupNull() {
        ftpFile.setGroup(null);
        assertNull(ftpFile.getGroup());
    }

    @Test
    public void testGetGroupWhitespace() {
        ftpFile.setGroup(" ");
        assertEquals(" ", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupNumeric() {
        ftpFile.setGroup("12345");
        assertEquals("12345", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupSpecialChars() {
        ftpFile.setGroup("!@#$%^&*()");
        assertEquals("!@#$%^&*()", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupMixedCase() {
        ftpFile.setGroup("TeStGrOuP");
        assertEquals("TeStGrOuP", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupUnicode() {
        ftpFile.setGroup("\u00E4\u00F6\u00FC"); // German umlauts
        assertEquals("\u00E4\u00F6\u00FC", ftpFile.getGroup());
    }


}