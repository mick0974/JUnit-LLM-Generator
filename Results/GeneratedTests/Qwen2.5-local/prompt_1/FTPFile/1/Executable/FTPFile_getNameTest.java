// FTPFile_getNameTest.java
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
 * FTPFile#public getName() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getNameTest {
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
    public void testNameIsNull() {
        assertEquals(null, ftpFile.getName());
    }

    @Test
    public void testNameIsNotEmpty() {
        ftpFile.setName("test.txt");
        assertEquals("test.txt", ftpFile.getName());
    }
    
    @Test
    public void testSetAndGetName() {
        ftpFile.setName("sample.pdf");
        String expectedName = "sample.pdf";
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testSetNullName() {
        ftpFile.setName(null);
        assertEquals(null, ftpFile.getName());
    }

    @Test
    public void testGetEmptyName() {
        ftpFile.setName("");
        assertEquals("", ftpFile.getName());
    }

    @Test
    public void testGetWhitespaceName() {
        ftpFile.setName("   ");
        assertEquals("   ", ftpFile.getName());
    }

    @Test
    public void testGetNameWithSpecialCharacters() {
        ftpFile.setName("file@with!special*characters.txt");
        assertEquals("file@with!special*characters.txt", ftpFile.getName());
    }

    @Test
    public void testGetNameWithLeadingSpace() {
        ftpFile.setName(" leading_space.txt");
        assertEquals(" leading_space.txt", ftpFile.getName());
    }

    @Test
    public void testGetNameWithTrailingSpace() {
        ftpFile.setName("trailing_space.txt ");
        assertEquals("trailing_space.txt ", ftpFile.getName());
    }

    @Test
    public void testGetNameWithMultipleSpaces() {
        ftpFile.setName(" multiple  spaces.txt");
        assertEquals(" multiple  spaces.txt", ftpFile.getName());
    }
}