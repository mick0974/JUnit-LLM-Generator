// FTPFile_getTimestampTest.java
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
 * FTPFile#public getTimestamp() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getTimestampTest {
	private FTPFile _ftpFile;
	private Calendar _calendar;
	private Date _date;
	private Formatter _formatter;
	private String _timezone;

	@Before
	public void setUp() {
		_ftpFile = new FTPFile();
		_calendar = Calendar.getInstance();
		_date = new Date();
		_formatter = new Formatter();
		_timezone = "GMT";
	}

	@After
	public void tearDown() {
		_ftpFile = null;
		_calendar = null;
		_date = null;
		_formatter = null;
		_timezone = null;
	}

	@Test
	public void test_getTimestamp_1() {
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_2() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_3() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_4() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_5() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_6() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_7() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_8() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_9() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}

	@Test
	public void test_getTimestamp_10() {
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		_ftpFile.setTimestamp(_calendar);
		_calendar.setTimeZone(TimeZone.getTimeZone(_timezone));
		assertEquals(_calendar, _ftpFile.getTimestamp());
	}
}