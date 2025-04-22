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

    @Test
    public void testSetLink_null() {
        String link = null;
        ftpFile.setLink(link);
        assertNull(ftpFile.getLink());
    }

    @Test
    public void testSetLink_empty() {
        String link = "";
        ftpFile.setLink(link);
        assertEquals("", ftpFile.getLink());
    }

    @Test
    public void testSetLink_space() {
        String link = " ";
        ftpFile.setLink(link);
        assertEquals(" ", ftpFile.getLink());
    }

    @Test
    public void testSetLink_regularFile() {
        String link = "regular_file.txt";
        ftpFile.setLink(link);
        assertEquals("regular_file.txt", ftpFile.getLink());
    }

    @Test
    public void testSetLink_directory() {
        String link = "directory/";
        ftpFile.setLink(link);
        assertEquals("directory/", ftpFile.getLink());
    }

    @Test
    public void testSetLink_symbolicLink() {
        String link = "symbolic_link -> target_file.txt";
        ftpFile.setLink(link);
        assertEquals("symbolic_link -> target_file.txt", ftpFile.getLink());
    }

    @Test
    public void testSetLink_specialCharacters() {
        String link = "!@#$%^&*()_+{}|:\"<>?`~";
        ftpFile.setLink(link);
        assertEquals("!@#$%^&*()_+{}|:\"<>?`~", ftpFile.getLink());
    }

    @Test
    public void testSetLink_unicode() {
        String link = "你好，世界！";
        ftpFile.setLink(link);
        assertEquals("你好，世界！", ftpFile.getLink());
    }

    @Test
    public void testSetLink_backslash() {
        String link = "path\\with\\backslashes";
        ftpFile.setLink(link);
        assertEquals("path\\with\\backslashes", ftpFile.getLink());
    }

    @Test
    public void testSetLink_forwardSlash() {
        String link = "path/with/forward/slashes";
        ftpFile.setLink(link);
        assertEquals("path/with/forward/slashes", ftpFile.getLink());
    }
}