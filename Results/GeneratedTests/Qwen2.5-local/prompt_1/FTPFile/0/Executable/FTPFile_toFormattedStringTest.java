// FTPFile_toFormattedStringTest.java
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
 * FTPFile#public toFormattedString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FTPFile_toFormattedStringTest {
    private FTPFile ftpFile;
    private Calendar calendar;

    @Before
    public void setUp() throws Exception {
        ftpFile = new FTPFile();
        calendar = Calendar.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        ftpFile = null;
        calendar = null;
    }



}