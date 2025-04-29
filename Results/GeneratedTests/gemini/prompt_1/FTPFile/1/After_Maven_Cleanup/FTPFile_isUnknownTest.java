import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the FTPFile#isUnknown() method.
 * It verifies the behavior of the method under various conditions,
 * ensuring it correctly identifies files of UNKNOWN_TYPE.
 * Tests follow the Arrange-Act-Assert pattern.
 */
public class FTPFile_isUnknownTest {

    private FTPFile ftpFile;

    @Before
    public void setUp() {
        // Arrange: Create a new FTPFile instance before each test
        ftpFile = new FTPFile();
    }

    /**
     * Test case 1: Verify isUnknown() returns true when type is explicitly set to UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_WhenTypeIsUnknown_ShouldReturnTrue() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE);

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertTrue("isUnknown should return true when type is UNKNOWN_TYPE", result);
    }

    /**
     * Test case 2: Verify isUnknown() returns false when type is set to FILE_TYPE.
     */
    @Test
    public void testIsUnknown_WhenTypeIsFile_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertFalse("isUnknown should return false when type is FILE_TYPE", result);
    }

    /**
     * Test case 3: Verify isUnknown() returns false when type is set to DIRECTORY_TYPE.
     */
    @Test
    public void testIsUnknown_WhenTypeIsDirectory_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertFalse("isUnknown should return false when type is DIRECTORY_TYPE", result);
    }

    /**
     * Test case 4: Verify isUnknown() returns false when type is set to SYMBOLIC_LINK_TYPE.
     */
    @Test
    public void testIsUnknown_WhenTypeIsSymbolicLink_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertFalse("isUnknown should return false when type is SYMBOLIC_LINK_TYPE", result);
    }

    /**
     * Test case 5: Verify isUnknown() returns true for a default FTPFile instance.
     * The default constructor initializes the type to UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_WhenDefaultConstructor_ShouldReturnTrue() {
        // Arrange: ftpFile is already created in setUp with the default constructor

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertTrue("isUnknown should return true for a default FTPFile instance", result);
    }

    /**
     * Test case 6: Verify isUnknown() returns true after changing type from File to Unknown.
     */
    @Test
    public void testIsUnknown_WhenTypeChangedToUnknown_ShouldReturnTrue() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE); // Initial type
        assertFalse("Precondition failed: isUnknown should be false initially", ftpFile.isUnknown());
        ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Change type

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertTrue("isUnknown should return true after type is changed to UNKNOWN_TYPE", result);
    }

    /**
     * Test case 7: Verify isUnknown() returns false after changing type from Unknown to Directory.
     */
    @Test
    public void testIsUnknown_WhenTypeChangedFromUnknown_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Initial type (or default)
        assertTrue("Precondition failed: isUnknown should be true initially", ftpFile.isUnknown());
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Change type

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertFalse("isUnknown should return false after type is changed from UNKNOWN_TYPE to DIRECTORY_TYPE", result);
    }

    /**
     * Test case 8: Verify isUnknown() returns true when using the constructor for invalid entries.
     * This constructor sets the type to UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_WhenInvalidEntryConstructor_ShouldReturnTrue() {
        // Arrange
        FTPFile invalidFile = new FTPFile("This is a raw listing that failed parsing");

        // Act
        boolean result = invalidFile.isUnknown();

        // Assert
        assertTrue("isUnknown should return true for an FTPFile created with the invalid entry constructor", result);
        assertFalse("isValid should return false for an invalid entry", invalidFile.isValid());
    }

    /**
     * Test case 9: Test with a numerically different value, not one of the defined TYPE constants.
     * While not standard usage, isUnknown should return false if the type is not explicitly UNKNOWN_TYPE.
     */
    @Test
    public void testIsUnknown_WhenTypeIsArbitraryValue_ShouldReturnFalse() {
        // Arrange
        ftpFile.setType(99); // An arbitrary value different from UNKNOWN_TYPE

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertFalse("isUnknown should return false when type is an arbitrary non-UNKNOWN_TYPE value", result);
    }

     /**
     * Test case 10: Re-setting type to UNKNOWN_TYPE after setting it to something else.
     */
    @Test
    public void testIsUnknown_WhenTypeResetToUnknown_ShouldReturnTrue() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        assertFalse("Precondition failed: isUnknown should be false after setting type to FILE", ftpFile.isUnknown());
        ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Reset to Unknown

        // Act
        boolean result = ftpFile.isUnknown();

        // Assert
        assertTrue("isUnknown should return true after resetting type to UNKNOWN_TYPE", result);
    }
}