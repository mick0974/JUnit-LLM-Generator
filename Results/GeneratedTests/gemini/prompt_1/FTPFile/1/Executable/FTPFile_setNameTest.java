// FTPFile_setNameTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the FTPFile#setName(String name) method.
 * Contains 10 unit test cases following the Arrange-Act-Assert pattern
 * to cover typical and edge cases for setting the file name.
 */
public class FTPFile_setNameTest {

	private FTPFile ftpFile;

	@Before
	public void setUp() {
		// Arrange: Create a new FTPFile instance before each test
		ftpFile = new FTPFile();
		// Ensure the initial name is null as per the constructor
		assertNull("Initial name should be null", ftpFile.getName());
	}

	@After
	public void tearDown() {
		// Clean up resources if needed (not required for this simple object)
		ftpFile = null;
	}

	/**
	 * Test setting a typical file name.
	 */
	@Test
	public void testSetTypicalFileName() {
		// Arrange
		String expectedName = "document.txt";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should be set to 'document.txt'", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting a typical directory name.
	 */
	@Test
	public void testSetTypicalDirectoryName() {
		// Arrange
		String expectedName = "my_folder";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should be set to 'my_folder'", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting a name containing spaces.
	 */
	@Test
	public void testSetNameWithSpaces() {
		// Arrange
		String expectedName = "file with spaces.zip";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should handle spaces correctly", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting a name with various allowed special characters.
	 */
	@Test
	public void testSetNameWithSpecialCharacters() {
		// Arrange
		String expectedName = "data-set_v1.0-(final).csv";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should handle special characters", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting an empty string as the name.
	 */
	@Test
	public void testSetEmptyName() {
		// Arrange
		String expectedName = "";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should be settable to an empty string", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting the name to null.
	 */
	@Test
	public void testSetNullName() {
		// Arrange
		String expectedName = null;

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertNull("Name should be settable to null", ftpFile.getName());
	}

	/**
	 * Test setting a very long file name.
	 */
	@Test
	public void testSetLongName() {
		// Arrange
		String expectedName = "a_very_long_file_name_that_might_exceed_some_limits_but_should_be_handled_gracefully_by_the_setter_method_0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.dat";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should handle long strings", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting a name that consists only of numbers.
	 */
	@Test
	public void testSetNumericName() {
		// Arrange
		String expectedName = "1234567890";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should handle numeric strings", expectedName, ftpFile.getName());
	}

	/**
	 * Test setting the name multiple times, checking the final value.
	 */
	@Test
	public void testSetNameMultipleTimes() {
		// Arrange
		String intermediateName = "old_name.tmp";
		String finalName = "new_name.final";

		// Act
		ftpFile.setName(intermediateName); // Set once
		assertEquals("Intermediate name should be set", intermediateName, ftpFile.getName());

		ftpFile.setName(finalName); // Set again

		// Assert
		assertEquals("Final name should overwrite the previous one", finalName, ftpFile.getName());
	}

	/**
	 * Test setting the name when it was initially null (standard case via setUp).
	 */
	@Test
	public void testSetFromInitialNullState() {
		// Arrange: ftpFile is already created with name=null in setUp
		String expectedName = "initial_set.log";

		// Act
		ftpFile.setName(expectedName);

		// Assert
		assertEquals("Name should be set correctly from initial null state", expectedName, ftpFile.getName());
	}
}