// FTPFile_getUserTest.java
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
 * FTPFile#public getUser() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_getUserTest {
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
		timezone = "America/New_York";
	}

	@After
	public void tearDown() {
		ftpFile = null;
		calendar = null;
		date = null;
		fmt = null;
		timezone = null;
	}










}