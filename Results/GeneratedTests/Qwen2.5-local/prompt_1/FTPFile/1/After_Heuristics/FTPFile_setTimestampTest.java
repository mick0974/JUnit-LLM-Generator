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
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.set(2020, Calendar.JANUARY, 1, 12, 0, 0);
        ftpFile.setTimestamp(testCalendar);

        assertEquals(testCalendar, ftpFile.getTimestamp());
    }

    @Test
    public void testSetTimestamp_withNullCalendar() {
        ftpFile.setTimestamp(null);

        assertNull(ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withCurrentTime() {
        Calendar now = Calendar.getInstance();
        ftpFile.setTimestamp(now);

        assertEquals(now, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withPastDate() {
        Calendar past = Calendar.getInstance();
        past.add(Calendar.YEAR, -1);
        ftpFile.setTimestamp(past);

        assertEquals(past, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withFutureDate() {
        Calendar future = Calendar.getInstance();
        future.add(Calendar.YEAR, 1);
        ftpFile.setTimestamp(future);

        assertEquals(future, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withDifferentTimeZone() {
        Calendar differentTimeZone = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        ftpFile.setTimestamp(differentTimeZone);

        assertEquals(differentTimeZone, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withSameTimeZone() {
        Calendar sameTimeZone = Calendar.getInstance();
        ftpFile.setTimestamp(sameTimeZone);

        assertEquals(sameTimeZone, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withZeroHour() {
        Calendar zeroHour = Calendar.getInstance();
        zeroHour.set(2020, Calendar.JANUARY, 1, 0, 0, 0);
        ftpFile.setTimestamp(zeroHour);

        assertEquals(zeroHour, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withAllFieldsSet() {
        Calendar allFieldsSet = Calendar.getInstance();
        allFieldsSet.set(2020, Calendar.JANUARY, 1, 12, 30, 45);
        allFieldsSet.set(Calendar.MILLISECOND, 500);
        ftpFile.setTimestamp(allFieldsSet);

        assertEquals(allFieldsSet, ftpFile.getTimestamp());
    }
    
    @Test
    public void testSetTimestamp_withEmptyCalendar() {
        Calendar emptyCalendar = Calendar.getInstance();
        emptyCalendar.clear();
        ftpFile.setTimestamp(emptyCalendar);

        assertEquals(emptyCalendar, ftpFile.getTimestamp());
    }
}