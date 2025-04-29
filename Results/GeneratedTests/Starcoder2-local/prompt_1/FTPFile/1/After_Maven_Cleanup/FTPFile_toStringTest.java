// FTPFile_toStringTest.java
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
 * FTPFile#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_toStringTest {
	private FTPFile file;
	private Calendar cal;
	private Date date;
	private TimeZone timeZone;
	private Formatter fmt;
	private String timeZoneString;

	@Before

	@After
	public void tearDown() {
		file = null;
		cal = null;
		date = null;
		timeZone = null;
		timeZoneString = null;
		fmt = null;
	}




}