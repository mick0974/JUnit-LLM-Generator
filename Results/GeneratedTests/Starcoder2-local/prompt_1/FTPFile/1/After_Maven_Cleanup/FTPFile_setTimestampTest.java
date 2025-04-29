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
	private FTPFile _ftpFile;
	private Calendar _calendar;
	private Date _date;
	private TimeZone _timeZone;
	private Formatter _formatter;

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_calendar = Calendar.getInstance();
		_date = new Date();
		_timeZone = TimeZone.getDefault();
		_formatter = new Formatter();
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_calendar = null;
		_date = null;
		_timeZone = null;
		_formatter = null;
	}

	@Test
	public void test_setTimestamp_1() {
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_2() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_3() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_4() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_5() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_6() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_7() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_8() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_9() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_setTimestamp_10() {
		_calendar.set(Calendar.YEAR, 2015);
		_calendar.set(Calendar.MONTH, Calendar.JANUARY);
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		_calendar.set(Calendar.HOUR_OF_DAY, 0);
		_calendar.set(Calendar.MINUTE, 0);
		_calendar.set(Calendar.SECOND, 0);
		_calendar.set(Calendar.MILLISECOND, 0);
		_calendar.setTimeZone(_timeZone);
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}
}