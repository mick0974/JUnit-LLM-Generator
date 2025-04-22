 import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;
public class TimeStampTest {
    @Test
    public void constructorLongTest() {
        long ntpTimeValue = 0x83AA7E80BBAB7B34L;
        TimeStamp timeStamp = new TimeStamp(ntpTimeValue);
        assertEquals(ntpTimeValue, timeStamp.ntpValue());
    }
    @Test
    public void constructorHexStringTest() {
        String hexString = "83aa7e80.bbab7b34";
        TimeStamp timeStamp = new TimeStamp(hexString);
        assertEquals(0x83AA7E80BBAB7B34L, timeStamp.ntpValue());
    }
    @Test(expected = NumberFormatException.class)
    public void constructorInvalidHexStringTest() {
        new TimeStamp("invalid_hex");
    }
    @Test
    public void constructorDateTest() {
        Date date = new Date(0);
        TimeStamp timeStamp = new TimeStamp(date);
        assertEquals(0x83AA7E8000000000L, timeStamp.ntpValue());
    }
    @Test
    public void ntpValueTest() {
        long ntpTimeValue = 0x83AA7E80BBAB7B34L;
        TimeStamp timeStamp = new TimeStamp(ntpTimeValue);
        assertEquals(ntpTimeValue, timeStamp.ntpValue());
    }
    @Test
    public void getSecondsTest() {
        TimeStamp timeStamp = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertEquals(0x83AA7E80L, timeStamp.getSeconds());
    }
    @Test
    public void getFractionTest() {
        TimeStamp timeStamp = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertEquals(0xBBAB7B34L, timeStamp.getFraction());
    }
    @Test
    public void toStringTest() {
        TimeStamp timeStamp = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertEquals("83aa7e80.bbab7b34", timeStamp.toString());
    }
    @Test
    public void toDateStringTest() {
        TimeStamp timeStamp = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertNotNull(timeStamp.toDateString());
    }
    @Test
    public void toUTCStringTest() {
        TimeStamp timeStamp = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertNotNull(timeStamp.toUTCString());
    }
    @Test
    public void hashCodeTest() {
        TimeStamp timeStamp = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertEquals((int) (0x83AA7E80BBAB7B34L ^ (0x83AA7E80BBAB7B34L >>> 32)), timeStamp.hashCode());
    }
    @Test
    public void equalsSameObjectTest() {
        TimeStamp timeStamp1 = new TimeStamp(0x83AA7E80BBAB7B34L);
        TimeStamp timeStamp2 = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertTrue(timeStamp1.equals(timeStamp2));
    }
    @Test
    public void equalsDifferentObjectTest() {
        TimeStamp timeStamp1 = new TimeStamp(0x83AA7E80BBAB7B34L);
        TimeStamp timeStamp2 = new TimeStamp(0x83AA7E81BBAB7B34L);
        assertFalse(timeStamp1.equals(timeStamp2));
    }
    @Test
    public void compareToLessThanTest() {
        TimeStamp timeStamp1 = new TimeStamp(0x83AA7E80BBAB7B34L);
        TimeStamp timeStamp2 = new TimeStamp(0x83AA7E81BBAB7B34L);
        assertTrue(timeStamp1.compareTo(timeStamp2) < 0);
    }
    @Test
    public void compareToEqualsTest() {
        TimeStamp timeStamp1 = new TimeStamp(0x83AA7E80BBAB7B34L);
        TimeStamp timeStamp2 = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertEquals(0, timeStamp1.compareTo(timeStamp2));
    }
    @Test
    public void compareToGreaterThanTest() {
        TimeStamp timeStamp1 = new TimeStamp(0x83AA7E81BBAB7B34L);
        TimeStamp timeStamp2 = new TimeStamp(0x83AA7E80BBAB7B34L);
        assertTrue(timeStamp1.compareTo(timeStamp2) > 0);
    }
}