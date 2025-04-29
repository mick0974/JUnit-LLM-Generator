```java
    private HSLColor hslColor;

    @Before
    public void setUp() {
        // Initialize the HSLColor object before each test
        hslColor = new HSLColor();
        // Set an initial color to blend with
        hslColor.initHSLbyRGB(100, 150, 200); // arbitrary initial RGB values
    }

    @After
    public void tearDown() {
        // Clean up after each test
        hslColor = null;
    }
    
    @Test
    public void testBlendFullPercent() {
        // Arrange
        int targetR = 255, targetG = 255, targetB = 255;
        float blendPercent = 1.0f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(targetR, hslColor.getRed());
        assertEquals(targetG, hslColor.getGreen());
        assertEquals(targetB, hslColor.getBlue());
    }

    @Test
    public void testBlendZeroPercent() {
        // Arrange
        int initialR = hslColor.getRed(), initialG = hslColor.getGreen(), initialB = hslColor.getBlue();
        int targetR = 255, targetG = 255, targetB = 255;
        float blendPercent = 0.0f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(initialR, hslColor.getRed());
        assertEquals(initialG, hslColor.getGreen());
        assertEquals(initialB, hslColor.getBlue());
    }

    @Test
    public void testBlendHalfPercent() {
        // Arrange
        int targetR = 200, targetG = 200, targetB = 200;
        float blendPercent = 0.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(150, hslColor.getRed());
        assertEquals(175, hslColor.getGreen());
        assertEquals(200, hslColor.getBlue());
    }

    @Test
    public void testBlendNegativePercent() {
        // Arrange
        int initialR = hslColor.getRed(), initialG = hslColor.getGreen(), initialB = hslColor.getBlue();
        int targetR = 0, targetG = 0, targetB = 0;
        float blendPercent = -0.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        // Blend percentage is invalid, so the color should not change
        assertEquals(initialR, hslColor.getRed());
        assertEquals(initialG, hslColor.getGreen());
        assertEquals(initialB, hslColor.getBlue());
    }

    @Test
    public void testBlendPercentGreaterThanOne() {
        // Arrange
        int targetR = 0, targetG = 0, targetB = 0;
        float blendPercent = 1.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        // Blend percentage is capped at 1, so it should fully blend to the target color
        assertEquals(targetR, hslColor.getRed());
        assertEquals(targetG, hslColor.getGreen());
        assertEquals(targetB, hslColor.getBlue());
    }

    @Test
    public void testBlendWithSameColor() {
        // Arrange
        int initialR = hslColor.getRed(), initialG = hslColor.getGreen(), initialB = hslColor.getBlue();
        int targetR = initialR, targetG = initialG, targetB = initialB;
        float blendPercent = 0.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        // Blending with the same color should result in no change
        assertEquals(initialR, hslColor.getRed());
        assertEquals(initialG, hslColor.getGreen());
        assertEquals(initialB, hslColor.getBlue());
    }

    @Test
    public void testBlendWithWhite() {
        // Arrange
        int targetR = 255, targetG = 255, targetB = 255;
        float blendPercent = 0.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(178, hslColor.getRed());
        assertEquals(203, hslColor.getGreen());
        assertEquals(227, hslColor.getBlue());
    }

    @Test
    public void testBlendWithBlack() {
        // Arrange
        int targetR = 0, targetG = 0, targetB = 0;
        float blendPercent = 0.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(50, hslColor.getRed());
        assertEquals(75, hslColor.getGreen());
        assertEquals(100, hslColor.getBlue());
    }

    @Test
    public void testBlendWithAnotherColor() {
        // Arrange
        int targetR = 0, targetG = 127, targetB = 127;
        float blendPercent = 0.75f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(25, hslColor.getRed());
        assertEquals(132, hslColor.getGreen());
        assertEquals(143, hslColor.getBlue());
    }

    @Test
    public void testBlendWithBoundaryColor() {
        // Arrange
        int targetR = 0, targetG = RGBMAX, targetB = RGBMAX;
        float blendPercent = 0.5f;

        // Act
        hslColor.blend(targetR, targetG, targetB, blendPercent);

        // Assert
        assertEquals(50, hslColor.getRed());
        assertEquals(190, hslColor.getGreen());
        assertEquals(200, hslColor.getBlue());
    }
}
```