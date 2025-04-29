import org.junit.Before;
import org.junit.Test; // Ensure JUnit 4 @Test is imported
import static org.junit.Assert.*;

/**
 * Test class for FTPFile#setRawListing(String).
 * Focuses on verifying that the setRawListing method correctly stores
 * the provided string, which can then be retrieved using getRawListing.
 * Uses JUnit 4 annotations and assertions.
 */
public class FTPFile_setRawListingTest {

	private FTPFile ftpFile;

	@Before
	public void setUp() {
		// Setup a fresh FTPFile instance before each test
		ftpFile = new FTPFile();
	}

	/**
	 * Test case 1: Setting a typical, non-empty raw listing string.
	 */
	@Test
	public void testSetRawListing_Typical() {
		// Arrange
		String expectedListing = "drwxr-xr-x   2 ftp      ftp          4096 May 15 10:00 public_html";

		// Act
		ftpFile.setRawListing(expectedListing);
		String actualListing = ftpFile.getRawListing();

		// Assert
		assertEquals("Typical raw listing should be set and retrieved correctly.", expectedListing, actualListing);
	}

	/**
	 * Test case 2: Setting an empty string as the raw listing.
	 */
	@Test
	public void testSetRawListing_EmptyString() {
		// Arrange
		String expectedListing = "";

		// Act
		ftpFile.setRawListing(expectedListing);
		String actualListing = ftpFile.getRawListing();

		// Assert
		assertEquals("Empty raw listing should be set and retrieved correctly.", expectedListing, actualListing);
	}

	/**
	 * Test case 3: Setting null as the raw listing.
	 */
	@Test
	public void testSetRawListing_Null() {
		// Arrange
		String expectedListing = null;

		// Act
		ftpFile.setRawListing(expectedListing);
		String actualListing = ftpFile.getRawListing();

		// Assert
		assertNull("Null raw listing should be set and retrieved correctly.", actualListing);
	}

	/**
	 * Test case 4: Setting a raw listing with leading and trailing whitespace.
	 * The method should preserve the whitespace.
	 */
	@Test
	public void testSetRawListing_WithWhitespace() {
		// Arrange
		String expectedListing = "  -rw-r--r-- 1 user group 1024 Jun 1 12:00 file.txt  ";

		// Act
		ftpFile.setRawListing(expectedListing);
		String actualListing = ftpFile.getRawListing();

		// Assert
		assertEquals("Raw listing with leading/trailing whitespace should be preserved.", expectedListing, actualListing);
	}

	/**
	 * Test case 5: Setting a raw listing containing special characters (e.g., newline, tab).
	 */
	@Test
	public void testSetRawListing_WithSpecialChars() {
		// Arrange
		String expectedListing = "line1\nline2\tdata";

		// Act
		ftpFile.setRawListing(expectedListing);
		String actualListing = ftpFile.getRawListing();

		// Assert
		assertEquals("Raw listing with special characters should be stored as is.", expectedListing, actualListing);
	}

	/**
	 * Test case 6: Overwriting an existing raw listing with a new one.
	 */
	@Test
	public void testSetRawListing_Overwrite() {
		// Arrange
		String initialListing = "initial listing";
		String expectedListing = "new overwritten listing";
		ftpFile.setRawListing(initialListing); // Set initial value

		// Act
		ftpFile.setRawListing(expectedListing); // Overwrite with new value
		String actualListing = ftpFile.getRawListing();

		// Assert
		assertEquals("Raw listing should be correctly overwritten.", expectedListing, actualListing);
		assertNotEquals("Raw listing should not be the initial value.", initialListing, actualListing);
	}

    /**
     * Test case 7: Setting a raw listing with only whitespace characters.
     */
    @Test
    public void testSetRawListing_WhitespaceOnly() {
        // Arrange
        String expectedListing = "   \t  \n  ";

        // Act
        ftpFile.setRawListing(expectedListing);
        String actualListing = ftpFile.getRawListing();

        // Assert
        assertEquals("Raw listing containing only whitespace should be set correctly.", expectedListing, actualListing);
    }

    /**
     * Test case 8: Setting a relatively long raw listing string.
     */
    @Test
    public void testSetRawListing_LongString() {
        // Arrange
        String expectedListing = "drwxrwxrwx   1 owner    group    4194304000 Jan  1  1970 ThisIsAVeryLongDirectoryNameUsedForTestingPurposeToEnsureThereAreNoUnexpectedTruncationsOrLengthLimitsAppliedImplicitlyByTheSetterMethodCallInTheFTPFileClassImplementationProvidedInTheContextOfTheProblemDescription";

        // Act
        ftpFile.setRawListing(expectedListing);
        String actualListing = ftpFile.getRawListing();

        // Assert
        assertEquals("Long raw listing should be handled correctly without modification.", expectedListing, actualListing);
    }

    /**
     * Test case 9: Setting the raw listing back to null after setting a value.
     */
    @Test
    public void testSetRawListing_SetToNullAfterValue() {
        // Arrange
        String initialListing = "some listing data";
        ftpFile.setRawListing(initialListing); // Set initial value
        assertNotNull("Initial listing should not be null", ftpFile.getRawListing());

        // Act
        ftpFile.setRawListing(null); // Set back to null
        String actualListing = ftpFile.getRawListing();

        // Assert
        assertNull("Raw listing should be null after being explicitly set to null.", actualListing);
    }

    /**
     * Test case 10: Setting the raw listing on an instance created via the invalid parse constructor.
     * This checks if the method works independently of the file's validity state.
     */
    @Test
    public void testSetRawListing_OnInvalidInstance() {
        // Arrange
        String initialRawListing = "--- invalid raw listing ---";
        FTPFile invalidFile = new FTPFile(initialRawListing); // Creates an 'invalid' FTPFile
        String expectedListing = "new listing for invalid file";

        // Act
        invalidFile.setRawListing(expectedListing);
        String actualListing = invalidFile.getRawListing();

        // Assert
        assertEquals("Setting raw listing should work even on an instance marked as invalid.", expectedListing, actualListing);
        assertFalse("Instance should remain invalid after setting raw listing.", invalidFile.isValid());
    }

}