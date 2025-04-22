// FTPFile_setNameTest.java
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
 * FTPFile#public setName(String name) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setNameTest {
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
    public void testNameWithValidInput() {
        String expectedName = "test.txt";
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testNameWithEmptyInput() {
        String expectedName = "";
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testNameWithNullInput() {
        String expectedName = null;
        ftpFile.setName(expectedName);
        assertNull(ftpFile.getName());
    }


    @Test
    public void testNameWithSpecialCharactersInput() {
        String expectedName = "!@#$%^&*()_+";
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testNameWithLongInput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append('a');
        }
        String expectedName = sb.toString();
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testNameWithUnicodeInput() {
        String expectedName = "\u00A9 \u00E9 \u00F6 \u00FC \u00DF";
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }


    @Test
    public void testNameWithMixedCaseInput() {
        String expectedName = "Mixed Case Input";
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testNameWithNumbersInput() {
        String expectedName = "1234567890";
        ftpFile.setName(expectedName);
        assertEquals(expectedName, ftpFile.getName());
    }
}