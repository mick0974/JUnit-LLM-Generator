```java
    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testExpandEmptyElementsDefault() {
        // Arrange & Act
        boolean result = outputFormat.isExpandEmptyElements();
        
        // Assert
        assertFalse("By default, expandEmptyElements should be false", result);
    }

    @Test
    public void testExpandEmptyElementsSetTrue() {
        // Arrange
        outputFormat.setExpandEmptyElements(true);

        // Act
        boolean result = outputFormat.isExpandEmptyElements();

        // Assert
        assertTrue("expandEmptyElements should be true after setting it to true", result);
    }

    @Test
    public void testExpandEmptyElementsSetFalse() {
        // Arrange
        outputFormat.setExpandEmptyElements(false);

        // Act
        boolean result = outputFormat.isExpandEmptyElements();

        // Assert
        assertFalse("expandEmptyElements should remain false after setting it to false", result);
    }

    @Test
    public void testToggleExpandEmptyElements() {
        // Arrange
        outputFormat.setExpandEmptyElements(false);
        outputFormat.setExpandEmptyElements(true);

        // Act
        boolean result = outputFormat.isExpandEmptyElements();

        // Assert
        assertTrue("expandEmptyElements should be true after being toggled", result);
    }

    @Test
    public void testSetExpandEmptyElementsTwiceToTrue() {
        // Arrange
        outputFormat.setExpandEmptyElements(true);
        outputFormat.setExpandEmptyElements(true);

        // Act
        boolean result = outputFormat.isExpandEmptyElements();

        // Assert
        assertTrue("expandEmptyElements should remain true after being set to true twice", result);
    }

    @Test
    public void testSetExpandEmptyElementsTrueThenFalse() {
        // Arrange
        outputFormat.setExpandEmptyElements(true);
        outputFormat.setExpandEmptyElements(false);

        // Act
        boolean result = outputFormat.isExpandEmptyElements();

        // Assert
        assertFalse("expandEmptyElements should be false after being set to false after true", result);
    }

    @Test
    public void testExpandEmptyElementsAfterResetting() {
        // Arrange
        outputFormat.setExpandEmptyElements(true);
        outputFormat = new OutputFormat(); // Reset

        // Act
        boolean result = outputFormat.isExpandEmptyElements();

        // Assert
        assertFalse("expandEmptyElements should default to false after resetting OutputFormat", result);
    }

    @Test
    public void testExpandEmptyElementsInPrettyPrint() {
        // Arrange
        OutputFormat prettyPrintFormat = OutputFormat.createPrettyPrint();

        // Act
        boolean result = prettyPrintFormat.isExpandEmptyElements();

        // Assert
        assertFalse("PrettyPrint format should not expand empty elements by default", result);
    }
    
    @Test
    public void testExpandEmptyElementsInCompactFormat() {
        // Arrange
        OutputFormat compactFormat = OutputFormat.createCompactFormat();

        // Act
        boolean result = compactFormat.isExpandEmptyElements();

        // Assert
        assertFalse("Compact format should not expand empty elements by default", result);
    }

    @Test
    public void testInvalidCaseForExpandEmptyElements() {
        // There's no invalid input for this boolean setting, confirming robustness
        // Arrange & Act
        boolean resultTrue = outputFormat.isExpandEmptyElements();
        
        // Assert
        assertFalse("By default, expandEmptyElements should be false", resultTrue);
    }
}
```

These test cases cover typical scenarios such as setting the option on and off, resetting the object, using built-in factory methods, and more.