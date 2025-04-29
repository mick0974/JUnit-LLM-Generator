// TimeStamp_hashCodeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Test class of TimeStamp.
 * It contains 10 unit test cases for the
 * TimeStamp#public hashCode() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_hashCodeTest {

 private TimeStamp timeStamp1;
    private TimeStamp timeStamp2;
    private TimeStamp timeStamp3;
    @Before
    public void setUp() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date1 = format.parse("2023-01-01 00:00:00.000");
            Date date2 = format.parse("2037-01-01 00:00:00.000");
            Date date3 = format.parse("1970-01-01 00:00:00.000");
            timeStamp1 = new TimeStamp(date1);
            timeStamp2 = new TimeStamp(date2);
            timeStamp3 = new TimeStamp(date3);
        } catch (Exception e) {
            fail("Failed to parse dates in setup due to: " + e.getMessage());
        }
    }
    @After
    public void tearDown() {
        timeStamp1 = null;
        timeStamp2 = null;
        timeStamp3 = null;
    }
    @Test
    public void testHashCodeConsistency() {
        int initialHashCode = timeStamp1.hashCode();
        assertEquals(initialHashCode, timeStamp1.hashCode());
    }
    @Test
    public void testHashCodeEqualityForSameTimestamp() {
        TimeStamp equivalentTimeStamp = new TimeStamp(timeStamp1.ntpValue());
        assertEquals(timeStamp1.hashCode(), equivalentTimeStamp.hashCode());
    }
    @Test
    public void testHashCodeInequalityForDifferentTimestamps() {
        assertNotEquals(timeStamp1.hashCode(), timeStamp2.hashCode());
    }
    @Test
    public void testHashCodeWithDifferentBaseTimes() {
        long timestamp1 = TimeStamp.toNtpTime(-Long.parseLong("2208960000000"));
        long timestamp2 = TimeStamp.toNtpTime(Long.parseLong("2098713600000"));
        TimeStamp ts1 = new TimeStamp(timestamp1);
        TimeStamp ts2 = new TimeStamp(timestamp2);
        assertNotEquals(ts1.hashCode(), ts2.hashCode());
    }
    @Test
    public void testHashCodeWithDifferentJavaTimesSameNtp() {
        long ntp = timeStamp1.ntpValue();
        TimeStamp tsNew1 = new TimeStamp(ntp);
        TimeStamp tsNew2 = new TimeStamp(ntp);
        assertEquals(tsNew1.hashCode(), tsNew2.hashCode());
    }
    @Test
    public void testHashCodeZeroTime() {
        TimeStamp zeroTimeStamp = new TimeStamp(0);
        assertEquals(0, zeroTimeStamp.hashCode());
    }
    @Test
    public void testHashCodeNegativeTime() {
        try {
            TimeStamp negativeTimeStamp = new TimeStamp(-1);
            fail("Expected IllegalArgumentException for negative time.");
        } catch (IllegalArgumentException e) {
        }
    }
    @Test
    public void testHashCodeCurrentTime() {
        TimeStamp current = TimeStamp.getCurrentTime();
        int hashcode = current.hashCode();
        assertNotEquals(0, hashcode);
    }
    @Test
    public void testEquivalentDateTimesWithDifferentTimeZonesProduceDifferentHashes() {
        long timestamp = timeStamp3.ntpValue();
        TimeStamp tsUTC = new TimeStamp(timestamp);
        TimeStamp tsOffset = new TimeStamp(timestamp);
        tsOffset.toFixedMethodToChangeZone();
        assertNotEquals(tsUTC.hashCode(), tsOffset.hashCode());
    }
    @Test
    public void testHashCodeEqualityForSameNtpValuesDifferentConstructionMethods() {
        long ntpValue = timeStamp1.ntpValue();
        TimeStamp fromTime = new TimeStamp(TimeStamp.getTime(ntpValue));
        TimeStamp fromNtp = new TimeStamp(ntpValue);
        assertEquals(fromTime.hashCode(), fromNtp.hashCode());
    }
}