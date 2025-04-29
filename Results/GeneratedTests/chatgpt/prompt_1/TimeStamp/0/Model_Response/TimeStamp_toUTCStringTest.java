```java
    private TimeStamp timeStamp;
    private DateFormat utcFormatter;

    @Before
    public void setUp() {
        // Initialize the UTC formatter for comparisons
        utcFormatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'", Locale.US);
        utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @After
    public void tearDown() {
        timeStamp = null;
    }

    @Test
    public void testToUTCString_RFC2036_Base() {
        // Arrange
        long ntpTime = 0; // Represents the baseline 7-Feb-2036 @ 06:28:16 UTC
        timeStamp = new TimeStamp(ntpTime);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        assertNotNull("toUTCString should not return null", result);
        assertEquals("Sat, Feb 07 2036 06:28:16.000 UTC", result);
    }

    @Test
    public void testToUTCString_RFC1900_Base() {
        // Arrange
        long ntpTime = Long.MAX_VALUE; // Near the baseline 1-Jan-1900 @ 01:00:00 UTC
        timeStamp = new TimeStamp(ntpTime);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        Date expectedDate = new Date(timeStamp.getTime());
        String expected = utcFormatter.format(expectedDate);
        assertEquals("Mismatch in UTC string for RFC 1900 base", expected, result);
    }

    @Test
    public void testToUTCString_CurrentTime() {
        // Arrange
        timeStamp = TimeStamp.getCurrentTime();

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        Date currentDate = new Date(timeStamp.getTime());
        String expected = utcFormatter.format(currentDate);
        assertEquals("Mismatch between current UTC date string", expected, result);
    }

    @Test
    public void testToUTCString_FromJavaDate() {
        // Arrange
        Date now = new Date();
        timeStamp = new TimeStamp(now);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        String expected = utcFormatter.format(now);
        assertEquals("Mismatch between Java Date and UTC formatted string", expected, result);
    }

    @Test
    public void testToUTCString_MidnightDate() {
        // Arrange
        Date midnight = new Date(0); // 1-Jan-1970 UTC
        timeStamp = new TimeStamp(midnight);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        String expected = utcFormatter.format(midnight);
        assertEquals("Mismatch for midnight UTC formatted string", expected, result);
    }

    @Test
    public void testToUTCString_OverflowBoundary() {
        // Arrange
        long overflowNtpValue = TimeStamp.toNtpTime(msb0baseTime + (2L << 31) * 1000L);
        timeStamp = new TimeStamp(overflowNtpValue);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        Date expectedDate = new Date(timeStamp.getTime());
        String expected = utcFormatter.format(expectedDate);
        assertEquals("Mismatch for overflow boundary UTC formatted string", expected, result);
    }

    @Test
    public void testToUTCString_EndOfCentury() {
        // Arrange
        Date endOfCentury = new Date(4102444800000L); // 31-Dec-2099 23:00:00 UTC
        timeStamp = new TimeStamp(endOfCentury);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        String expected = utcFormatter.format(endOfCentury);
        assertEquals("Mismatch for end of century UTC formatted string", expected, result);
    }

    @Test
    public void testToUTCString_BeginningOfCentury() {
        // Arrange
        Date beginningOfCentury = new Date(-2208988800000L); // 1-Jan-1900
        timeStamp = new TimeStamp(beginningOfCentury);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        String expected = utcFormatter.format(beginningOfCentury);
        assertEquals("Mismatch for beginning of century UTC formatted string", expected, result);
    }

    @Test
    public void testToUTCString_RollOverScenario() {
        // Arrange
        long nearRollOverNtpValue = TimeStamp.toNtpTime(msb1baseTime + (1L << 31) * 1000L);
        timeStamp = new TimeStamp(nearRollOverNtpValue);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        Date expectedDate = new Date(timeStamp.getTime());
        String expected = utcFormatter.format(expectedDate);
        assertEquals("Mismatch for near rollover UTC formatted string", expected, result);
    }

    @Test
    public void testToUTCString_EarlyDate() {
        // Arrange
        Date earlyDate = new Date(-3000000000000L); // Prior to 1900
        timeStamp = new TimeStamp(earlyDate);

        // Act
        String result = timeStamp.toUTCString();

        // Assert
        String expected = utcFormatter.format(earlyDate);
        assertEquals("Mismatch for early date UTC formatted string", expected, result);
    }
}
```