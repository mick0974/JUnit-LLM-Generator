```java
    private HSLColor hslColor;

    @Before
    public void setUp() {
        // This method will be called before each test case.
    }

    @After
    public void tearDown() {
        // This method will be called after each test case.
    }

    @Test
    public void testGetBlue_fromBlack() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(0, 0, 0);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for black should be 0.", 0, blue);
    }

    @Test
    public void testGetBlue_fromWhite() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(255, 255, 255);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for white should be 255.", 255, blue);
    }

    @Test
    public void testGetBlue_fromPureRed() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(255, 0, 0);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for pure red should be 0.", 0, blue);
    }

    @Test
    public void testGetBlue_fromPureGreen() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(0, 255, 0);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for pure green should be 0.", 0, blue);
    }

    @Test
    public void testGetBlue_fromPureBlue() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(0, 0, 255);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for pure blue should be 255.", 255, blue);
    }

    @Test
    public void testGetBlue_fromCyan() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(0, 255, 255);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for cyan should be 255.", 255, blue);
    }

    @Test
    public void testGetBlue_fromMagenta() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(255, 0, 255);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for magenta should be 255.", 255, blue);
    }

    @Test
    public void testGetBlue_fromYellow() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(255, 255, 0);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for yellow should be 0.", 0, blue);
    }

    @Test
    public void testGetBlue_fromGrey() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(128, 128, 128);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for grey (128, 128, 128) should be 128.", 128, blue);
    }

    @Test
    public void testGetBlue_fromRandomColor() {
        // Arrange
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(123, 45, 67);
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals("The blue component for the random color (123, 45, 67) should be 67.", 67, blue);
    }
}
```