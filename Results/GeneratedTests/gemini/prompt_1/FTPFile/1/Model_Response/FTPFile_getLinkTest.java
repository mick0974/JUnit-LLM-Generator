```java
import org.junit.Before;
// No @After needed for this specific test class
import org.junit.Test;
import static org.junit.Assert.*;

// Importing FTPFile itself
// Note: Assuming FTPFile is in the same package or properly imported.
// If not, add the correct package declaration.
// import com.example.yourpackage.FTPFile;

/**
 * Test class for the FTPFile#getLink() method.
 * Focuses on verifying the correct link target is returned based on the file type
 * and whether the link has been set.
 * Uses JUnit 4 annotations and assertions.
 */
public class FTPFile_getLinkTest {

    private FTPFile ftpFile;

    /**
     * Set up a new FTPFile instance before each test.
     * This ensures tests are independent.
     */
    @Before
    public void setUp() {
        ftpFile = new FTPFile();
        // Ensure the file starts as valid for most tests
        // (setting permissions array implicitly makes it valid)
        ftpFile.setPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION, true);
    }

    // --- Test Cases ---

    /**
     * Test case 1: File type is SYMBOLIC_LINK and a valid link target is set.
     * Expected: The set link target string.
     */
    @Test
    public void testGetLink_SymbolicLink_ValidLinkSet() {
        // Arrange
        String expectedLink = "/path/to/target/file";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the set link target for a symbolic link", expectedLink, actualLink);
    }

    /**
     * Test case 2: File type is FILE_TYPE.
     * Expected: null, as only symbolic links have link targets.
     */
    @Test
    public void testGetLink_FileType_ShouldReturnNull() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setLink("should_be_ignored"); // Set a link to ensure type takes precedence

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for a regular file type", actualLink);
    }

    /**
     * Test case 3: File type is DIRECTORY_TYPE.
     * Expected: null.
     */
    @Test
    public void testGetLink_DirectoryType_ShouldReturnNull() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setLink("should_also_be_ignored");

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for a directory type", actualLink);
    }

    /**
     * Test case 4: File type is UNKNOWN_TYPE.
     * Expected: null.
     */
    @Test
    public void testGetLink_UnknownType_ShouldReturnNull() {
        // Arrange
        // Type is UNKNOWN_TYPE by default in the constructor used in setUp()
        // ftpFile.setType(FTPFile.UNKNOWN_TYPE); // Redundant but explicit
        ftpFile.setLink("should_be_ignored_too");

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for an unknown file type", actualLink);
    }

    /**
     * Test case 5: File type is SYMBOLIC_LINK, but setLink() was never called.
     * Expected: null (default value of _link field).
     */
    @Test
    public void testGetLink_SymbolicLink_NoLinkSet() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        // Do not call setLink()

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null if link was never set for a symbolic link", actualLink);
    }

    /**
     * Test case 6: File type is SYMBOLIC_LINK, and setLink() was called with null.
     * Expected: null.
     */
    @Test
    public void testGetLink_SymbolicLink_NullLinkSet() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(null);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null if link was explicitly set to null", actualLink);
    }

    /**
     * Test case 7: File type is SYMBOLIC_LINK, and the link target is an empty string.
     * Expected: An empty string.
     */
    @Test
    public void testGetLink_SymbolicLink_EmptyLinkSet() {
        // Arrange
        String expectedLink = "";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return an empty string if the link was set to empty", expectedLink, actualLink);
    }

    /**
     * Test case 8: File type is SYMBOLIC_LINK, and the link target contains special characters.
     * Expected: The string with special characters.
     */
    @Test
    public void testGetLink_SymbolicLink_SpecialCharsLinkSet() {
        // Arrange
        String expectedLink = "target with spaces & symbols %$?/";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the link target including special characters", expectedLink, actualLink);
    }

     /**
     * Test case 9: File type is SYMBOLIC_LINK, and the link target is a relative path.
     * Expected: The relative path string.
     */
    @Test
    public void testGetLink_SymbolicLink_RelativePathLinkSet() {
        // Arrange
        String expectedLink = "../sibling_dir/target_file.txt";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the relative path link target", expectedLink, actualLink);
    }

    /**
     * Test case 10: File type changes from SYMBOLIC_LINK back to FILE_TYPE after setting a link.
     * Expected: null, as the current type (FILE_TYPE) dictates the result.
     */
    @Test
    public void testGetLink_TypeChangedAfterLinkSet_ShouldReturnNull() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink("/path/to/target");
        // Now change the type *after* setting the link
        ftpFile.setType(FTPFile.FILE_TYPE);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null if type changed from symbolic link after link was set", actualLink);
    }

    // Note: Tests related to the 'isValid()' state are less relevant for getLink(),
    // as getLink() itself doesn't check isValid(). It primarily depends on the _type field.
    // An invalid FTPFile constructed via `new FTPFile(rawListing)` cannot have its type or link set easily anyway.
}
```