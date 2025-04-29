// FTPFile_getLinkTest.java
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
 * FTPFile#public getLink() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getLinkTest {
	private FTPFile ftpFile;
	private Calendar calendar;
	private Date date;
	private Formatter fmt;
	private String timezone;

	@Before
	public void setUp() {
		ftpFile = new FTPFile();
		calendar = Calendar.getInstance();
		date = new Date();
		fmt = new Formatter();
		timezone = "GMT";
	}

	@After
	public void tearDown() {
		ftpFile = null;
		calendar = null;
		date = null;
		fmt = null;
		timezone = null;
	}

	@Test
	public void test_getLink_01() {
		ftpFile.setRawListing("drwxr-xr-x    2 1000     1000          4096 2012-01-01 00:00:00.000000000 +0000");
		assertEquals("drwxr-xr-x", ftpFile.getRawListing());
		assertEquals(FTPFile.DIRECTORY_TYPE, ftpFile.getType());
		assertEquals(2, ftpFile.getHardLinkCount());
		assertEquals(1000, ftpFile.getUser());
		assertEquals(1000, ftpFile.getGroup());
		assertEquals(4096, ftpFile.getSize());
		assertEquals(2012, calendar.get(Calendar.YEAR));
		assertEquals(1, calendar.get(Calendar.MONTH));
		assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, calendar.get(Calendar.MINUTE));
		assertEquals(0, calendar.get(Calendar.SECOND));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(TimeZone.getTimeZone(timezone), calendar.getTimeZone());
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.MILLISECOND));
		assertEquals(0, calendar.get(Calendar.ZONE_OFFSET));
		assertEquals(0, calendar.get(Calendar.DST_OFFSET));
		assertEquals(0, calendar.get(Calendar.ERA));
		assertEquals(0, calendar.get(Calendar.LEAP_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.WEEK_OF_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(0, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(0, calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		assertEquals(0, calendar.get(Calendar.AM_PM));
	
}
}