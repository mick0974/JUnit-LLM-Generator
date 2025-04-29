import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import these specifically for setLink testing, 
// unless used in setup which isn't necessary here.
// import java.io.Serializable;
// import java.util.Calendar;
// import java.util.Date;
// import java.util.Formatter;
// import java.util.TimeZone;

/**
 * Test class for the {@link FTPFile#setLink(String)} method.
 * It contains 10 unit test cases covering typical and edge scenarios.
 * Tests follow the Arrange-Act-Assert pattern.
 */
public class FTPFile_setLinkTest {

	private FTPFile ftpFile;

	/**
	 * Sets up a new FTPFile instance before each test.
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		// Ensure the initial state has a null link
		assertNull("FTPFile link should be null initially", ftpFile.getLink());
	}

	/**
	 * Cleans up resources after each test (optional here, good practice).
	 */
	@After
	public void tearDown() {
		ftpFile = null;
	}

	/**
	 * Test case 1: Set a typical, non-empty link string.
	 */
	@Test
	public void testSetLinkWithNonNullValue() {
		// Arrange
		String expectedLink = "target_file.txt";

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should be set to the provided non-null value", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 2: Set the link to null.
	 */
	@Test
	public void testSetLinkWithNullValue() {
		// Arrange
		// Set an initial value to ensure it changes
		ftpFile.setLink("initial_link");
		assertEquals("Link should have initial value before setting to null", "initial_link", ftpFile.getLink());

		// Act
		ftpFile.setLink(null);

		// Assert
		assertNull("Link should be null after setting to null", ftpFile.getLink());
	}

	/**
	 * Test case 3: Set the link to an empty string.
	 */
	@Test
	public void testSetLinkWithEmptyString() {
		// Arrange
		String expectedLink = "";

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should be set to the empty string", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 4: Set a link string containing path separators.
	 */
	@Test
	public void testSetLinkWithPathSeparators() {
		// Arrange
		String expectedLink = "/path/to/some/target_dir/";

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should correctly store path separators", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 5: Set a link string containing spaces.
	 */
	@Test
	public void testSetLinkWithSpaces() {
		// Arrange
		String expectedLink = "target file with spaces";

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should correctly store spaces", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 6: Set a link string containing special characters.
	 */
	@Test
	public void testSetLinkWithSpecialCharacters() {
		// Arrange
		String expectedLink = "target_!@#$%^&*()_+-={}|[]\\:\";'<>?,./~`";

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should correctly store various special characters", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 7: Set the link multiple times, verifying the last value is stored.
	 */
	@Test
	public void testSetLinkOverwritesPreviousValue() {
		// Arrange
		String initialLink = "old_link.txt";
		String finalLink = "new_link.log";

		// Act
		ftpFile.setLink(initialLink);
		assertEquals("Link should hold the initial value", initialLink, ftpFile.getLink());

		ftpFile.setLink(finalLink);

		// Assert
		assertEquals("Link should be overwritten with the final value", finalLink, ftpFile.getLink());
	}

	/**
	 * Test case 8: Set the link when the FTPFile type is explicitly set to FILE_TYPE.
	 * Although intended for SYMBOLIC_LINK_TYPE, setLink itself doesn't check the type.
	 */
	@Test
	public void testSetLinkOnFileWithTypeFile() {
		// Arrange
		ftpFile.setType(FTPFile.FILE_TYPE);
		String expectedLink = "link_set_on_file";
		assertTrue("FTPFile type should be FILE", ftpFile.isFile());

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should still be set even if type is FILE_TYPE", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 9: Set the link when the FTPFile type is explicitly set to DIRECTORY_TYPE.
	 */
	@Test
	public void testSetLinkOnFileWithTypeDirectory() {
		// Arrange
		ftpFile.setType(FTPFile.DIRECTORY_TYPE);
		String expectedLink = "link_set_on_directory";
		assertTrue("FTPFile type should be DIRECTORY", ftpFile.isDirectory());

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should still be set even if type is DIRECTORY_TYPE", expectedLink, ftpFile.getLink());
	}

	/**
	 * Test case 10: Set the link when the FTPFile type is explicitly set to SYMBOLIC_LINK_TYPE (intended use case).
	 */
	@Test
	public void testSetLinkOnFileWithTypeSymbolicLink() {
		// Arrange
		ftpFile.setType(FTPFile.SYMBOLIC_LINK_TYPE);
		String expectedLink = "symlink_target";
		assertTrue("FTPFile type should be SYMBOLIC_LINK", ftpFile.isSymbolicLink());

		// Act
		ftpFile.setLink(expectedLink);

		// Assert
		assertEquals("Link should be set correctly for SYMBOLIC_LINK_TYPE", expectedLink, ftpFile.getLink());
	}
}