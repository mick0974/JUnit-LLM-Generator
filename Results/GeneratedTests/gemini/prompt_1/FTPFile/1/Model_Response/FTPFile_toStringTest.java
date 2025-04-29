```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Test class for FTPFile#toString().
 * It contains 10 unit test cases focusing on the behavior of the
 * public String toString() method.
 * Tests follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 */
public class FTPFile_toStringTest {

    private FTPFile ftpFile;

    @Before
    public void setUp() {
        // Initialize a new FTPFile instance before each test
        ftpFile = new FTPFile();
    }

    // Test case 1: Typical raw listing
    @Test
    public void testToString_WithTypicalRawListing() {
        // Arrange
        String rawListing = "drwxr-xr-x   2 ftp      ftp          4096 Mar 15 10:00 public_html";
        ftpFile.setRawListing(rawListing);

        // Act
        String result = ftpFile.toString();

        // Assert
        assertEquals("toString() should return the exact raw listing set.", rawListing, result);
    }

    // Test case 2: Raw listing with file details
    @Test
    public void testToString_WithFileRawListing() {
        // Arrange
        String rawListing = "-rw-r--r--   1 user     group     1024 Sep 30 15:30 document.txt";
        ftpFile.setRawListing(rawListing);

        // Act
        String result = ftpFile.toString();

        // Assert
        assertEquals("toString() should return the file's raw listing.", rawListing, result);
    }

    // Test case 3: Raw listing for a symbolic link
    @Test
    public void testToString_WithSymlinkRawListing() {
        // Arrange
        String rawListing = "lrwxrwxrwx   1 user     group        25 Oct 01 09:00 link -> target";
        ftpFile.setRawListing(rawListing);

        // Act
        String result = ftpFile.toString();

        // Assert
        assertEquals("toString() should return the symlink's raw listing.", rawListing, result);
    }

    // Test case 4: Empty string as raw listing
    @Test
    public void testToString_WithEmptyRawListing() {
        // Arrange
        String rawListing = "";
        ftpFile.setRawListing(rawListing);

        // Act
        String result = ftpFile.toString();

        // Assert
        assertEquals("toString() should return an empty string if set.", rawListing, result);
    }

    // Test case 5: Null raw listing (default state after default constructor)
    @Test
    public void testToString_WithNullRawListing_DefaultConstructor() {
        // Arrange
        // ftpFile is created with default constructor, _rawListing is null

        // Act
        String result = ftpFile.toString();

        // Assert
        assertNull("toString() should return null if raw listing was never set.", result);
    }

    // Test case 6: Setting raw listing to null explicitly
    @Test
    public void testToString_WithExplicitlyNullRawListing() {
        // Arrange
        ftpFile.setRawListing("some initial listing"); // Set non-null first
        ftpFile.setRawListing(null); // Set to null

        // Act
        String result = ftpFile.toString();

        // Assert
        assertNull("toString() should return null if raw listing is explicitly set to null.", result);
    }

    // Test case 7: Raw listing containing only whitespace
    @Test
    public void testToString_WithWhitespaceRawListing() {
        // Arrange
        String rawListing = "   \t   \n ";
        ftpFile.setRawListing(rawListing);

        // Act
        String result = ftpFile.toString();

        // Assert
        assertEquals("toString() should return the whitespace string if set.", rawListing, result);
    }

    // Test case 8: Raw listing with special characters
    @Test
    public void testToString_WithSpecialCharsRawListing() {
        // Arrange
        String rawListing = "file with spaces & special chars like !@#$%^&*()_+=-`~";
        ftpFile.setRawListing(rawListing);

        // Act
        String result = ftpFile.toString();

        // Assert
        assertEquals("toString() should return the raw listing with special characters.", rawListing, result);
    }

    // Test case 9: Raw listing set via the "invalid" constructor
    @Test
    public void testToString_WithInvalidConstructor() {
        // Arrange
        String rawListing = "This is an unparseable raw listing entry.";
        FTPFile invalidFile = new FTPFile(rawListing); // Use the constructor taking rawListing

        // Act
        String result = invalidFile.toString();

        // Assert
        assertEquals("toString() should return the raw listing provided to the invalid constructor.", rawListing, result);
    }

    // Test case 10: Calling toString() after setting other properties but rawListing remains default (null)
    @Test
    public void testToString_AfterSettingOtherProperties() {
        // Arrange
        // ftpFile created with default constructor, _rawListing is null
        ftpFile.setName("test.file");
        ftpFile.setSize(12345);
        ftpFile.setType(FTPFile.FILE_TYPE);
        ftpFile.setTimestamp(Calendar.getInstance());

        // Act
        String result = ftpFile.toString();

        // Assert
        assertNull("toString() should still return null if raw listing was never set, even if other properties are set.", result);
    }
}
```