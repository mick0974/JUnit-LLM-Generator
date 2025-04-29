```java
    private HSLColor hslColor;

    @Before
    public void setUp() {
        hslColor = new HSLColor();
    }

    @After
    public void tearDown() {
        hslColor = null;
    }

    @Test
    public void testWhite() {
        // Arrange
        int R = 255, G = 255, B = 255;

        // Act
        hslColor.initHSLbyRGB(R, G, B);

        // Assert
        assertEquals(0, hslColor.getSaturation());
        assertEquals(255, hslColor.getLuminence());
        assertEquals(HSLColor.UNDEFINED, hslColor.getHue());
    }

    @Test
    public void testBlack() {
        int R = 0, G = 0, B = 0;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(0, hslColor.getSaturation());
        assertEquals(0, hslColor.getLuminence());
        assertEquals(HSLColor.UNDEFINED, hslColor.getHue());
    }

    @Test
    public void testRed() {
        int R = 255, G = 0, B = 0;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(0, hslColor.getHue());
    }

    @Test
    public void testLimeGreen() {
        int R = 0, G = 255, B = 0;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(85, hslColor.getHue());
    }

    @Test
    public void testBlue() {
        int R = 0, G = 0, B = 255;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(170, hslColor.getHue());
    }

    @Test
    public void testGreyMiddle() {
        int R = 128, G = 128, B = 128;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(0, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(HSLColor.UNDEFINED, hslColor.getHue());
    }

    @Test
    public void testYellow() {
        int R = 255, G = 255, B = 0;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(43, hslColor.getHue());
    }

    @Test
    public void testCyan() {
        int R = 0, G = 255, B = 255;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(128, hslColor.getHue());
    }

    @Test
    public void testMagenta() {
        int R = 255, G = 0, B = 255;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(213, hslColor.getHue());
    }

    @Test
    public void testRandomColor() {
        int R = 123, G = 150, B = 200;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(85, hslColor.getSaturation());
        assertEquals(161, hslColor.getLuminence());
        assertEquals(152, hslColor.getHue());
    }
}
```

This test class provides a comprehensive set of tests for the `initHSLbyRGB` method, covering basic colors, greyscale, and a random color example. Each test case follows the Arrange-Act-Assert pattern, ensuring clarity and consistency.