```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;


import org.junit.Test;

/**
 * JUnit 4 Tests for the TimeStamp class aiming for 100% line coverage.
 */
public class TimeStampTest {

    // Constants for testing derived from analysis or examples
    private static final long NTP_TIME_MSB1_EXAMPLE = 0x83AA7E8C80000000L; // Corresponds to 12500 ms (Epoch + 12.5s)
    private static final long JAVA_TIME_MSB1_EXAMPLE = 12500L;

    private static final long NTP_TIME_MSB0_EXAMPLE = 0x0000000C80000000L; // Corresponds to 2085978508500 ms (msb0baseTime + 12.5s)
    private static final long JAVA_TIME_MSB0_EXAMPLE = 2085978508500L;

    private static final long NTP_TIME_EPOCH = 0x83AA7E8000000000L; // Corresponds to 0 ms (Epoch)
    private static final long JAVA_TIME_EPOCH = 0L;

    private static final long NTP_TIME_MSB0_BASE = 0L; // Corresponds to msb0baseTime
    private static final long JAVA_TIME_MSB0_BASE = TimeStamp.msb0baseTime; // 2085978496000L

    private static final long NTP_TIME_MSB0_BASE_MINUS_1 = 0xFFFFFFFFFFBEBEBEL; // Corresponds to msb0baseTime - 1ms
    private static final long JAVA_TIME_MSB0_BASE_MINUS_1 = TimeStamp.msb0baseTime - 1;


    @Test
    public void TimeStampLongConstructorTest() {
        long val = 12345L;
        TimeStamp ts = new TimeStamp(val);
        assertEquals(val, ts.ntpValue());
    }

    @Test
    public void TimeStampStringConstructorValidWithDotTest() {
        String hex = "83aa7e8c.80000000";
        TimeStamp ts = new TimeStamp(hex);
        assertEquals(NTP_TIME_MSB1_EXAMPLE, ts.ntpValue());
    }

    @Test
    public void TimeStampStringConstructorValidWithoutDotTest() {
        String hex = "83aa7e8c"; // Represents seconds only, fraction is 0
        TimeStamp ts = new TimeStamp(hex);
        assertEquals(0x83AA7E8C00000000L, ts.ntpValue());
    }
    
    @Test
    public void TimeStampStringConstructorValidZeroTest() {
        String hex = "0"; // Represents 0 seconds, 0 fraction
        TimeStamp ts = new TimeStamp(hex);
        // Note: "0" without dot is parsed as seconds=0, fraction=0.
        // This is equivalent to 0x0000000000000000L.
        assertEquals(0L, ts.ntpValue()); 
    }

    @Test
    public void TimeStampStringConstructorValidZeroHexWithDotTest() {
        String hex = "0.0";
        TimeStamp ts = new TimeStamp(hex);
        assertEquals(0L, ts.ntpValue());
    }

    @Test
    public void TimeStampStringConstructorValidEmptyTest() {
        String hex = "";
        TimeStamp ts = new TimeStamp(hex);
        assertEquals(0L, ts.ntpValue());
    }

    @Test(expected = NumberFormatException.class)
    public void TimeStampStringConstructorNullTest() {
        new TimeStamp((String) null);
    }

    @Test(expected = NumberFormatException.class)
    public void TimeStampStringConstructorInvalidHexTest() {
        new TimeStamp("invalid.hex");
    }

    @Test(expected = NumberFormatException.class)
    public void TimeStampStringConstructorInvalidHexPart1Test() {
        new TimeStamp("invalid."); // Invalid first part
    }

    @Test(expected = NumberFormatException.class)
    public void TimeStampStringConstructorInvalidHexPart2Test() {
        new TimeStamp("abc.invalid"); // Invalid second part
    }
    
    @Test(expected = NumberFormatException.class)
    public void TimeStampStringConstructorInvalidDotPlacementTest() {
         new TimeStamp(".abc"); // Starts with dot
    }

    @Test
    public void TimeStampDateConstructorTest() {
        Date d = new Date(JAVA_TIME_MSB1_EXAMPLE);
        TimeStamp ts = new TimeStamp(d);
        assertEquals(NTP_TIME_MSB1_EXAMPLE, ts.ntpValue());
    }
    
    @Test
    public void TimeStampDateConstructorMsb0Test() {
        Date d = new Date(JAVA_TIME_MSB0_EXAMPLE);
        TimeStamp ts = new TimeStamp(d);
        assertEquals(NTP_TIME_MSB0_EXAMPLE, ts.ntpValue());
    }

    @Test
    public void TimeStampDateConstructorNullTest() {
        TimeStamp ts = new TimeStamp((Date) null);
        // Per constructor logic, null date results in ntpTime = 0
        assertEquals(NTP_TIME_MSB0_BASE, ts.ntpValue()); 
    }

    @Test
    public void ntpValueGetterTest() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(NTP_TIME_MSB1_EXAMPLE, ts.ntpValue());
    }

    @Test
    public void getSecondsTest() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(0x83AA7E8CL, ts.getSeconds());
    }

    @Test
    public void getFractionTest() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(0x80000000L, ts.getFraction());
    }
    
    @Test
    public void getSecondsZeroTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals(0L, ts.getSeconds());
    }
    
    @Test
    public void getFractionZeroTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals(0L, ts.getFraction());
    }

    @Test
    public void getTimeInstanceMsb1Test() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(JAVA_TIME_MSB1_EXAMPLE, ts.getTime());
    }

    @Test
    public void getTimeInstanceMsb0Test() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB0_EXAMPLE);
        assertEquals(JAVA_TIME_MSB0_EXAMPLE, ts.getTime());
    }

    @Test
    public void getDateMsb1Test() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(new Date(JAVA_TIME_MSB1_EXAMPLE), ts.getDate());
    }

    @Test
    public void getDateMsb0Test() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB0_EXAMPLE);
        assertEquals(new Date(JAVA_TIME_MSB0_EXAMPLE), ts.getDate());
    }

    @Test
    public void getTimeStaticMsb1Test() {
        assertEquals(JAVA_TIME_MSB1_EXAMPLE, TimeStamp.getTime(NTP_TIME_MSB1_EXAMPLE));
    }

    @Test
    public void getTimeStaticMsb0Test() {
        assertEquals(JAVA_TIME_MSB0_EXAMPLE, TimeStamp.getTime(NTP_TIME_MSB0_EXAMPLE));
    }

    @Test
    public void getTimeStaticEpochTest() {
        assertEquals(JAVA_TIME_EPOCH, TimeStamp.getTime(NTP_TIME_EPOCH));
    }

    @Test
    public void getTimeStaticMsb0BaseTest() {
        assertEquals(JAVA_TIME_MSB0_BASE, TimeStamp.getTime(NTP_TIME_MSB0_BASE));
    }
    
    @Test
    public void getTimeStaticMsb0BaseMinusOneTest() {
        assertEquals(JAVA_TIME_MSB0_BASE_MINUS_1, TimeStamp.getTime(NTP_TIME_MSB0_BASE_MINUS_1));
    }


    @Test
    public void getNtpTimeStaticBase1Test() {
        TimeStamp ts = TimeStamp.getNtpTime(JAVA_TIME_MSB1_EXAMPLE);
        assertEquals(NTP_TIME_MSB1_EXAMPLE, ts.ntpValue());
    }

    @Test
    public void getNtpTimeStaticBase0Test() {
        TimeStamp ts = TimeStamp.getNtpTime(JAVA_TIME_MSB0_EXAMPLE);
        assertEquals(NTP_TIME_MSB0_EXAMPLE, ts.ntpValue());
    }

    @Test
    public void getCurrentTimeNotNullTest() {
        // Cannot predict the exact value, but can ensure it doesn't fail
        assertNotNull(TimeStamp.getCurrentTime());
    }

    @Test(expected = NumberFormatException.class)
    public void decodeNtpHexStringNullTest() {
        TimeStamp.decodeNtpHexString(null);
    }

    @Test
    public void decodeNtpHexStringEmptyTest() {
        assertEquals(0L, TimeStamp.decodeNtpHexString(""));
    }

    @Test
    public void decodeNtpHexStringNoDotTest() {
        assertEquals(0x83AA7E8C00000000L, TimeStamp.decodeNtpHexString("83aa7e8c"));
    }

    @Test
    public void decodeNtpHexStringWithDotTest() {
        assertEquals(NTP_TIME_MSB1_EXAMPLE, TimeStamp.decodeNtpHexString("83aa7e8c.80000000"));
    }

    @Test(expected = NumberFormatException.class)
    public void decodeNtpHexStringInvalidTest() {
        TimeStamp.decodeNtpHexString("invalid.hex");
    }
    
    @Test(expected = NumberFormatException.class)
    public void decodeNtpHexStringInvalidFirstPartTest() {
        TimeStamp.decodeNtpHexString("xyz.123");
    }
    
    @Test(expected = NumberFormatException.class)
    public void decodeNtpHexStringInvalidSecondPartTest() {
        TimeStamp.decodeNtpHexString("123.xyz");
    }

    @Test
    public void parseNtpStringValidTest() {
        TimeStamp ts = TimeStamp.parseNtpString("83aa7e8c.80000000");
        assertEquals(NTP_TIME_MSB1_EXAMPLE, ts.ntpValue());
    }

    @Test(expected = NumberFormatException.class)
    public void parseNtpStringInvalidTest() {
        TimeStamp.parseNtpString("invalid");
    }
    
    @Test(expected = NumberFormatException.class)
    public void parseNtpStringNullTest() {
        TimeStamp.parseNtpString(null);
    }

    @Test
    public void toNtpTimeBase1Test() {
        assertEquals(NTP_TIME_MSB1_EXAMPLE, TimeStamp.toNtpTime(JAVA_TIME_MSB1_EXAMPLE));
    }

    @Test
    public void toNtpTimeBase0Test() {
        assertEquals(NTP_TIME_MSB0_EXAMPLE, TimeStamp.toNtpTime(JAVA_TIME_MSB0_EXAMPLE));
    }

    @Test
    public void toNtpTimeBase0EdgeTest() {
        // Time exactly at the boundary should use Base0
        assertEquals(NTP_TIME_MSB0_BASE, TimeStamp.toNtpTime(JAVA_TIME_MSB0_BASE));
    }
    
    @Test
    public void toNtpTimeBase1EdgeTest() {
         // Time just before the boundary should use Base1
        assertEquals(NTP_TIME_MSB0_BASE_MINUS_1, TimeStamp.toNtpTime(JAVA_TIME_MSB0_BASE_MINUS_1));
    }


    @Test
    public void hashCodeEqualObjectsTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(ts1.hashCode(), ts2.hashCode());
    }

    @Test
    public void hashCodeDifferentObjectsTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB0_EXAMPLE);
        // Highly likely hash codes will differ, though not strictly guaranteed by contract
        assertNotEquals(ts1.hashCode(), ts2.hashCode());
    }

    @Test
    public void equalsSameObjectTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertTrue(ts1.equals(ts1)); // Reflexive
    }

    @Test
    public void equalsEqualObjectTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertTrue(ts1.equals(ts2)); // Symmetric
        assertTrue(ts2.equals(ts1));
    }

    @Test
    public void equalsDifferentObjectTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB0_EXAMPLE);
        assertFalse(ts1.equals(ts2));
        assertFalse(ts2.equals(ts1));
    }

    @Test
    public void equalsNullTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertFalse(ts1.equals(null));
    }

    @Test
    public void equalsDifferentTypeTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        Object obj = new Object();
        assertFalse(ts1.equals(obj));
    }

    @Test
    public void toStringInstanceTest() {
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals("83aa7e8c.80000000", ts.toString());
    }

    @Test
    public void toStringStaticPaddingSecondsTest() {
        // Example where seconds part needs padding
        assertEquals("0000000c.80000000", TimeStamp.toString(NTP_TIME_MSB0_EXAMPLE));
    }

    @Test
    public void toStringStaticPaddingFractionTest() {
        // Example where fraction part needs padding
        long ntpTime = 0x83AA7E8C00000001L;
        assertEquals("83aa7e8c.00000001", TimeStamp.toString(ntpTime));
    }

    @Test
    public void toStringStaticPaddingBothTest() {
        // Example where both parts need padding
        long ntpTime = 0x0000000100000001L;
        assertEquals("00000001.00000001", TimeStamp.toString(ntpTime));
    }

    @Test
    public void toStringStaticZeroTest() {
        assertEquals("00000000.00000000", TimeStamp.toString(0L));
    }
    
    @Test
    public void toStringStaticMaxTest() {
        assertEquals("ffffffff.ffffffff", TimeStamp.toString(0xFFFFFFFFFFFFFFFFL));
    }

    @Test
    public void toDateStringFormatTest() {
        // Test lazy initialization and basic functionality.
        // Exact string depends on default Locale/TimeZone, so avoid strict matching.
        TimeStamp ts = new TimeStamp(NTP_TIME_EPOCH); // Epoch
        
        // Temporarily set default TimeZone and Locale for predictable output
        TimeZone defaultTZ = TimeZone.getDefault();
        Locale defaultLocale = Locale.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Locale.setDefault(Locale.US);

        try {
             String expectedFormat = "EEE, MMM dd yyyy HH:mm:ss.SSS"; // From TimeStamp.NTP_DATE_FORMAT
             SimpleDateFormat sdf = new SimpleDateFormat(expectedFormat, Locale.US);
             sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
             String expectedDateString = sdf.format(new Date(JAVA_TIME_EPOCH));

             String s1 = ts.toDateString(); // Initialize formatter
             String s2 = ts.toDateString(); // Use existing formatter

             assertNotNull(s1);
             assertFalse(s1.isEmpty());
             assertEquals("Should use cached formatter", s1, s2); 
             assertEquals(expectedDateString, s1); // Verify format with known settings
        } finally {
             // Restore original defaults
             TimeZone.setDefault(defaultTZ);
             Locale.setDefault(defaultLocale);
        }
    }

    @Test
    public void toUTCStringFormatTest() {
        // Test lazy initialization and basic UTC format.
        TimeStamp ts = new TimeStamp(NTP_TIME_EPOCH); // Epoch (0 ms Java time)

        String s1 = ts.toUTCString(); // Initialize formatter
        String s2 = ts.toUTCString(); // Use existing formatter

        assertNotNull(s1);
        assertFalse(s1.isEmpty());
        // Check for key components of the UTC string for Epoch time
        assertTrue("Should contain year 1970", s1.contains("1970"));
        assertTrue("Should contain Jan", s1.contains("Jan"));
        assertTrue("Should contain day 01", s1.contains("01"));
        assertTrue("Should contain 00:00:00.000", s1.contains("00:00:00.000"));
        assertTrue("Should end with UTC", s1.endsWith(" UTC"));
        assertEquals("Should use cached formatter", s1, s2);
    }
    
     @Test
    public void toUTCStringFormatMsb0Test() {
        // Test lazy initialization and basic UTC format for MSB 0 time
        TimeStamp ts = new TimeStamp(NTP_TIME_MSB0_BASE); // msb0baseTime
        
        String s1 = ts.toUTCString(); // Initialize formatter
        String s2 = ts.toUTCString(); // Use existing formatter

        assertNotNull(s1);
        assertFalse(s1.isEmpty());
        // Check for key components of the UTC string for msb0baseTime
        assertTrue("Should contain year 2036", s1.contains("2036"));
        assertTrue("Should contain Feb", s1.contains("Feb"));
        assertTrue("Should contain day 07", s1.contains("07"));
        assertTrue("Should contain 06:28:16.000", s1.contains("06:28:16.000"));
        assertTrue("Should end with UTC", s1.endsWith(" UTC"));
        assertEquals("Should use cached formatter", s1, s2);
    }


    @Test
    public void compareToLessThanTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB0_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE); // MSB1 times are generally larger NTP values
        assertTrue(ts1.compareTo(ts2) < 0);
        
        TimeStamp ts3 = new TimeStamp(100L);
        TimeStamp ts4 = new TimeStamp(200L);
        assertTrue(ts3.compareTo(ts4) < 0);
    }

    @Test
    public void compareToEqualTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        assertEquals(0, ts1.compareTo(ts2));
        assertEquals(0, ts2.compareTo(ts1));
    }

    @Test
    public void compareToGreaterThanTest() {
        TimeStamp ts1 = new TimeStamp(NTP_TIME_MSB1_EXAMPLE);
        TimeStamp ts2 = new TimeStamp(NTP_TIME_MSB0_EXAMPLE);
        assertTrue(ts1.compareTo(ts2) > 0);
        
        TimeStamp ts3 = new TimeStamp(200L);
        TimeStamp ts4 = new TimeStamp(100L);
        assertTrue(ts3.compareTo(ts4) > 0);
    }
}
```