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
    public void testSetNameTypicalCase() {
        // Arrange
        String expectedName = "test.txt";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testSetNameEmptyString() {
        // Arrange
        String expectedName = "";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName, ftpFile.getName());
    }

    @Test
    public void testSetNameNull() {
        // Arrange
        String expectedName = null;

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertNull(ftpFile.getName());
    }
    
    @Test
    public void testSetNameWhitespace() {
        // Arrange
        String expectedName = "   ";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName.trim(), ftpFile.getName().trim());
    }
    
    @Test
    public void testSetNameLongFileName() {
        // Arrange
        String expectedName = "ThisIsAVeryLongFileNameThatExceedsTheMaximumAllowedLengthOfAFileNameInMostOperatingSystemsAndShouldBeTruncatedOrRejectedBySomeFileSystemsIfItIsNotSupported";
        
        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertTrue(ftpFile.getName().length() <= 255); // Assuming maximum length is 255 characters
    }
    
    @Test
    public void testSetNameWithSpecialCharacters() {
        // Arrange
        String expectedName = "file@with#special*characters.txt";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName, ftpFile.getName());
    }
    
    @Test
    public void testSetNameWithLeadingSpaces() {
        // Arrange
        String expectedName = "  LeadingSpaces.txt";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName.trim(), ftpFile.getName().trim());
    }
    
    @Test
    public void testSetNameWithTrailingSpaces() {
        // Arrange
        String expectedName = "TrailingSpaces.txt  ";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName.trim(), ftpFile.getName().trim());
    }
    
    @Test
    public void testSetNameWithLeadingAndTrailingSpaces() {
        // Arrange
        String expectedName = "  LeadingAndTrailingSpaces.txt  ";

        // Act
        ftpFile.setName(expectedName);

        // Assert
        assertEquals(expectedName.trim(), ftpFile.getName().trim());
    }
}