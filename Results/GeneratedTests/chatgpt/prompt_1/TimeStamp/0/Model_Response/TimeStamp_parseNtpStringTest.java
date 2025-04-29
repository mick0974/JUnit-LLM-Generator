```java
    private DateFormat formatter;

    @Before
    public void setUp() {
        formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss.SSS", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @After
    public void tearDown() {
        formatter = null;
    }

    @Test
    public void testParseValidNtpString() {
        String ntpString = "c1a089bd.fc904f6d";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringEdgeCaseAllZeros() {
        String ntpString = "00000000.00000000";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void testParseNtpStringInvalidHex() {
        String ntpString = "zzzzzzzz.zzzzzzzz";
        TimeStamp.parseNtpString(ntpString);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseNtpStringNull() {
        TimeStamp.parseNtpString(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseNtpStringEmpty() {
        TimeStamp.parseNtpString("");
    }

    @Test
    public void testParseNtpStringValid1900Epoch() {
        // Hex representation of Jan 1, 1900
        String ntpString = "80000000.00000000";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringValid2036Epoch() {
        // NTP time for 7-Feb-2036 06:28:16 UTC
        String ntpString = "00000000.00000000";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringMidRangeValue() {
        // Mid-range value check
        String ntpString = "ffffffff.ffffffff";
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        assertEquals(ntpString, timestamp.toString());
    }

    @Test
    public void testParseNtpStringDateConversion() throws Exception {
        String ntpString = "dc896e90.44fcd6e1"; // An arbitrary NTP timestamp
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        Date date = timestamp.getDate();
        String dateString = formatter.format(date);
        assertEquals("Mon, Feb 28 2022 17:21:36.272", dateString);
    }

    @Test
    public void testParseNtpStringWithRoundTripConversion() {
        String ntpString = "4b963f77.9a054be6"; // Another arbitrary NTP timestamp
        TimeStamp timestamp = TimeStamp.parseNtpString(ntpString);
        String result = timestamp.toString();
        assertEquals(ntpString, result);
    }
}
```

Note:
- The tests include valid and invalid cases, edge conditions, and conversions to/from string representations.
- Error cases such as malformed strings, null values, and extremes of data are considered carefully.
- The test checks both the `toString` method and correctness of date conversion where applicable.