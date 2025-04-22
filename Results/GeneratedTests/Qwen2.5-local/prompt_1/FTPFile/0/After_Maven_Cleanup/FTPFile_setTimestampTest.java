// FTPFile_setTimestampTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Test class of FTPFile.
 * It contains 10 unit test cases for the
 * FTPFile#public setTimestamp(Calendar date) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_setTimestampTest {
    private FTPFile ftpFile;
    private Calendar calendar;

    @Before
    public void setUp() {
        ftpFile = new FTPFile();
        calendar = Calendar.getInstance();
    }

    @After
    public void tearDown() {
        ftpFile = null;
        calendar = null;
    }

    @Test
    public void testSetTimestamp_withValidCalendar() {
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2023, Calendar.JANUARY, 1);

        ftpFile.setTimestamp(expectedDate);
        assertEquals(expectedDate, ftpFile.getTimestamp());
    }

    @Test
    public void testSetTimestamp_withNullCalendar() {
        ftpFile.setTimestamp(null);
        assertNull(ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withDifferentTimeZone() {
        Calendar differentTimeZone = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        differentTimeZone.set(2023, Calendar.JANUARY, 1);

        ftpFile.setTimestamp(differentTimeZone);
        assertEquals(differentTimeZone, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withSameTimeZone() {
        Calendar sameTimeZone = Calendar.getInstance();
        sameTimeZone.set(2023, Calendar.JANUARY, 1);

        ftpFile.setTimestamp(sameTimeZone);
        assertEquals(sameTimeZone, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withCurrentTime() {
        Calendar currentDate = Calendar.getInstance();

        ftpFile.setTimestamp(currentDate);
        assertEquals(currentDate, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withPastDate() {
        Calendar pastDate = Calendar.getInstance();
        pastDate.add(Calendar.YEAR, -1);

        ftpFile.setTimestamp(pastDate);
        assertEquals(pastDate, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withFutureDate() {
        Calendar futureDate = Calendar.getInstance();
        futureDate.add(Calendar.YEAR, 1);

        ftpFile.setTimestamp(futureDate);
        assertEquals(futureDate, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withMidnight() {
        Calendar midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);

        ftpFile.setTimestamp(midnight);
        assertEquals(midnight, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withLeapSecond() {
        Calendar leapSecond = Calendar.getInstance();
        leapSecond.set(2015, Calendar.DECEMBER, 31, 23, 59, 59);
        leapSecond.set(Calendar.MILLISECOND, 999);

        ftpFile.setTimestamp(leapSecond);
        assertEquals(leapSecond, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withAllFieldsSet() {
        Calendar allFieldsSet = Calendar.getInstance();
        allFieldsSet.set(2023, Calendar.JANUARY, 1, 12, 34, 56);
        allFieldsSet.set(Calendar.MILLISECOND, 789);

        ftpFile.setTimestamp(allFieldsSet);
        assertEquals(allFieldsSet, ftpFile.getTimestamp());
    }
}