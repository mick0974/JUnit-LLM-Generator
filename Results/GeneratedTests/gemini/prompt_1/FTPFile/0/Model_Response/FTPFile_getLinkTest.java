```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import everything from FTPFile.java, just the class itself
// import java.io.Serializable; // Not needed for test
// import java.util.Calendar; // Not needed for this specific method test
// import java.util.Date; // Not needed for test
// import java.util.Formatter; // Not needed for test
// import java.util.TimeZone; // Not needed for test

/**
 * Test class for {@link FTPFile#getLink()}.
 * Contains 10 unit test cases focusing on retrieving the link target
 * under various conditions, adhering to the Arrange-Act-Assert pattern.
 */
public class FTPFile_getLinkTest {

    private FTPFile ftpFile;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Initializes a new FTPFile instance.
     */
    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     * Cleans up the FTPFile instance.
     */
    @After
    public void tearDown() {
        ftpFile = null;
    }

    /**
     * Test case 1: File type is SYMBOLIC_LINK_TYPE and link is set to a non-empty string.
     * Expected: Returns the set link string.
     */
    @Test
    public void testGetLink_SymbolicLink_ValidLink() {
        // Arrange
        String expectedLink = "target_file.txt";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the set link for a symbolic link", expectedLink, actualLink);
    }

    /**
     * Test case 2: File type is SYMBOLIC_LINK_TYPE but the link is explicitly set to null.
     * Expected: Returns null.
     */
    @Test
    public void testGetLink_SymbolicLink_NullLink() {
        // Arrange
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(null);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null when the link is explicitly set to null", actualLink);
    }

    /**
     * Test case 3: File type is SYMBOLIC_LINK_TYPE and the link is set to an empty string.
     * Expected: Returns the empty string.
     */
    @Test
    public void testGetLink_SymbolicLink_EmptyLink() {
        // Arrange
        String expectedLink = "";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return an empty string when the link is set to empty", expectedLink, actualLink);
    }

    /**
     * Test case 4: File type is FILE_TYPE (not a symbolic link) and no link is set.
     * Expected: Returns null.
     */
    @Test
    public void testGetLink_FileType_NoLinkSet() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE);
        // ftpFile.setLink(null); // Link is null by default

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for a regular file type when no link is set", actualLink);
    }

    /**
     * Test case 5: File type is DIRECTORY_TYPE (not a symbolic link) and no link is set.
     * Expected: Returns null.
     */
    @Test
    public void testGetLink_DirectoryType_NoLinkSet() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        // ftpFile.setLink(null); // Link is null by default

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for a directory type when no link is set", actualLink);
    }

    /**
     * Test case 6: File type is UNKNOWN_TYPE (not a symbolic link) and no link is set.
     * This also covers the default state of a newly instantiated FTPFile.
     * Expected: Returns null.
     */
    @Test
    public void testGetLink_UnknownType_NoLinkSet() {
        // Arrange
        // ftpFile is UNKNOWN_TYPE and link is null by default constructor

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for an unknown type when no link is set (default state)", actualLink);
    }

    /**
     * Test case 7: File type is FILE_TYPE, but a link is incorrectly set.
     * The getLink() method simply returns the _link field regardless of type.
     * Expected: Returns the incorrectly set link string.
     */
    @Test
    public void testGetLink_FileType_LinkSet() {
        // Arrange
        String unexpectedLink = "/path/to/something";
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setLink(unexpectedLink); // Setting link even though it's not a symlink

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the set link even if type is not SYMBOLIC_LINK", unexpectedLink, actualLink);
    }

    /**
     * Test case 8: File type is DIRECTORY_TYPE, but a link is incorrectly set.
     * The getLink() method simply returns the _link field regardless of type.
     * Expected: Returns the incorrectly set link string.
     */
    @Test
    public void testGetLink_DirectoryType_LinkSet() {
        // Arrange
        String unexpectedLink = "another_target";
        ftpFile.setType(FTPFile.DIRECTORY_TYPE);
        ftpFile.setLink(unexpectedLink); // Setting link even though it's not a symlink

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the set link even if type is not SYMBOLIC_LINK", unexpectedLink, actualLink);
    }
     
    /**
     * Test case 9: File type is SYMBOLIC_LINK_TYPE and link contains special characters or path elements.
     * Expected: Returns the exact link string as set.
     */
    @Test
    public void testGetLink_SymbolicLink_ComplexLink() {
        // Arrange
        String expectedLink = "../complex/path with spaces/file?query#fragment";
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
        ftpFile.setLink(expectedLink);

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Should return the complex link string exactly as set", expectedLink, actualLink);
    }

    /**
     * Test case 10: Default state of FTPFile created via default constructor.
     * Type is UNKNOWN, link is null.
     * Expected: Returns null. (Similar to testGetLink_UnknownType_NoLinkSet, but explicitly tests default constructor state).
     */
    @Test
    public void testGetLink_DefaultConstructorState() {
        // Arrange
        // ftpFile is created in setUp() using the default constructor

        // Act
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Should return null for a default FTPFile instance", actualLink);
        assertEquals("Default type should be UNKNOWN", FTPFile.UNKNOWN_TYPE, ftpFile.getType());
    }
}
```