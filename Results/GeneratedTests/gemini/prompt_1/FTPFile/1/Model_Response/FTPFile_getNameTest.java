```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;

/**
 * Test class for FTPFile#getName().
 * Contains unit test cases focusing on the getName() method behavior.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Uses JUnit 4 framework.
 */
public class FTPFile_getNameTest {

    private FTPFile ftpFile;

    @Before
    public void setUp() {
        // Arrange: Create a new FTPFile instance before each test
        ftpFile = new FTPFile();
    }

    /**
     * Test case 1: Get name when it's explicitly set to a typical file name.
     */
    @Test
    public void testGetName_TypicalFileName() {
        // Arrange
        String expectedName = "myfile.txt";
        ftpFile.setName(expectedName);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the set typical file name", expectedName, actualName);
    }

    /**
     * Test case 2: Get name when it's set to an empty string.
     */
    @Test
    public void testGetName_EmptyFileName() {
        // Arrange
        String expectedName = "";
        ftpFile.setName(expectedName);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the set empty file name", expectedName, actualName);
    }

    /**
     * Test case 3: Get name when it hasn't been set (should be null by default).
     */
    @Test
    public void testGetName_NotSet() {
        // Arrange: ftpFile is newly created in setUp(), name is null

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertNull("Should return null if the name was never set", actualName);
    }

    /**
     * Test case 4: Get name when it's explicitly set to null.
     */
    @Test
    public void testGetName_SetToNull() {
        // Arrange
        ftpFile.setName(null);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertNull("Should return null if the name was explicitly set to null", actualName);
    }

    /**
     * Test case 5: Get name containing spaces.
     */
    @Test
    public void testGetName_WithSpaces() {
        // Arrange
        String expectedName = "my document file.pdf";
        ftpFile.setName(expectedName);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the name containing spaces correctly", expectedName, actualName);
    }

    /**
     * Test case 6: Get name containing special characters.
     */
    @Test
    public void testGetName_WithSpecialCharacters() {
        // Arrange
        String expectedName = "file!@#$%^&*()_+=-`~[]{};':\",.<>?.log";
        ftpFile.setName(expectedName);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the name containing special characters correctly", expectedName, actualName);
    }

    /**
     * Test case 7: Get name after setting it multiple times.
     */
    @Test
    public void testGetName_SetMultipleTimes() {
        // Arrange
        ftpFile.setName("initial_name.tmp");
        ftpFile.setName("intermediate_name.bak");
        String expectedName = "final_name.dat";
        ftpFile.setName(expectedName);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the last set name", expectedName, actualName);
    }

    /**
     * Test case 8: Get name for a directory type file.
     */
    @Test
    public void testGetName_DirectoryName() {
        // Arrange
        String expectedName = "my_directory";
        ftpFile.setName(expectedName);
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the name correctly for a directory", expectedName, actualName);
        assertTrue(ftpFile.isDirectory());
    }

     /**
     * Test case 9: Get name for a symbolic link type file.
     */
    @Test
    public void testGetName_SymbolicLinkName() {
        // Arrange
        String expectedName = "link_to_file";
        ftpFile.setName(expectedName);
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink("actual_file.txt");


        // Act
        String actualName = ftpFile.getName();

        // Assert
        assertEquals("Should return the name correctly for a symbolic link", expectedName, actualName);
        assertTrue(ftpFile.isSymbolicLink());
        assertEquals("actual_file.txt", ftpFile.getLink());
    }

    /**
     * Test case 10: Get name for an FTPFile created from an invalid raw listing.
     * The name should be null as per the invalid constructor's initialization.
     */
    @Test
    public void testGetName_InvalidFTPFile() {
        // Arrange: Use the constructor for invalid entries
        FTPFile invalidFile = new FTPFile("drwxr-xr-x This is an invalid raw listing");

        // Act
        String actualName = invalidFile.getName();

        // Assert
        assertNull("Should return null for an invalid FTPFile instance", actualName);
        assertFalse("The file instance should be marked as invalid", invalidFile.isValid());
        assertEquals("drwxr-xr-x This is an invalid raw listing", invalidFile.getRawListing());
    }
}
```