```java
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
    public void testSetTrimTextTrue() {
        // Arrange
        boolean expected = true;
        
        // Act
        format.setTrimText(true);
        
        // Assert
        assertEquals(expected, format.isTrimText());
    }

    @Test
    public void testSetTrimTextFalse() {
        // Arrange
        boolean expected = false;
        
        // Act
        format.setTrimText(false);
        
        // Assert
        assertEquals(expected, format.isTrimText());
    }
    
    @Test
    public void testDefaultTrimText() {
        // Arrange
        boolean expected = false; // Default value
        
        // Act
        boolean actual = format.isTrimText();
        
        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToggleTrimText() {
        // Act & Assert
        format.setTrimText(true);
        assertTrue(format.isTrimText());
        
        format.setTrimText(false);
        assertFalse(format.isTrimText());
        
        format.setTrimText(true);
        assertTrue(format.isTrimText());
    }
    
    @Test
    public void testSetTrimTextIdempotence() {
        // Setting same value multiple times should not alter the behavior
        format.setTrimText(true);
        format.setTrimText(true);
        assertTrue(format.isTrimText());
        
        format.setTrimText(false);
        format.setTrimText(false);
        assertFalse(format.isTrimText());
    }
    
    @Test
    public void testSetTrimTextBoundaryTrueToFalse() {
        // Act
        format.setTrimText(true);
        assertTrue(format.isTrimText());
        
        // Transition from true to false
        format.setTrimText(false);
        assertFalse(format.isTrimText());
    }
    
    @Test
    public void testSetTrimTextBoundaryFalseToTrue() {
        // Act
        format.setTrimText(false);
        assertFalse(format.isTrimText());
        
        // Transition from false to true
        format.setTrimText(true);
        assertTrue(format.isTrimText());
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetTrimTextWithNullFormat() {
        // Should trigger NullPointerException because format is null
        format = null;
        format.setTrimText(true);
    }
    
    @Test
    public void testTrimTextWithPrettyPrint() {
        // Arrange
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        
        // Assert
        assertTrue(prettyFormat.isTrimText());
    }
    
    @Test
    public void testTrimTextWithCompactFormat() {
        // Arrange
        OutputFormat compactFormat = OutputFormat.createCompactFormat();
        
        // Assert
        assertTrue(compactFormat.isTrimText());
    }
}
```