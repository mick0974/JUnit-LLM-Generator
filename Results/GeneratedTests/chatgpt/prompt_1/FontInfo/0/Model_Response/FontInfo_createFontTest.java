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
    public void testCreateFont_DefaultConstructor() {
        // Arrange & Act
        Font font = fontInfo.createFont();

        // Assert
        assertNotNull(font);
        assertEquals("Monospaced", font.getFamily());
        assertEquals(Font.PLAIN, font.getStyle());
        assertEquals(12, font.getSize());
    }

    @Test
    public void testCreateFont_SetFamily() {
        // Arrange
        fontInfo.setFamily("Arial");

        // Act
        Font font = fontInfo.createFont();

        // Assert
        assertEquals("Arial", font.getFamily());
        assertEquals(Font.PLAIN, font.getStyle());
        assertEquals(12, font.getSize());
    }

    @Test
    public void testCreateFont_SetSize() {
        // Arrange
        fontInfo.setSize(16);

        // Act
        Font font = fontInfo.createFont();

        // Assert
        assertEquals("Monospaced", font.getFamily());
        assertEquals(Font.PLAIN, font.getStyle());
        assertEquals(16, font.getSize());
    }

    @Test
    public void testCreateFont_SetBold() {
        // Arrange
        fontInfo.setIsBold(true);

        // Act
        Font font = fontInfo.createFont();

        // Assert
        assertEquals("Monospaced", font.getFamily());
        assertEquals(Font.BOLD, font.getStyle());
        assertEquals(12, font.getSize());
    }

    @Test
    public void testCreateFont_SetItalic() {
        // Arrange
        fontInfo.setIsItalic(true);

        // Act
        Font font = fontInfo.createFont();

        // Assert
        assertEquals("Monospaced", font.getFamily());
        assertEquals(Font.ITALIC, font.getStyle());
        assertEquals(12, font.getSize());
    }

    @Test
    public void testCreateFont_SetBoldAndItalic() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);

        // Act
        Font font = fontInfo.createFont();

        // Assert
        assertEquals("Monospaced", font.getFamily());
        assertEquals(Font.BOLD | Font.ITALIC, font.getStyle());
        assertEquals(12, font.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFontInfo_NullFontConstructor() {
        new FontInfo(null);
    }

    @Test
    public void testCreateFont_WithFontConstructor() {
        // Arrange
        Font inputFont = new Font("Serif", Font.BOLD | Font.ITALIC, 20);
        FontInfo fontInfoWithFont = new FontInfo(inputFont);

        // Act
        Font font = fontInfoWithFont.createFont();

        // Assert
        assertEquals("Serif", font.getFamily());
        assertEquals(Font.BOLD | Font.ITALIC, font.getStyle());
        assertEquals(20, font.getSize());
    }

    @Test
    public void testFontInfo_EqualsAndHashCode() {
        // Arrange
        FontInfo anotherFontInfo = new FontInfo();
        
        // Act & Assert
        assertTrue(fontInfo.equals(anotherFontInfo));
        assertEquals(fontInfo.hashCode(), anotherFontInfo.hashCode());

        anotherFontInfo.setFamily("SansSerif");
        assertFalse(fontInfo.equals(anotherFontInfo));
        assertNotEquals(fontInfo.hashCode(), anotherFontInfo.hashCode());
    }

    @Test
    public void testFontInfo_Serialization() throws Exception {
        // Arrange
        fontInfo.setFamily("Courier");
        fontInfo.setSize(14);
        fontInfo.setIsBold(true);
        
        // Act
        Serializable result = (Serializable) fontInfo.clone();

        // Assert
        assertEquals(fontInfo, result);
    }
}
```