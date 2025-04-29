```java
    private FontInfo fontInfo;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testSetSizePositiveValue() {
        int newSize = 16;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeZero() {
        int newSize = 0;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeNegativeValue() {
        int newSize = -5;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeUpdateFromDefault() {
        int defaultSize = fontInfo.getSize();
        int newSize = defaultSize + 5;
        fontInfo.setSize(newSize);
        assertNotEquals(defaultSize, fontInfo.getSize());
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeMaxInteger() {
        int newSize = Integer.MAX_VALUE;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeMinInteger() {
        int newSize = Integer.MIN_VALUE;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeBoundaryValuesPositive() {
        int newSize = 1;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());

        newSize = 2;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeBoundaryValuesNegative() {
        int newSize = -1;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());

        newSize = -2;
        fontInfo.setSize(newSize);
        assertEquals(newSize, fontInfo.getSize());
    }

    @Test
    public void testSetSizeMultipleUpdates() {
        int[] sizes = {5, 10, 15, 20, 25};

        for (int newSize : sizes) {
            fontInfo.setSize(newSize);
            assertEquals(newSize, fontInfo.getSize());
        }
    }

    @Test
    public void testSetSizeWithFontComparison() {
        int newSize = 18;
        fontInfo.setSize(newSize);
        Font createdFont = fontInfo.createFont();
        assertEquals(createdFont.getSize(), fontInfo.getSize());
    }
}
```

This test class contains 10 unit test cases for the `FontInfo#setSize(int value)` method. The test cases include setting the size to positive values, zero, negative values, maximum and minimum integer values, multiple updates, and verification with the `createFont()` method. These tests adhere to the Arrange-Act-Assert pattern and ensure comprehensive coverage of both typical and edge cases.