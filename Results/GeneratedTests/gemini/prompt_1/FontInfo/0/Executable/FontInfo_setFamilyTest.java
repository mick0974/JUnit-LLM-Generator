import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
// No need to import Serializable as it's not directly used in tests for this method

/**
 * Test class for the {@link FontInfo#setFamily(String)} method.
 * Contains 10 unit test cases adhering to the Arrange-Act-Assert pattern,
 * covering typical usage, edge cases, and null handling.
 */
public class FontInfo_setFamilyTest {

    private FontInfo fontInfo;
    private static final String DEFAULT_FAMILY = "Monospaced"; // As defined in FontInfo

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Initializes a new FontInfo object with default settings.
     */
    @Before
    public void setUp() {
        // Arrange: Create a new FontInfo instance before each test
        fontInfo = new FontInfo();
        // Initial state check (optional but good practice)
        assertEquals("Default family should be set initially", DEFAULT_FAMILY, fontInfo.getFamily());
        assertEquals("Default size should be set initially", 12, fontInfo.getSize());
        assertFalse("Default bold should be false initially", fontInfo.isBold());
        assertFalse("Default italic should be false initially", fontInfo.isItalic());
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     * Cleans up resources, setting the test object to null.
     */
    @After
    public void tearDown() {
        fontInfo = null;
    }

    /**
     * Test case 1: Setting a typical, non-null, non-default family name.
     */
    @Test
    public void testSetFamily_NonNull_Arial() {
        // Arrange
        String expectedFamily = "Arial";

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should be set to Arial", expectedFamily, actualFamily);
    }

    /**
     * Test case 2: Setting another typical, non-null, non-default family name.
     */
    @Test
    public void testSetFamily_NonNull_TimesNewRoman() {
        // Arrange
        String expectedFamily = "Times New Roman";

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should be set to Times New Roman", expectedFamily, actualFamily);
    }

    /**
     * Test case 3: Setting the family name to null.
     * Expected behavior: The family name should revert to the default.
     */
    @Test
    public void testSetFamily_Null_ShouldSetDefault() {
        // Arrange
        // First set a non-default value to ensure the null actually changes it back
        fontInfo.setFamily("TemporaryValue");
        assertNotEquals("Family name should not be default before setting null", DEFAULT_FAMILY, fontInfo.getFamily());
        String familyToSet = null;
        String expectedFamily = DEFAULT_FAMILY;

        // Act
        fontInfo.setFamily(familyToSet);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should revert to default when set to null", expectedFamily, actualFamily);
    }

    /**
     * Test case 4: Setting the family name to an empty string.
     * Expected behavior: The family name should be set to the empty string.
     */
    @Test
    public void testSetFamily_EmptyString() {
        // Arrange
        String expectedFamily = "";

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should be set to an empty string", expectedFamily, actualFamily);
    }

    /**
     * Test case 5: Setting the family name to the default value explicitly.
     */
    @Test
    public void testSetFamily_NonNull_DefaultExplicitly() {
        // Arrange
        String expectedFamily = DEFAULT_FAMILY;

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should be set to the default value", expectedFamily, actualFamily);
    }

    /**
     * Test case 6: Setting a family name with leading and trailing whitespace.
     * Expected behavior: Whitespace should be preserved as the method does not trim.
     */
    @Test
    public void testSetFamily_WithWhitespace() {
        // Arrange
        String expectedFamily = "  Courier New  ";

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should preserve leading/trailing whitespace", expectedFamily, actualFamily);
    }

    /**
     * Test case 7: Setting a family name containing special characters (hyphen).
     */
    @Test
    public void testSetFamily_WithSpecialChars() {
        // Arrange
        String expectedFamily = "Sans-Serif";

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should allow special characters like hyphens", expectedFamily, actualFamily);
    }

    /**
     * Test case 8: Setting a family name that differs from the default only by case.
     */
    @Test
    public void testSetFamily_DifferentCase() {
        // Arrange
        String expectedFamily = "monospaced"; // Lowercase version of default

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should be case-sensitive", expectedFamily, actualFamily);
        assertNotEquals("Family name should not be equal to the default due to case difference", DEFAULT_FAMILY, actualFamily);
    }

    /**
     * Test case 9: Overwriting an existing non-default family name with another non-default name.
     */
    @Test
    public void testSetFamily_OverwriteExisting() {
        // Arrange
        fontInfo.setFamily("Arial"); // Set initial non-default value
        assertEquals("Initial family name setup failed", "Arial", fontInfo.getFamily());
        String expectedFamily = "Verdana";

        // Act
        fontInfo.setFamily(expectedFamily);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should be overwritten with the new value", expectedFamily, actualFamily);
    }

    /**
     * Test case 10: Setting null after having set a non-default value.
     * Verifies again that null correctly reverts to the default.
     */
    @Test
    public void testSetFamily_ResetToNullFromNonDefault() {
        // Arrange
        fontInfo.setFamily("Helvetica"); // Set a non-default value
        assertEquals("Initial family name setup failed", "Helvetica", fontInfo.getFamily());
        String familyToSet = null;
        String expectedFamily = DEFAULT_FAMILY;

        // Act
        fontInfo.setFamily(familyToSet);
        String actualFamily = fontInfo.getFamily();

        // Assert
        assertEquals("Family name should revert to default when set to null from a non-default value", expectedFamily, actualFamily);
    }
}