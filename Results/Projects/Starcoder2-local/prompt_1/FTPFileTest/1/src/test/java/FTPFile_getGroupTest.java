// FTPFile_getGroupTest.java
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
 * FTPFile#public getGroup() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getGroupTest {
	private FTPFile file;
	private Calendar cal;
	private Date date;
	private TimeZone timeZone;
	private Formatter fmt;

	@Before
	public void setUp() {
		file = new FTPFile();
		cal = Calendar.getInstance();
		date = new Date();
		timeZone = TimeZone.getTimeZone("GMT");
		fmt = new Formatter(System.out);
	}

	@After
	public void tearDown() {
		file = null;
		cal = null;
		date = null;
		timeZone = null;
		fmt.close();
	}

	@Test
	public void test_getGroup_1() {
		file.setGroup("1");
		assertEquals("1", file.getGroup());
	}

	@Test
	public void test_getGroup_2() {
}
}