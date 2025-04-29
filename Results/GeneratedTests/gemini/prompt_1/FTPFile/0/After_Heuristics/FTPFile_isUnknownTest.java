import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for FTPFile#isUnknown().
 * Contains unit tests covering different scenarios for the method.
 * Tests follow the Arrange-Act-Assert pattern.
 */
public class FTPFile_isUnknownTest {

    private FTPFile ftpFile;

    /**
     * Sets up a new FTPFile instance before each test.
     */
    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    /**
     * Test case 1: Default state.
     * An FTPFile created with the default constructor should have UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_DefaultState_ShouldReturnTrue() {
        // Arrange: ftpFile is created in setUp() with default constructor
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertTrue("Default FTPFile type should be UNKNOWN", result);
    }

    /**
     * Test case 2: Type explicitly set to UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_SetToUnknownType_ShouldReturnTrue() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertTrue("FTPFile type explicitly set to UNKNOWN should return true", result);
    }

    /**
     * Test case 3: Type set to FILE_TYPE.
     */
    @Test
    public void testIsUnknown_SetToFileType_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertFalse("FTPFile type set to FILE should return false for isUnknown", result);
    }

    /**
     * Test case 4: Type set to DIRECTORY_TYPE.
     */
    @Test
    public void testIsUnknown_SetToDirectoryType_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertFalse("FTPFile type set to DIRECTORY should return false for isUnknown", result);
    }

    /**
     * Test case 5: Type set to SYMBOLIC_LINK_TYPE.
     */
    @Test
    public void testIsUnknown_SetToSymbolicLinkType_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertFalse("FTPFile type set to SYMBOLIC_LINK should return false for isUnknown", result);
    }

    /**
     * Test case 6: Type changed from FILE_TYPE to UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_ChangedToFileThenUnknown_ShouldReturnTrue() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE); // Set to a known type first
        ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Change to unknown
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertTrue("FTPFile type changed to UNKNOWN should return true", result);
    }

    /**
     * Test case 7: Type changed from UNKNOWN_TYPE to DIRECTORY_TYPE.
     */
    @Test
    public void testIsUnknown_ChangedFromUnknownToDirectory_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Start as unknown (redundant due to default, but explicit)
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Change to known type
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertFalse("FTPFile type changed from UNKNOWN to DIRECTORY should return false", result);
    }

    /**
     * Test case 8: Using the constructor for failed parsing.
     * This constructor also sets the type to UNKNOWN_TYPE internally.
     */
    @Test
    public void testIsUnknown_InvalidFileConstructor_ShouldReturnTrue() {
        // Arrange
        FTPFile invalidFile = new FTPFile("some raw listing that failed parsing");
        // Act
        boolean result = invalidFile.isUnknown();
        // Assert
        assertTrue("FTPFile created with raw listing constructor should be UNKNOWN type", result);
        // Also check validity if needed (though not directly related to isUnknown)
        assertFalse("FTPFile created with raw listing constructor should be invalid", invalidFile.isValid());
    }

    /**
     * Test case 9: Setting other properties shouldn't affect isUnknown result based on type.
     * (Type remains the default UNKNOWN)
     */
    @Test
    public void testIsUnknown_WithOtherPropertiesSet_ShouldReturnTrue() {
        // Arrange
        ftpFile.setName("test.txt");
        ftpFile.setSize(1024);
        ftpFile.setUser("testuser");
        // Type is still default UNKNOWN
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertTrue("Setting other properties should not affect isUnknown when type is UNKNOWN", result);
    }

    /**
     * Test case 10: Setting other properties shouldn't affect isUnknown result based on type.
     * (Type is explicitly set to FILE)
     */
    @Test
    public void testIsUnknown_WithOtherPropertiesSetAndTypeFile_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setName("test.txt");
        ftpFile.setSize(1024);
        ftpFile.setUser("testuser");
        // Act
        boolean result = ftpFile.isUnknown();
        // Assert
        assertFalse("Setting other properties should not affect isUnknown when type is FILE", result);
    }
}