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

    /**
     * Test Case 1: Normal case
     * Input: "testUser"
     * Expected Output: User field should be set to "testUser".
     */
    @Test
    public void testSetUser_NormalCase() {
        ftpFile.setUser("testUser");
        assertEquals("testUser", ftpFile.getUser());
    }

    /**
     * Test Case 2: Empty input
     * Input: ""
     * Expected Output: User field should remain unchanged.
     */
    @Test
    public void testSetUser_EmptyInput() {
        ftpFile.setUser("");
        assertEquals("", ftpFile.getUser());
    }

    /**
     * Test Case 3: Null input
     * Input: null
     * Expected Output: User field should remain unchanged.
     */
    @Test
    public void testSetUser_NullInput() {
        ftpFile.setUser(null);
        assertNull(ftpFile.getUser());
    }

    /**
     * Test Case 4: Large input
     * Input: A very large string
     * Expected Output: User field should be set to the input string.
     */
    @Test
    public void testSetUser_LargeInput() {
        String largeString = "a".repeat(1000); // repeat "a" 1000 times
        ftpFile.setUser(largeString);
        assertEquals(largeString, ftpFile.getUser());
    }

    /**
     * Test Case 5: White space characters
     * Input: "   "
     * Expected Output: User field should be set to "   ".
     */
    @Test
    public void testSetUser_WhiteSpaceCharacters() {
        ftpFile.setUser("   ");
        assertEquals("   ", ftpFile.getUser());
    }

    /**
     * Test Case 6: Special characters
     * Input: "!@#$%^&*()"
     * Expected Output: User field should be set to "!@#$%^&*()".
     */
    @Test
    public void testSetUser_SpecialCharacters() {
        ftpFile.setUser("!@#$%^&*()");
        assertEquals("!@#$%^&*()", ftpFile.getUser());
    }

    /**
     * Test Case 7: Single quote
     * Input: "'"
     * Expected Output: User field should be set to "'".
     */
    @Test
    public void testSetUser_SingleQuote() {
        ftpFile.setUser("'"); // single quote character
        assertEquals("'", ftpFile.getUser());
    }

    /**
     * Test Case 8: Double quote
     * Input: "\""
     * Expected Output: User field should be set to "\"".
     */
    @Test
    public void testSetUser_DoubleQuote() {
        ftpFile.setUser("\""); // double quote character
        assertEquals("\"", ftpFile.getUser());
    }

    /**
     * Test Case 9: Backslash
     * Input: "\\"
     * Expected Output: User field should be set to "\\".
     */
    @Test
    public void testSetUser_Backslash() {
        ftpFile.setUser("\\"); // backslash character
        assertEquals("\\", ftpFile.getUser());
    }

    /**
     * Test Case 10: Tab character
     * Input: "\t"
     * Expected Output: User field should be set to "\t".
     */
    @Test
    public void testSetUser_TabCharacter() {
        ftpFile.setUser("\t"); // tab character
        assertEquals("\t", ftpFile.getUser());
    }
}