```java
    private OutputFormat outputFormat;

    @Before
    public void setUp() throws Exception {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() throws Exception {
        outputFormat = null;
    }

    @Test
    public void testSetNewLineAfterDeclarationDefault() {
        // Arrange
        // default setup

        // Act & Assert
        assertTrue("Default value for newLineAfterDeclaration should be true", outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testSetNewLineAfterDeclarationTrue() {
        // Arrange
        boolean expected = true;

        // Act
        outputFormat.setNewLineAfterDeclaration(expected);

        // Assert
        assertEquals("The value should be set to true", expected, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testSetNewLineAfterDeclarationFalse() {
        // Arrange
        boolean expected = false;

        // Act
        outputFormat.setNewLineAfterDeclaration(expected);

        // Assert
        assertEquals("The value should be set to false", expected, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testToggleNewLineAfterDeclaration() {
        // Arrange
        boolean initial = outputFormat.isNewLineAfterDeclaration();
        
        // Act
        outputFormat.setNewLineAfterDeclaration(!initial);

        // Assert
        assertNotEquals("The value should invert its initial state", initial, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testMultipleSetNewLineAfterDeclaration() {
        // Arrange
        outputFormat.setNewLineAfterDeclaration(false);
        outputFormat.setNewLineAfterDeclaration(true);

        // Act & Assert
        assertTrue("The value should be true after setting it to true", outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testRedundantSetSameValueTrue() {
        // Arrange
        boolean expected = true;
        outputFormat.setNewLineAfterDeclaration(expected);

        // Act
        outputFormat.setNewLineAfterDeclaration(expected);

        // Assert
        assertEquals("The value should remain true", expected, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testRedundantSetSameValueFalse() {
        // Arrange
        boolean expected = false;
        outputFormat.setNewLineAfterDeclaration(expected);

        // Act
        outputFormat.setNewLineAfterDeclaration(expected);

        // Assert
        assertEquals("The value should remain false", expected, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testSetNewLineAfterDeclarationAfterOtherSettings() {
        // Arrange
        outputFormat.setIndent("  ");
        outputFormat.setEncoding("ISO-8859-1");
        
        boolean expected = false;

        // Act
        outputFormat.setNewLineAfterDeclaration(expected);

        // Assert
        assertEquals("The value should be changed to false correctly", expected, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testPreserveNewLineAfterDeclarationWhenOtherPropertiesChange() {
        // Arrange
        boolean expected = outputFormat.isNewLineAfterDeclaration();
        outputFormat.setIndent("    ");
        outputFormat.setEncoding("UTF-16");

        // Act & Assert
        assertEquals("NewLineAfterDeclaration should remain unchanged", expected, outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testDefaultNewLineAfterDeclarationWithCreateMethods() {
        // Arrange
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        OutputFormat compactFormat = OutputFormat.createCompactFormat();

        // Act & Assert
        assertTrue("Pretty print default should be true", prettyFormat.isNewLineAfterDeclaration());
        assertTrue("Compact format default should be true", compactFormat.isNewLineAfterDeclaration());
    }
}
```