```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import java.* classes unless used directly in the test logic,
// which is not the case here. We only need the class under test.
// Importing FTPFile is sufficient.
import FTPFile; // Assuming FTPFile is in the default package or appropriately imported

/**
 * Test class for FTPFile#isSymbolicLink().
 * Contains unit test cases covering different scenarios for the
 * isSymbolicLink() method, following the Arrange-Act-Assert pattern.
 */
public class FTPFile_isSymbolicLinkTest {

	private FTPFile ftpFile;

	/**
	 * Sets up the test fixture.
	 * Called before every test case method.
	 */
	@Before
	public void setUp() {
		// Arrange: Create a new FTPFile instance before each test
		ftpFile = new FTPFile();
	}

	/**
	 * Tears down the test fixture.
	 * Called after every test case method.
	 */
	@After
	public void tearDown() {
		// Clean up resources if necessary (not needed for this simple case)
		ftpFile = null;
	}

	/**
	 * Test case 1: Type is explicitly set to SYMBOLIC_LINK_TYPE.
	 * Expected: isSymbolicLink() should return true.
	 */
	@Test
	public void testIsSymbolicLinkWhenTypeIsSymbolicLink() {
		// Arrange
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertTrue("isSymbolicLink() should return true when type is SYMBOLIC_LINK_TYPE", result);
	}

	/**
	 * Test case 2: Type is explicitly set to FILE_TYPE.
	 * Expected: isSymbolicLink() should return false.
	 */
	@Test
	public void testIsSymbolicLinkWhenTypeIsFile() {
		// Arrange
		ftpFile.setType(FTPFile.FILE_TYPE);

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false when type is FILE_TYPE", result);
	}

	/**
	 * Test case 3: Type is explicitly set to DIRECTORY_TYPE.
	 * Expected: isSymbolicLink() should return false.
	 */
	@Test
	public void testIsSymbolicLinkWhenTypeIsDirectory() {
		// Arrange
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false when type is DIRECTORY_TYPE", result);
	}

	/**
	 * Test case 4: Type is explicitly set to UNKNOWN_TYPE.
	 * Expected: isSymbolicLink() should return false.
	 */
	@Test
	public void testIsSymbolicLinkWhenTypeIsUnknown() {
		// Arrange
		ftpFile.setType(FTPFile.UNKNOWN_TYPE);

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false when type is UNKNOWN_TYPE", result);
	}

	/**
	 * Test case 5: Type is left as the default (initialized to UNKNOWN_TYPE in the constructor).
	 * Expected: isSymbolicLink() should return false.
	 */
	@Test
	public void testIsSymbolicLinkWhenTypeIsDefault() {
		// Arrange: ftpFile is already initialized with default type in setUp()

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false for the default type (UNKNOWN_TYPE)", result);
	}

	/**
	 * Test case 6: File object created using the invalid entry constructor.
	 * Type defaults to UNKNOWN_TYPE.
	 * Expected: isSymbolicLink() should return false.
	 */
	@Test
	public void testIsSymbolicLinkForInvalidEntry() {
		// Arrange
		FTPFile invalidFile = new FTPFile("This is an unparseable raw listing");
		// Note: The type _type defaults to UNKNOWN_TYPE in this constructor

		// Act
		boolean result = invalidFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false for an invalid entry (default UNKNOWN_TYPE)", result);
		assertFalse("isValid() should return false for an invalid entry", invalidFile.isValid());
	}

	/**
	 * Test case 7: Type is changed from DIRECTORY to SYMBOLIC_LINK.
	 * Expected: isSymbolicLink() should return true after the change.
	 */
	@Test
	public void testIsSymbolicLinkAfterTypeChangeToSymbolicLink() {
		// Arrange
		ftpFile.setType(FTPFile.DIRECTORY_TYPE); // Initial type
		assertFalse("Precondition: Should not be symbolic link initially", ftpFile.isSymbolicLink());
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE); // Change type

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertTrue("isSymbolicLink() should return true after type changed to SYMBOLIC_LINK_TYPE", result);
	}

	/**
	 * Test case 8: Type is changed from SYMBOLIC_LINK to FILE.
	 * Expected: isSymbolicLink() should return false after the change.
	 */
	@Test
	public void testIsSymbolicLinkAfterTypeChangeFromSymbolicLink() {
		// Arrange
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE); // Initial type
		assertTrue("Precondition: Should be symbolic link initially", ftpFile.isSymbolicLink());
		ftpFile.setType(FTPFile.FILE_TYPE); // Change type

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false after type changed from SYMBOLIC_LINK_TYPE", result);
	}

	/**
	 * Test case 9: Set type to SYMBOLIC_LINK_TYPE and also set the link name.
	 * The presence of a link name should not affect isSymbolicLink().
	 * Expected: isSymbolicLink() should return true.
	 */
	@Test
	public void testIsSymbolicLinkWhenLinkNameIsSet() {
		// Arrange
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		ftpFile.setLink("target_file"); // Set link attribute

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertTrue("isSymbolicLink() should return true even when link name is set", result);
		assertEquals("target_file", ftpFile.getLink()); // Verify link name was set correctly
	}

	/**
	 * Test case 10: Set type to DIRECTORY_TYPE but also set the link name (inconsistent state).
	 * The method should rely only on the _type field.
	 * Expected: isSymbolicLink() should return false.
	 */
	@Test
	public void testIsSymbolicLinkWhenTypeIsDirectoryButLinkNameIsSet() {
		// Arrange
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		ftpFile.setLink("inconsistent_link"); // Set link attribute, even though it's a directory

		// Act
		boolean result = ftpFile.isSymbolicLink();

		// Assert
		assertFalse("isSymbolicLink() should return false if type is DIRECTORY, regardless of link name", result);
		assertEquals("inconsistent_link", ftpFile.getLink()); // Verify link name was set correctly
	}
}
```