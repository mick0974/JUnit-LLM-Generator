 import org.junit.Test;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
public class TimeStampTest {
    private static final long MSB0_BASE_TIME = Long.parseLong("2085978496000");
    private static final long MSB1_BASE_TIME = -Long.parseLong("2208988800000");
    private static final String NTP_DATE_FORMAT = "EEE, MMM dd yyyy HH:mm:ss.SSS";
    @Test
    public void constructorLongTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals(0L, ts.ntpValue());
    }
    @Test
    public void constructorHexTest() throws NumberFormatException {
        TimeStamp ts = new TimeStamp("00000000.00000000");
        assertEquals(0L, ts.ntpValue());
        ts = new TimeStamp("c1a089bd.fc904f6d");
        assertEquals(0xc1a089bdL << 32 | 0xfc904f6dL, ts.ntpValue());
    }
    @Test(expected = NumberFormatException.class)
    public void constructorHexExceptionTest() throws NumberFormatException {
        new TimeStamp("invalid");
    }
    @Test
    public void constructorDateTest() {
        TimeStamp ts = new TimeStamp(new Date(0L));
        assertEquals(0L, ts.ntpValue());
    }
    @Test
    public void ntpValueTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals(0L, ts.ntpValue());
    }
    @Test
    public void getSecondsTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals(0L, ts.getSeconds());
        ts = new TimeStamp(0xFFFFFFFFL << 32);
        assertEquals(0xFFFFFFFFL, ts.getSeconds());
    }
    @Test
    public void getFractionTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals(0L, ts.getFraction());
        ts = new TimeStamp(0x00000000FFFFFFFFL);
        assertEquals(0xFFFFFFFFL, ts.getFraction());
    }
    @Test
    public void getTimeTest() {
        TimeStamp ts = new TimeStamp(MSB0_BASE_TIME);
        assertEquals(0L, ts.getTime());
        ts = new TimeStamp(MSB1_BASE_TIME);
        assertEquals(-Long.parseLong("2208988800000"), ts.getTime());
    }
    @Test
    public void getDateTest() {
        TimeStamp ts = new TimeStamp(MSB0_BASE_TIME);
        assertEquals(new Date(0L), ts.getDate());
        ts = new TimeStamp(MSB1_BASE_TIME);
        assertEquals(new Date(-Long.parseLong("2208988800000")), ts.getDate());
    }
    @Test
    public void getTimeStaticTest() {
        assertEquals(0L, TimeStamp.getTime(0L));
        long ntpTime = (System.currentTimeMillis() / 1000L) << 32;
        assertEquals(System.currentTimeMillis(), TimeStamp.getTime(ntpTime));
    }
    @Test
    public void getNtpTimeStaticTest() {
        TimeStamp ts = TimeStamp.getNtpTime(0L);
        assertEquals(0L, ts.ntpValue());
        ts = TimeStamp.getNtpTime(System.currentTimeMillis());
        assertEquals(System.currentTimeMillis(), ts.getTime());
    }
    @Test
    public void getCurrentTimeStaticTest() {
        TimeStamp ts = TimeStamp.getCurrentTime();
        assertTrue(ts.getTime() > System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(1));
        assertTrue(ts.getTime() < System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(1));
    }
    @Test
    public void decodeNtpHexStringTest() throws NumberFormatException {
        assertEquals(0L, TimeStamp.decodeNtpHexString("00000000.00000000"));
        assertEquals(0xc1a089bdL << 32 | 0xfc904f6dL, TimeStamp.decodeNtpHexString("c1a089bd.fc904f6d"));
    }
    @Test(expected = NumberFormatException.class)
    public void decodeNtpHexStringExceptionTest() throws NumberFormatException {
        TimeStamp.decodeNtpHexString("invalid");
    }
    @Test
    public void parseNtpStringTest() throws NumberFormatException {
        TimeStamp ts = TimeStamp.parseNtpString("00000000.00000000");
        assertEquals(0L, ts.ntpValue());
        ts = TimeStamp.parseNtpString("c1a089bd.fc904f6d");
        assertEquals(0xc1a089bdL << 32 | 0xfc904f6dL, ts.ntpValue());
    }
    @Test(expected = NumberFormatException.class)
    public void parseNtpStringExceptionTest() throws NumberFormatException {
        TimeStamp.parseNtpString("invalid");
    }
    @Test
    public void toNtpTimeStaticTest() {
        assertEquals(0L, TimeStamp.toNtpTime(0L));
        long currentTimeMillis = System.currentTimeMillis();
        long ntpTime = TimeStamp.toNtpTime(currentTimeMillis);
        assertEquals(currentTimeMillis, TimeStamp.getTime(ntpTime));
    }
    @Test
    public void hashCodeTest() {
        TimeStamp ts1 = new TimeStamp(0L);
        TimeStamp ts2 = new TimeStamp(0L);
        TimeStamp ts3 = new TimeStamp(1L);
        assertEquals(ts1.hashCode(), ts2.hashCode());
        assertNotEquals(ts1.hashCode(), ts3.hashCode());
    }
    @Test
    public void equalsTest() {
        TimeStamp ts1 = new TimeStamp(0L);
        TimeStamp ts2 = new TimeStamp(0L);
        TimeStamp ts3 = new TimeStamp(1L);
        assertTrue(ts1.equals(ts2));
        assertFalse(ts1.equals(ts3));
        assertFalse(ts1.equals(null));
        assertFalse(ts1.equals(new Object()));
    }
    @Test
    public void toStringTest() {
        TimeStamp ts = new TimeStamp(0L);
        assertEquals("00000000.00000000", ts.toString());
        ts = new TimeStamp(0xc1a089bdL << 32 | 0xfc904f6dL);
        assertEquals("c1a089bd.fc904f6d", ts.toString());
    }
    @Test
    public void toDateStringTest() {
        TimeStamp ts = new TimeStamp(MSB0_BASE_TIME);
        assertEquals("Thu, Jan 01 1970 00:00:00.000", ts.toDateString());
        ts = new TimeStamp(MSB1_BASE_TIME);
        assertEquals("Thu, Jan 01 1970 00:00:00.000", ts.toDateString());
    }
    @Test
    public void toUTCStringTest() {
        TimeStamp ts = new TimeStamp(MSB0_BASE_TIME);
        assertEquals("Thu, Jan 01 1970 00:00:00.000 UTC", ts.toUTCString());
        ts = new TimeStamp(MSB1_BASE_TIME);
        assertEquals("Thu, Jan 01 1970 00:00:00.000 UTC", ts.toUTCString());
    }
    @Test
    public void compareToTest() {
        TimeStamp ts1 = new TimeStamp(0L);
        TimeStamp ts2 = new TimeStamp(0L);
        TimeStamp ts3 = new TimeStamp(1L);
        assertEquals(0, ts1.compareTo(ts2));
        assertEquals(-1, ts1.compareTo(ts3));
        assertEquals(1, ts3.compareTo(ts1));
    }
}