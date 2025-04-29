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
    public void testGenerateStyle_Plain() {
        // Arrange
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.PLAIN, style);
    }

    @Test
    public void testGenerateStyle_Bold() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.BOLD, style);
    }

    @Test
    public void testGenerateStyle_Italic() {
        // Arrange
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.ITALIC, style);
    }

    @Test
    public void testGenerateStyle_BoldItalic() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.BOLD | Font.ITALIC, style);
    }

    @Test
    public void testGenerateStyle_SetBoldThenItalic() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.BOLD | Font.ITALIC, style);
    }

    @Test
    public void testGenerateStyle_SetItalicThenBold() {
        // Arrange
        fontInfo.setIsItalic(true);
        fontInfo.setIsBold(true);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.BOLD | Font.ITALIC, style);
    }

    @Test
    public void testDefaultStyle_ShouldBePlain() {
        // Arrange
        FontInfo defaultFontInfo = new FontInfo();

        // Act
        int style = defaultFontInfo.generateStyle();

        // Assert
        assertEquals(Font.PLAIN, style);
    }

    @Test
    public void testGenerateStyle_AfterResetProperties() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.PLAIN, style);
    }

    @Test
    public void testGenerateStyle_ChangeOnlyBold() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);

        // Act
        int styleBold = fontInfo.generateStyle();

        fontInfo.setIsBold(false);
        int stylePlain = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.BOLD, styleBold);
        assertEquals(Font.PLAIN, stylePlain);
    }

    @Test
    public void testGenerateStyle_ChangeOnlyItalic() {
        // Arrange
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);

        // Act
        int styleItalic = fontInfo.generateStyle();

        fontInfo.setIsItalic(false);
        int stylePlain = fontInfo.generateStyle();

        // Assert
        assertEquals(Font.ITALIC, styleItalic);
        assertEquals(Font.PLAIN, stylePlain);
    }
}
```

This test class provides comprehensive coverage for the `generateStyle` method in the `FontInfo` class, ensuring all possible combinations of the bold and italic properties are tested.