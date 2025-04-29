```java
public class OutputFormat_setIndentTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    @After
    public void tearDown() {
        format = null;
    }

    @Test
    public void testSetIndentNull() {
        // Arrange, Act
        format.setIndent(null);
        
        // Assert
        assertNull("Indent should be null", format.getIndent());
    }

    @Test
    public void testSetIndentEmptyString() {
        // Arrange, Act
        format.setIndent("");
        
        // Assert
        assertNull("Indent should be null when provided empty string", format.getIndent());
    }

    @Test
    public void testSetIndentSingleSpace() {
        // Arrange
        String indent = " ";
        
        // Act
        format.setIndent(indent);
        
        // Assert
        assertEquals("Indent should be a single space", indent, format.getIndent());
    }

    @Test
    public void testSetIndentMultipleSpaces() {
        // Arrange
        String indent = "    "; // Four spaces
        
        // Act
        format.setIndent(indent);
        
        // Assert
        assertEquals("Indent should be four spaces", indent, format.getIndent());
    }

    @Test
    public void testSetIndentTabCharacter() {
        // Arrange
        String indent = "\t";
        
        // Act
        format.setIndent(indent);
        
        // Assert
        assertEquals("Indent should be a tab character", indent, format.getIndent());
    }

    @Test
    public void testSetIndentMixedSpacesAndTabs() {
        // Arrange
        String indent = " \t  ";
        
        // Act
        format.setIndent(indent);
        
        // Assert
        assertEquals("Indent should be mixed spaces and tab characters", indent, format.getIndent());
    }

    @Test
    public void testSetIndentNonStandardCharacters() {
        // Arrange
        String indent = "-->";
        
        // Act
        format.setIndent(indent);
        
        // Assert
        assertEquals("Indent should preserve non-standard characters", indent, format.getIndent());
    }

    @Test
    public void testSetIndentResetToDefault() {
        // Arrange
        format.setIndent("  ");
        
        // Act
        format.setIndent(null); // Reset
        
        // Assert
        assertNull("Indent should be reset to null", format.getIndent());
    }

    @Test
    public void testSetIndentAfterStandardValue() {
        // Arrange
        format.setIndent("  ");
        
        // Act
        format.setIndent("    ");
        
        // Assert
        assertEquals("Indent should change from standard value to four spaces", "    ", format.getIndent());
    }

    @Test
    public void testSetIndentConsecutiveNull() {
        // Arrange
        format.setIndent(null);
        
        // Act
        format.setIndent(null); // Again setting to null
        
        // Assert
        assertNull("Indent should stay null after consecutive null assignments", format.getIndent());
    }
}
```