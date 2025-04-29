// FTPFile_setUserTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

// No need to import everything from FTPFile.java, just the class itself.
// Other imports like Calendar, Date, Formatter, TimeZone are not used in this test class.

/**
 * Test class for FTPFile#setUser(String).
 * It contains 10 unit test cases focusing on setting the user property
 * of an FTPFile object, covering typical and edge cases using the
 * Arrange-Act-Assert pattern.
 */
public class FTPFile_setUserTest {

	private FTPFile ftpFile;

	/**
	 * Sets up a new FTPFile instance before each test method.
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile();
	}

	/**
	 * Cleans up resources after each test method (optional here, but good practice).
	 */
	@After
	public void tearDown() {
		ftpFile = null;
	}

	/**
	 * Test case 1: Set a typical alphanumeric username.
	 */
	@Test
	public void testSetUserWithTypicalUsername() {
		// Arrange
		String expectedUser = "testuser";

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the provided typical username", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 2: Set a username containing numbers.
	 */
	@Test
	public void testSetUserWithNumericUsername() {
		// Arrange
		String expectedUser = "user123";

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the provided numeric username", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 3: Set a username that looks like a numeric ID (common in FTP listings).
	 */
	@Test
	public void testSetUserWithNumericIdString() {
		// Arrange
		String expectedUser = "1001";

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the provided numeric ID string", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 4: Set a username containing special characters (e.g., underscore).
	 */
	@Test
	public void testSetUserWithSpecialCharacters() {
		// Arrange
		String expectedUser = "user_name.test";

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the provided username with special characters", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 5: Set the user to null.
	 */
	@Test
	public void testSetUserWithNull() {
		// Arrange
		String nullUser = null;

		// Act
		ftpFile.setUser(nullUser);

		// Assert
		assertNull("User should be set to null", ftpFile.getUser());
	}

	/**
	 * Test case 6: Set the user to an empty string.
	 */
	@Test
	public void testSetUserWithEmptyString() {
		// Arrange
		String expectedUser = "";

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to an empty string", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 7: Set the user to a string containing only whitespace.
	 */
	@Test
	public void testSetUserWithWhitespaceString() {
		// Arrange
		String expectedUser = "   "; // Three spaces

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the whitespace string", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 8: Set a very long username string.
	 */
	@Test
	public void testSetUserWithLongString() {
		// Arrange
		// Create a long string (e.g., 256 characters)
		StringBuilder longUserBuilder = new StringBuilder(256);
		for (int i = 0; i < 256; i++) {
			longUserBuilder.append('a');
		}
		String expectedUser = longUserBuilder.toString();

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the long string", expectedUser, ftpFile.getUser());
	}

	/**
	 * Test case 9: Call setUser multiple times and verify the last value is kept.
	 */
	@Test
	public void testSetUserOverwritePreviousValue() {
		// Arrange
		String initialUser = "firstUser";
		String finalUser = "secondUser";
		ftpFile.setUser(initialUser); // Set an initial value

		// Act
		ftpFile.setUser(finalUser); // Overwrite with the final value

		// Assert
		assertEquals("User should be overwritten to the final value", finalUser, ftpFile.getUser());
	}

	/**
	 * Test case 10: Set a username containing unicode characters.
	 */
	@Test
	public void testSetUserWithUnicodeCharacters() {
		// Arrange
		String expectedUser = "josé";

		// Act
		ftpFile.setUser(expectedUser);

		// Assert
		assertEquals("User should be set to the provided username with unicode characters", expectedUser, ftpFile.getUser());
	}
}