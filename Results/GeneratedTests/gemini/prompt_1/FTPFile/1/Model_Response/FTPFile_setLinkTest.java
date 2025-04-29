```java
import org.junit.Before;
// No @After needed for these simple tests
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import these for the test class itself,
// unless we were creating complex FTPFile instances,
// which isn't necessary for testing setLink/getLink.
// import java.io.Serializable;
// import java.util.Calendar;
// import java.util.Date;
// import java.util.Formatter;
// import java.util.TimeZone;

/**
 * Test class for FTPFile#setLink(String link).
 * It contains 10 unit test cases focusing on setting and retrieving
 * the link target for an FTPFile instance.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class FTPFile_setLinkTest {

    private FTPFile ftpFile;

    /**
     * Sets up a new FTPFile instance before each test.
     */
    @Before
    public void setUp() {
        // Arrange: Create a new FTPFile instance for each test
        ftpFile = new FTPFile();
        // Optional: Set type to symbolic link, although setLink doesn't enforce this
        ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
    }

    /**
     * Test case 1: Set a typical, non-empty, non-null link string.
     */
    @Test
    public void testSetLink_TypicalValue() {
        // Arrange
        String expectedLink = "target_file.txt";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should be the value that was set.", expectedLink, actualLink);
    }

    /**
     * Test case 2: Set the link to null.
     */
    @Test
    public void testSetLink_NullValue() {
        // Arrange
        // Set an initial value to ensure null overwrites it
        ftpFile.setLink("initial_link");

        // Act
        ftpFile.setLink(null);
        String actualLink = ftpFile.getLink();

        // Assert
        assertNull("Link should be null after setting it to null.", actualLink);
    }

    /**
     * Test case 3: Set the link to an empty string.
     */
    @Test
    public void testSetLink_EmptyString() {
        // Arrange
        String expectedLink = "";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should be an empty string.", expectedLink, actualLink);
    }

    /**
     * Test case 4: Set the link to a string containing only whitespace.
     */
    @Test
    public void testSetLink_WhitespaceString() {
        // Arrange
        String expectedLink = "   ";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should contain only whitespace.", expectedLink, actualLink);
    }

    /**
     * Test case 5: Set the link to a string representing an absolute path.
     */
    @Test
    public void testSetLink_AbsolutePath() {
        // Arrange
        String expectedLink = "/path/to/resource";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should store absolute path.", expectedLink, actualLink);
    }

    /**
     * Test case 6: Set the link to a string representing a relative path.
     */
    @Test
    public void testSetLink_RelativePath() {
        // Arrange
        String expectedLink = "../another/directory/link";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should store relative path.", expectedLink, actualLink);
    }

    /**
     * Test case 7: Set the link to a string containing special characters.
     */
    @Test
    public void testSetLink_SpecialCharacters() {
        // Arrange
        String expectedLink = "link?name=value&v=1#fragment";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should store string with special characters.", expectedLink, actualLink);
    }

    /**
     * Test case 8: Set the link multiple times and verify the last value is retained.
     */
    @Test
    public void testSetLink_OverwritePreviousLink() {
        // Arrange
        String initialLink = "first_link";
        String finalLink = "second_link";

        // Act
        ftpFile.setLink(initialLink); // Set initial value
        ftpFile.setLink(finalLink);   // Overwrite with final value
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Link should be the last value set.", finalLink, actualLink);
        assertNotEquals("Link should not be the initial value.", initialLink, actualLink);
    }

    /**
     * Test case 9: Set the link on an FTPFile instance initially marked as FILE_TYPE.
     * The setLink method itself doesn't validate the type.
     */
    @Test
    public void testSetLink_OnFileType() {
        // Arrange
        ftpFile.setType(FTPFile.FILE_TYPE); // Override the @Before setup
        String expectedLink = "link_on_file_type";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Setting link should work regardless of file type.", expectedLink, actualLink);
        assertEquals("File type should remain unchanged.", FTPFile.FILE_TYPE, ftpFile.getType());
    }

    /**
     * Test case 10: Set the link on an FTPFile instance initially marked as DIRECTORY_TYPE.
     * The setLink method itself doesn't validate the type.
     */
    @Test
    public void testSetLink_OnDirectoryType() {
        // Arrange
        ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Override the @Before setup
        String expectedLink = "link_on_directory_type";

        // Act
        ftpFile.setLink(expectedLink);
        String actualLink = ftpFile.getLink();

        // Assert
        assertEquals("Setting link should work regardless of directory type.", expectedLink, actualLink);
        assertEquals("Directory type should remain unchanged.", FTPFile.DIRECTORY_TYPE, ftpFile.getType());
    }
}
```