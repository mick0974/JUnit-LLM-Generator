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
    public void testNameNull() {
        assertEquals("", ftpFile.getName());
    }

    @Test
    public void testNameEmpty() {
        ftpFile.setName("");
        assertEquals("", ftpFile.getName());
    }

    @Test
    public void testNameValid() {
        ftpFile.setName("test.txt");
        assertEquals("test.txt", ftpFile.getName());
    }
    
    @Test
    public void testNameWithSpaces() {
        ftpFile.setName("test file.txt");
        assertEquals("test file.txt", ftpFile.getName());
    }

    @Test
    public void testNameWithSpecialChars() {
        ftpFile.setName("test!@#$%^&*().txt");
        assertEquals("test!@#$%^&*().txt", ftpFile.getName());
    }

    @Test
    public void testNameLong() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            sb.append('a');
        }
        ftpFile.setName(sb.toString());
        assertEquals(sb.toString(), ftpFile.getName());
    }

    @Test
    public void testNameUnicode() {
        ftpFile.setName("测试.txt"); // Chinese characters
        assertEquals("测试.txt", ftpFile.getName());
    }

    @Test
    public void testNameMaxValue() {
        ftpFile.setName("\uffff"); // Unicode max value
        assertEquals("\uffff", ftpFile.getName());
    }

    @Test
    public void testNameMinValue() {
        ftpFile.setName("\u0000"); // Unicode min value
        assertEquals("\u0000", ftpFile.getName());
    }

    @Test
    public void testNameBoundaryValue() {
        ftpFile.setName("a"); // Boundary value
        assertEquals("a", ftpFile.getName());
    }
}