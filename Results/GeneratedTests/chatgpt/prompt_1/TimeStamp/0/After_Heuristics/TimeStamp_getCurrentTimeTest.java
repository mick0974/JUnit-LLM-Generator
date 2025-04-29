// TimeStamp_getCurrentTimeTest.java
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
 * TimeStamp#public static getCurrentTime() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class TimeStamp_getCurrentTimeTest {

private static final long EPSILON = 1000; // 1 second tolerance

    private DateFormat formatter;

    @Before
    public void setUp() {
        formatter = new SimpleDateFormat(TimeStamp.NTP_DATE_FORMAT, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @After
    public void tearDown() {
        formatter = null;
    }

    @Test
    public void testGetCurrentTime_NotNull() {
        // Arrange & Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();

        // Assert
        assertNotNull("The current timestamp should not be null.", currentTime);
    }

    @Test
    public void testGetCurrentTime_CloseToSystemCurrentTime() {
        // Arrange
        long systemCurrentTime = System.currentTimeMillis();

        // Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        Date currentDateTime = currentTime.getDate();

        // Assert
        assertTrue("The current timestamp should be very close to the system time.",
                Math.abs(currentDateTime.getTime() - systemCurrentTime) < EPSILON);
    }

    @Test
    public void testGetCurrentTime_NtpAndJavaTimeConsistent() {
        // Arrange & Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        long javaTime = currentTime.getTime();
        long ntpTime = TimeStamp.toNtpTime(javaTime);

        // Assert
        assertEquals("NTP time should match Java time when converted.", currentTime.ntpValue(), ntpTime);
    }

    @Test
    public void testGetCurrentTime_ToDateStringFormat() {
        // Arrange & Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        String dateString = currentTime.toDateString();

        // Assert
        try {
            formatter.parse(dateString);
        } catch (Exception e) {
            fail("Date string should be parsable with the defined format.");
        }
    }

    @Test
    public void testGetCurrentTime_ToUTCStringFormat() {
        // Arrange & Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        String utcString = currentTime.toUTCString();

        // Assert
        String dateStrPart = utcString.substring(0, utcString.lastIndexOf(' '));
        String timeZonePart = utcString.substring(utcString.lastIndexOf(' ') + 1);

        assertEquals("Time zone part should be 'UTC'.", "UTC", timeZonePart);

        try {
            formatter.parse(dateStrPart);
        } catch (Exception e) {
            fail("UTC date string should be parsable with the defined format.");
        }
    }

    @Test
    public void testGetCurrentTime_SecondsField() {
        // Arrange & Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        long secondsField = currentTime.getSeconds();

        // Assert
        assertNotNull("Seconds field should not be null.", secondsField);
    }

    @Test
    public void testGetCurrentTime_FractionField() {
        // Arrange & Act
        TimeStamp currentTime = TimeStamp.getCurrentTime();
        long fractionField = currentTime.getFraction();

        // Assert
        assertNotNull("Fraction field should not be null.", fractionField);
    }

    @Test
    public void testGetCurrentTime_EqualityCheck() {
        // Arrange
        long systemCurrentTime = System.currentTimeMillis();

        // Act
        TimeStamp ts1 = TimeStamp.getCurrentTime();
        TimeStamp ts2 = TimeStamp.getCurrentTime();

        // Assert
        assertEquals("Two consecutive calls should provide near equal timestamps due to close time proximity.",
                ts1.compareTo(ts2), 0);
    }

    @Test
    public void testGetCurrentTime_HashCodeConsistency() {
        // Arrange
        TimeStamp currentTime = TimeStamp.getCurrentTime();

        // Act
        int hashCode1 = currentTime.hashCode();
        int hashCode2 = currentTime.hashCode();

        // Assert
        assertEquals("HashCode should remain consistent across multiple calls.", hashCode1, hashCode2);
    }

    @Test(expected = NullPointerException.class)
    public void testTimeStampConstructorWithNullDate() {
        // Arrange & Act
        @SuppressWarnings("unused")
        TimeStamp timestamp = new TimeStamp((Date) null);
    }
}