```java
    private HSLColor color;

    @Before
    public void setUp() {
        color = new HSLColor();
    }

    @After
    public void tearDown() {
        color = null;
    }

    @Test
    public void testSetSaturationToZeroWhenStartingFromGreyscale() {
        color.initHSLbyRGB(128, 128, 128);
        color.setSaturation(0);
        assertEquals(0, color.getSaturation());
        assertEquals(128, color.getRed());
        assertEquals(128, color.getGreen());
        assertEquals(128, color.getBlue());
    }

    @Test
    public void testSetSaturationToMaxWithRedColor() {
        color.initHSLbyRGB(255, 0, 0);
        color.setSaturation(255);
        assertEquals(255, color.getSaturation());
    }

    @Test
    public void testSetSaturationToMidValue() {
        color.initHSLbyRGB(100, 150, 200);
        color.setSaturation(128);
        assertEquals(128, color.getSaturation());
    }

    @Test
    public void testSetSaturationBelowZero() {
        color.initHSLbyRGB(50, 100, 150);
        color.setSaturation(-10);
        assertEquals(0, color.getSaturation());
    }

    @Test
    public void testSetSaturationAboveMaxValue() {
        color.initHSLbyRGB(10, 20, 30);
        color.setSaturation(300);
        assertEquals(255, color.getSaturation());
    }

    @Test
    public void testSetSaturationToItself() {
        color.initHSLbyRGB(120, 180, 240);
        int initialSaturation = color.getSaturation();
        color.setSaturation(initialSaturation);
        assertEquals(initialSaturation, color.getSaturation());
    }

    @Test
    public void testSetSaturationOnPureBlue() {
        color.initHSLbyRGB(0, 0, 255);
        color.setSaturation(100);
        assertTrue(color.getRed() < color.getBlue());
    }

    @Test
    public void testSetSaturationOnBlack() {
        color.initHSLbyRGB(0, 0, 0);
        color.setSaturation(120);
        assertEquals(0, color.getRed());
        assertEquals(0, color.getGreen());
        assertEquals(0, color.getBlue());
    }

    @Test
    public void testSetSaturationOnWhite() {
        color.initHSLbyRGB(255, 255, 255);
        color.setSaturation(200);
        assertEquals(255, color.getRed());
        assertEquals(255, color.getGreen());
        assertEquals(255, color.getBlue());
    }

    @Test
    public void testSetSaturationResultingInSameColorIfAlreadyGrey() {
        color.initHSLbyRGB(190, 190, 190);
        color.setSaturation(0);
        assertEquals(190, color.getRed());
        assertEquals(190, color.getGreen());
        assertEquals(190, color.getBlue());
    }
}
```

This set of tests covers different aspects of setting saturation in the `HSLColor` class. The tests include setting saturation to both minimum and maximum values, handling edge cases where the saturation value might exceed expected limits, and ensuring saturation changes correctly reflect in the object's RGB values.