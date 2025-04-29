import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Test class for the FTPFile#isValid() method.
 * Focuses on verifying the validity state based on how the FTPFile instance
 * was constructed or potentially modified.
 * Follows the Arrange-Act-Assert pattern.
 */
public class FTPFile_isValidTest {

    // No specific setup needed for most tests, but defining for structure
    @Before
    public void setUp() throws Exception {
        // Setup tasks, if any, before each test
    }

    // No specific teardown needed, but defining for structure
    @After
    public void tearDown() throws Exception {
        // Cleanup tasks, if any, after each test
    }

    /**
     * Test case 1: An FTPFile created using the default constructor should be valid.
     * The default constructor initializes the _permissions array.
     */
    @Test
    public void testIsValid_whenCreatedWithDefaultConstructor_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile();

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("FTPFile created with default constructor should be valid", isValid);
    }

    /**
     * Test case 2: An FTPFile created using the constructor for failed parses
     * (taking a raw listing string) should be invalid.
     * This constructor explicitly sets _permissions to null.
     */
    @Test
    public void testIsValid_whenCreatedWithRawListingConstructor_shouldReturnFalse() {
        // Arrange
        String rawListing = "drwxr-xr-x 1 user group 4096 Jan 1 10:00 directory"; // Example listing
        FTPFile file = new FTPFile(rawListing); // This constructor signifies a parse failure internally

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertFalse("FTPFile created with raw listing constructor should be invalid", isValid);
    }

    /**
     * Test case 3: An FTPFile created with the raw listing constructor using a null string
     * should still be invalid.
     */
    @Test
    public void testIsValid_whenCreatedWithNullRawListing_shouldReturnFalse() {
        // Arrange
        FTPFile file = new FTPFile(null); // Pass null to the specific constructor

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertFalse("FTPFile created with null raw listing constructor should be invalid", isValid);
    }

    /**
     * Test case 4: A valid FTPFile (default constructor) remains valid after setting its name.
     * Modifying basic properties should not affect validity status.
     */
    @Test
    public void testIsValid_afterSetNameOnValidFile_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile(); // Starts valid
        file.setName("testfile.txt");

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("Setting name on a valid file should not make it invalid", isValid);
    }

    /**
     * Test case 5: A valid FTPFile (default constructor) remains valid after setting its size.
     * Modifying basic properties should not affect validity status.
     */
    @Test
    public void testIsValid_afterSetSizeOnValidFile_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile(); // Starts valid
        file.setSize(1024L);

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("Setting size on a valid file should not make it invalid", isValid);
    }

    /**
     * Test case 6: A valid FTPFile (default constructor) remains valid after setting its type.
     * Modifying basic properties should not affect validity status.
     */
    @Test
    public void testIsValid_afterSetTypeOnValidFile_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile(); // Starts valid
        file.setType(FTPFile.FILE_TYPE);

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("Setting type on a valid file should not make it invalid", isValid);
    }

    /**
     * Test case 7: A valid FTPFile (default constructor) remains valid after setting its timestamp.
     * Modifying basic properties should not affect validity status.
     */
    @Test
    public void testIsValid_afterSetTimestampOnValidFile_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile(); // Starts valid
        file.setTimestamp(Calendar.getInstance());

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("Setting timestamp on a valid file should not make it invalid", isValid);
    }

    /**
     * Test case 8: A valid FTPFile (default constructor) remains valid after setting user/group.
     * Modifying basic properties should not affect validity status.
     */
    @Test
    public void testIsValid_afterSetUserGroupOnValidFile_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile(); // Starts valid
        file.setUser("testuser");
        file.setGroup("testgroup");

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("Setting user/group on a valid file should not make it invalid", isValid);
    }

    /**
     * Test case 9: A valid FTPFile (default constructor) remains valid after setting permissions.
     * Setting permissions uses the _permissions array, which is already non-null.
     */
    @Test
    public void testIsValid_afterSetPermissionOnValidFile_shouldReturnTrue() {
        // Arrange
        FTPFile file = new FTPFile(); // Starts valid
        file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
        file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertTrue("Setting permissions on a valid file should not make it invalid", isValid);
    }

     /**
     * Test case 10: An invalid FTPFile (raw listing constructor) remains invalid
     * even if attempts are made to set properties (though setters might behave unexpectedly
     * or be irrelevant if the object is invalid). The core check is on _permissions.
     */
    @Test
    public void testIsValid_afterSetPropertiesOnInvalidFile_shouldReturnFalse() {
        // Arrange
        FTPFile file = new FTPFile("invalid raw listing"); // Starts invalid
        file.setName("some_name");
        file.setSize(500);
        // Attempting to set permission would cause NullPointerException if not for isValid check
        // file.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true); // Avoid this

        // Act
        boolean isValid = file.isValid();

        // Assert
        assertFalse("Setting properties on an invalid file should not make it valid", isValid);
    }
}