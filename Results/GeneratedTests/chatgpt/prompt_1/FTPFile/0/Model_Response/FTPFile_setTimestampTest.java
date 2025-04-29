```java
    private FTPFile ftpFile;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
    }

    @After
    public void tearDown() {
        ftpFile = null;
    }

    @Test
    public void testSetTimestampWithNull() {
        // Arrange
        Calendar calendar = null;

        // Act
        ftpFile.setTimestamp(calendar);

        // Assert
        assertNull("Timestamp should be null.", ftpFile.getTimestamp());
    }

    @Test
    public void testSetTimestampWithCurrentTime() {
        // Arrange
        Calendar currentTime = Calendar.getInstance();

        // Act
        ftpFile.setTimestamp(currentTime);

        // Assert
        assertEquals("Timestamps should be equal.", currentTime, ftpFile.getTimestamp());
    }

    @Test
    public void testSetTimestampWithSpecificDate() {
        // Arrange
        Calendar specificDate = Calendar.getInstance();
        specificDate.set(2023, Calendar.OCTOBER, 10, 14, 30, 0); // 10th Oct 2023, 14:30:00

        // Act
        ftpFile.setTimestamp(specificDate);

        // Assert
        assertEquals("Year should match.", 2023, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Month should match.", Calendar.OCTOBER, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Day should match.", 10, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
        assertEquals("Hour should match.", 14, ftpFile.getTimestamp().get(Calendar.HOUR_OF_DAY));
        assertEquals("Minute should match.", 30, ftpFile.getTimestamp().get(Calendar.MINUTE));
    }

    @Test
    public void testSetTimestampWithDifferentZone() {
        // Arrange
        Calendar specificDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        specificDate.set(2023, Calendar.JANUARY, 1, 0, 0, 0);

        // Act
        ftpFile.setTimestamp(specificDate);

        // Assert
        assertEquals("Time zone should match the UTC.", "UTC", ftpFile.getTimestamp().getTimeZone().getID());
    }

    @Test
    public void testSetTimestampPreserveOriginalCalendar() {
        // Arrange
        Calendar originalTimestamp = Calendar.getInstance();
        originalTimestamp.set(2022, Calendar.JUNE, 15, 10, 0, 0);

        // Act
        ftpFile.setTimestamp(originalTimestamp);

        // Modify original calendar
        originalTimestamp.set(2023, Calendar.DECEMBER, 25, 12, 0, 0);

        // Assert
        assertEquals("Timestamp year should not change.", 2022, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Timestamp month should not change.", Calendar.JUNE, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Timestamp day should not change.", 15, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetTimestampTwice() {
        // Arrange
        Calendar firstTimestamp = Calendar.getInstance();
        firstTimestamp.set(2021, Calendar.APRIL, 10, 15, 0, 0);

        Calendar secondTimestamp = Calendar.getInstance();
        secondTimestamp.set(2022, Calendar.MARCH, 11, 16, 0, 0);

        // Act
        ftpFile.setTimestamp(firstTimestamp);
        ftpFile.setTimestamp(secondTimestamp);

        // Assert
        assertEquals("Year should match the second timestamp.", 2022, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Month should match the second timestamp.", Calendar.MARCH, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Day should match the second timestamp.", 11, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetTimestampWithLeapYear() {
        // Arrange
        Calendar leapYearDate = Calendar.getInstance();
        leapYearDate.set(2020, Calendar.FEBRUARY, 29, 23, 59, 59);

        // Act
        ftpFile.setTimestamp(leapYearDate);

        // Assert
        assertEquals("Year should match the leap year.", 2020, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Month should match February.", Calendar.FEBRUARY, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Day should match the 29th of February.", 29, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetTimestampLastDayOfYear() {
        // Arrange
        Calendar lastDayOfYear = Calendar.getInstance();
        lastDayOfYear.set(2021, Calendar.DECEMBER, 31, 23, 59, 59);

        // Act
        ftpFile.setTimestamp(lastDayOfYear);

        // Assert
        assertEquals("Year should match 2021.", 2021, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Month should match December.", Calendar.DECEMBER, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Day should match 31st.", 31, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetTimestampFirstDayOfYear() {
        // Arrange
        Calendar firstDayOfYear = Calendar.getInstance();
        firstDayOfYear.set(2022, Calendar.JANUARY, 1, 0, 0, 0);

        // Act
        ftpFile.setTimestamp(firstDayOfYear);

        // Assert
        assertEquals("Year should match 2022.", 2022, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Month should match January.", Calendar.JANUARY, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Day should match 1st.", 1, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testSetTimestampNoMillis() {
        // Arrange
        Calendar noMillis = Calendar.getInstance();
        noMillis.set(2023, Calendar.MAY, 5, 12, 12, 12);
        noMillis.clear(Calendar.MILLISECOND);

        // Act
        ftpFile.setTimestamp(noMillis);

        // Assert
        assertEquals("Year should match 2023.", 2023, ftpFile.getTimestamp().get(Calendar.YEAR));
        assertEquals("Month should match May.", Calendar.MAY, ftpFile.getTimestamp().get(Calendar.MONTH));
        assertEquals("Day should match 5th.", 5, ftpFile.getTimestamp().get(Calendar.DAY_OF_MONTH));
        assertFalse("Milliseconds should not be set.", ftpFile.getTimestamp().isSet(Calendar.MILLISECOND));
    }
}
```