 import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeStamp_getTimeTest {
    private static final long MSB1_BASE_TIME_JAVA = -Long.parseLong("2208988800000");
    private static final long MSB0_BASE_TIME_JAVA = Long.parseLong("2085978496000");
    private long createNtpTimestamp(long seconds, long fraction) {
        return (seconds << 32) | fraction;
    }
    @Test
    public void testGetTime_epoch() {
        long ntpEpochSeconds = Long.parseLong("2208988800");
        ntpEpochSeconds |= 0x80000000L;
        long ntpTimeValue = createNtpTimestamp(ntpEpochSeconds, 0);
        long expectedJavaTime = 0L;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Java epoch time should be 0", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_before2036_zeroFraction() {
        long javaTime = Long.parseLong("1704067200000");
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time before 2036 with zero fraction", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_before2036_halfSecondFraction() {
        long javaTime = Long.parseLong("1704067200500");
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time before 2036 with 0.5s fraction", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_before2036_fractionRoundsDownToOneMs() {
        long javaTime = Long.parseLong("1704067200001");
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time before 2036 fraction rounds down to 1ms", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_before2036_fractionRoundsUpTo999Ms() {
        long javaTime = Long.parseLong("1704067200999");
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time before 2036 fraction rounds up to 999ms", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_atBoundary() {
        long ntpTimeValue = 0x0000000000000000L;
        long expectedJavaTime = MSB0_BASE_TIME_JAVA;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time at the 2036 boundary", expectedJavaTime, actualJavaTime);
        assertEquals("Confirming expected boundary time", Long.parseLong("2085978496000"), actualJavaTime);
    }
    @Test
    public void testGetTime_justBeforeBoundary() {
        long javaTime = MSB0_BASE_TIME_JAVA - 1L;
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time just before the 2036 boundary", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_after2036_zeroFraction() {
        long javaTime = Long.parseLong("2208988800000");
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time after 2036 with zero fraction", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_after2036_halfSecondFraction() {
        long javaTime = Long.parseLong("2208988800500");
        long ntpTimeValue = TimeStamp.toNtpTime(javaTime);
        long expectedJavaTime = javaTime;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Time after 2036 with 0.5s fraction", expectedJavaTime, actualJavaTime);
    }
    @Test
    public void testGetTime_zeroNtpTimestamp() {
        long ntpTimeValue = 0L;
        long expectedJavaTime = MSB0_BASE_TIME_JAVA;
        long actualJavaTime = TimeStamp.getTime(ntpTimeValue);
        assertEquals("Zero NTP timestamp should resolve to msb0baseTime", expectedJavaTime, actualJavaTime);
    }
}