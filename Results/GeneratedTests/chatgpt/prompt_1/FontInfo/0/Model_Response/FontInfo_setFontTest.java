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

    @Test(expected = IllegalArgumentException.class)
    public void testSetFontWithNull() {
        // Arrange & Act
        fontInfo.setFont(null);
    }

    @Test
    public void testSetFontWithValidPlainFont() {
        // Arrange
        Font font = new Font("Arial", Font.PLAIN, 12);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Arial", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(12, fontInfo.getSize());
    }

    @Test
    public void testSetFontWithValidBoldFont() {
        // Arrange
        Font font = new Font("Arial", Font.BOLD, 14);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Arial", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(14, fontInfo.getSize());
    }

    @Test
    public void testSetFontWithValidItalicFont() {
        // Arrange
        Font font = new Font("Arial", Font.ITALIC, 16);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Arial", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(16, fontInfo.getSize());
    }

    @Test
    public void testSetFontWithBoldItalicFont() {
        // Arrange
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 18);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Arial", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(18, fontInfo.getSize());
    }

    @Test
    public void testSetFontWithDifferentFamily() {
        // Arrange
        Font font = new Font("Times New Roman", Font.PLAIN, 20);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Times New Roman", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(20, fontInfo.getSize());
    }

    @Test
    public void testSetFontWithZeroSize() {
        // Arrange
        Font font = new Font("Arial", Font.PLAIN, 0);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Arial", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(0, fontInfo.getSize());
    }

    @Test
    public void testSetFontWithNegativeSize() {
        // Arrange
        Font font = new Font("Arial", Font.PLAIN, -10);

        // Act
        fontInfo.setFont(font);

        // Assert
        assertEquals("Arial", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(-10, fontInfo.getSize());
    }

    @Test
    public void testSetFontResetsPreviousSettings() {
        // Arrange
        Font firstFont = new Font("Arial", Font.BOLD, 14);
        Font secondFont = new Font("Courier", Font.PLAIN, 12);

        // Act
        fontInfo.setFont(firstFont);
        fontInfo.setFont(secondFont);

        // Assert
        assertEquals("Courier", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(12, fontInfo.getSize());
    }
}
```

This test class covers various cases including setting null font, different font styles (plain, bold, italic, bold-italic), edge cases with zero or negative size, changing font families, and ensuring previous settings are overridden correctly.