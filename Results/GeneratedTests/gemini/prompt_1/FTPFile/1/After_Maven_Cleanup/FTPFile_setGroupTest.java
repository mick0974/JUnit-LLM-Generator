// FTPFile_setGroupTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class for the FTPFile#setGroup(String group) method.
 * It contains 10 unit test cases following the Arrange-Act-Assert pattern
 * to cover typical and edge cases.
 */
public class FTPFile_setGroupTest {

	private FTPFile ftpFile;

	/**
	 * Sets up the test fixture.
	 * Called before every test case method.
	 */
	@Before
	public void setUp() {
		ftpFile = new FTPFile();
	}

	/**
	 * Tears down the test fixture.
	 * Called after every test case method.
	 */
	@After
	public void tearDown() {
		ftpFile = null;
	}

	/**
	 * Test setting a typical alphabetical group name.
	 */
	@Test
	public void testSetGroup_TypicalName() {
		// Arrange
		String groupName = "users";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should be set to 'users'", groupName, ftpFile.getGroup());
	}

	/**
	 * Test setting a group name containing numbers.
	 */
	@Test
	public void testSetGroup_NumericName() {
		// Arrange
		String groupName = "1001";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should be set to '1001'", groupName, ftpFile.getGroup());
	}

	/**
	 * Test setting a group name containing alphanumeric characters.
	 */
	@Test
	public void testSetGroup_AlphaNumericName() {
		// Arrange
		String groupName = "adminGroup1";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should be set to 'adminGroup1'", groupName, ftpFile.getGroup());
	}

	/**
	 * Test setting an empty string as the group name.
	 */
	@Test
	public void testSetGroup_EmptyString() {
		// Arrange
		String groupName = "";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should be set to an empty string", groupName, ftpFile.getGroup());
		// Note: Default is also "", so this test confirms setting it explicitly works.
	}

	/**
	 * Test setting null as the group name.
	 */
	@Test
	public void testSetGroup_NullValue() {
		// Arrange
		String groupName = null;

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertNull("Group name should be set to null", ftpFile.getGroup());
	}

	/**
	 * Test setting a group name with leading and trailing spaces.
	 * The method should preserve the spaces.
	 */
	@Test
	public void testSetGroup_WithSpaces() {
		// Arrange
		String groupName = "  spaced group  ";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should preserve leading/trailing spaces", groupName, ftpFile.getGroup());
	}

	/**
	 * Test setting a group name containing special characters.
	 */
	@Test
	public void testSetGroup_SpecialCharacters() {
		// Arrange
		String groupName = "grp-@#$_";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should allow special characters", groupName, ftpFile.getGroup());
	}

	/**
	 * Test setting a very long group name.
	 */
	@Test
	public void testSetGroup_LongName() {
		// Arrange
		String groupName = "aVeryLongGroupNameThatExceedsTypicalUnixLimitsButShouldStillBeStoredCorrectlyByTheFTPFileClass";

		// Act
		ftpFile.setGroup(groupName);

		// Assert
		assertEquals("Group name should handle long strings", groupName, ftpFile.getGroup());
	}

	/**
	 * Test resetting the group name to a different value.
	 */
	@Test
	public void testSetGroup_ResetValue() {
		// Arrange
		String initialGroup = "initialGroup";
		String finalGroup = "finalGroup";
		ftpFile.setGroup(initialGroup); // Set initial value

		// Act
		ftpFile.setGroup(finalGroup);

		// Assert
		assertEquals("Group name should be updated to the final value", finalGroup, ftpFile.getGroup());
	}

    /**
     * Test setting the group name after it was explicitly set to null.
     */
    @Test
    public void testSetGroup_AfterSettingNull() {
        // Arrange
        String groupName = "notNullAnymore";
        ftpFile.setGroup(null); // Explicitly set to null first

        // Act
        ftpFile.setGroup(groupName);

        // Assert
        assertEquals("Group name should be set correctly after being null", groupName, ftpFile.getGroup());
    }
}