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
    public void testGetGroupTypicalCase() {
        ftpFile.setGroup("testGroup");
        assertEquals("testGroup", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupEmptyString() {
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
        ftpFile.setGroup("   ");
        assertEquals("   ", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupSpecialCharacters() {
        ftpFile.setGroup("!@#$%^&*()");
        assertEquals("!@#$%^&*()", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupNumericValue() {
        ftpFile.setGroup("12345");
        assertEquals("12345", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupMixedCase() {
        ftpFile.setGroup("TeStGrOuP");
        assertEquals("TeStGrOuP", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append('a');
        }
        ftpFile.setGroup(sb.toString());
        assertEquals(sb.toString(), ftpFile.getGroup());
    }

    @Test
    public void testGetGroupSingleCharacter() {
        ftpFile.setGroup("a");
        assertEquals("a", ftpFile.getGroup());
    }

    @Test
    public void testGetGroupMultipleSpaces() {
        ftpFile.setGroup("     ");
        assertEquals("     ", ftpFile.getGroup());
    }
}