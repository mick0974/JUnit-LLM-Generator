```java
import org.junit.Test;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * JUnit 4 Tests for the TimeStamp class achieving 100% line coverage.
 * Tests are atomic and do not use @Before or @After methods.
 * Method names follow the [MethodUnderTest][Scenario]Test template.
 */
public class TimeStampTest {

	// Baseline NTP time if bit-0=0 is 7-Feb-2036 @ 06:28:16 UTC
	private static final long MSB0_BASE_TIME_JAVA = 2085978496000L;
	// Baseline NTP time if bit-0=1 is 1-Jan-1900 @ 00:00:00 UTC (as per value)
	private static final long MSB1_BASE_TIME_JAVA = -2208988800000L;

	// Test Time 1 (Before 2036 epoch): Java Epoch January 1, 1970 00:00:00 GMT
	private static final long JAVA_TIME_1970 = 0L;
	private static final long NTP_TIME_1970 = 0x83aa7e8000000000L; // Calculated: toNtpTime(0L)
	private static final String NTP_HEX_1970 = "83aa7e80.00000000";

	// Test Time 2 (After 2036 epoch): January 1, 2040 00:00:00 GMT
	private static final long JAVA_TIME_2040 = 2208988800000L;
	private static final long NTP_TIME_2040 = 0x0758ab0000000000L; // Calculated: toNtpTime(2208988800000L)
	private static final String NTP_HEX_2040 = "0758ab00.00000000";

	// Test Time 3 (With fraction, before 2036): From example comment, adjusted for implementation rounding
	// Corresponds to Tue, Dec 10 2002 10:41:49.987 GMT (note the .987 due to rounding)
	private static final long JAVA_TIME_2002_FRAC = 1039534909987L; // Calculated: getTime(0xc1a089bdfc904f6dL)
	private static final long NTP_TIME_2002_FRAC = 0xc1a089bdfc904f6dL; // Example hex value
	private static final String NTP_HEX_2002_FRAC = "c1a089bd.fc904f6d";

	// Test Time 4 (Java time leading to specific NTP fractional part)
	private static final long JAVA_TIME_2002_FRAC_INPUT = 1039534909986L; // Original comment value
	// Calculated NTP time for JAVA_TIME_2002_FRAC_INPUT
	private static final long NTP_TIME_2002_FRAC_OUTPUT = 0xc1a089bdfc904f6eL;

	// Test Time 5 (Zero NTP time)
	private static final long NTP_TIME_ZERO = 0L;
	// Corresponding Java time for NTP_TIME_ZERO depends on MSB logic in getTime.
	// If MSB=0 (as in 0L), use msb0baseTime.
	private static final long JAVA_TIME_NTP_ZERO = MSB0_BASE_TIME_JAVA; // getTime(0L)
	private static final String NTP_HEX_ZERO = "00000000.00000000";

	// Test Time 6 (Padding check)
	private static final long NTP_TIME_PADDING = 0x000123450006789AL;
	private static final String NTP_HEX_PADDING = "00012345.0006789a";


	// *** Constructor Tests ***

	@Test
	public void ConstructorLongNominalTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
	}

	@Test
	public void ConstructorLongZeroTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_ZERO);
		assertEquals(NTP_TIME_ZERO, ts.ntpValue());
	}

	@Test
	public void ConstructorStringValidWithDotTest() {
		TimeStamp ts = new TimeStamp(NTP_HEX_2002_FRAC);
		assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
	}

	@Test
	public void ConstructorStringValidNoDotTest() {
		String hexNoDot = "c1a089bd";
		// Expect seconds part << 32
		long expectedNtp = 0xc1a089bd00000000L;
		TimeStamp ts = new TimeStamp(hexNoDot);
		assertEquals(expectedNtp, ts.ntpValue());
	}

	@Test
	public void ConstructorStringValidZeroTest() {
		TimeStamp ts = new TimeStamp("0"); // Interpreted as "0.0" effectively
		assertEquals(0L, ts.ntpValue());
	}

	@Test
	public void ConstructorStringValidZeroWithDotTest() {
		TimeStamp ts = new TimeStamp("0.0");
		assertEquals(0L, ts.ntpValue());
	}

	@Test
	public void ConstructorStringValidEmptyTest() {
		TimeStamp ts = new TimeStamp(""); // Should be treated as 0
		assertEquals(0L, ts.ntpValue());
	}

	@Test(expected = NumberFormatException.class)
	public void ConstructorStringNullTest() {
		new TimeStamp(null);
	}

	@Test(expected = NumberFormatException.class)
	public void ConstructorStringInvalidHexTest() {
		new TimeStamp("invalid.hex");
	}

	@Test(expected = NumberFormatException.class)
	public void ConstructorStringInvalidFormatJustDotTest() {
		new TimeStamp("."); // Invalid hex representation
	}

	@Test(expected = NumberFormatException.class)
	public void ConstructorStringInvalidFormatLeadingDotTest() {
		new TimeStamp(".fc904f6d"); // Empty string before dot causes parseLong error
	}

	@Test(expected = NumberFormatException.class)
	public void ConstructorStringInvalidFormatTrailingDotTest() {
		new TimeStamp("c1a089bd."); // Empty string after dot causes parseLong error
	}

	@Test
	public void ConstructorDateNominalTest() {
		Date date = new Date(JAVA_TIME_2002_FRAC_INPUT); // Use 986ms input
		TimeStamp ts = new TimeStamp(date);
		// Expect NTP time calculated from 986ms input
		assertEquals(NTP_TIME_2002_FRAC_OUTPUT, ts.ntpValue());
	}

	@Test
	public void ConstructorDateNullTest() {
		TimeStamp ts = new TimeStamp((Date) null);
		assertEquals(0L, ts.ntpValue()); // Should default to 0 NTP time
	}

	@Test
	public void ConstructorDateEpochTest() {
		Date date = new Date(JAVA_TIME_1970); // Java epoch
		TimeStamp ts = new TimeStamp(date);
		assertEquals(NTP_TIME_1970, ts.ntpValue());
	}

	@Test
	public void ConstructorDateBefore2036Test() {
		// Use JAVA_TIME_1970 which is before 2036
		Date date = new Date(JAVA_TIME_1970);
		TimeStamp ts = new TimeStamp(date);
		assertEquals(NTP_TIME_1970, ts.ntpValue());
		// Verify MSB is set (part of toNtpTime logic)
		assertTrue("MSB should be 1 for dates < 2036", (ts.ntpValue() & 0x8000000000000000L) != 0);
	}

	@Test
	public void ConstructorDateAfter2036Test() {
		Date date = new Date(JAVA_TIME_2040); // After 2036
		TimeStamp ts = new TimeStamp(date);
		assertEquals(NTP_TIME_2040, ts.ntpValue());
		// Verify MSB is not set (part of toNtpTime logic)
		assertTrue("MSB should be 0 for dates >= 2036", (ts.ntpValue() & 0x8000000000000000L) == 0);
	}

	// *** Getter Tests ***

	@Test
	public void NtpValueReturnInternalValueTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
	}

	@Test
	public void GetSecondsReturnsCorrectValueTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		long expectedSeconds = (NTP_TIME_2002_FRAC >>> 32) & 0xffffffffL;
		assertEquals(expectedSeconds, ts.getSeconds());
	}

	@Test
	public void GetFractionReturnsCorrectValueTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		long expectedFraction = NTP_TIME_2002_FRAC & 0xffffffffL;
		assertEquals(expectedFraction, ts.getFraction());
	}

	// *** Conversion Method Tests ***

	@Test
	public void GetTimeMsb1BaseTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_1970); // MSB = 1
		assertEquals(JAVA_TIME_1970, ts.getTime());
	}

	@Test
	public void GetTimeMsb0BaseTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2040); // MSB = 0
		assertEquals(JAVA_TIME_2040, ts.getTime());
	}

	@Test
	public void GetTimeFractionRoundingTest() {
		// Use the example value where rounding resulted in .987ms
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		assertEquals(JAVA_TIME_2002_FRAC, ts.getTime());
	}

	@Test
	public void GetTimeZeroTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_ZERO);
		// getTime uses msb0baseTime when MSB is 0
		assertEquals(JAVA_TIME_NTP_ZERO, ts.getTime());
	}

	@Test
	public void GetDateReturnsCorrectDateTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_1970);
		Date date = ts.getDate();
		assertNotNull(date);
		assertEquals(JAVA_TIME_1970, date.getTime());

		TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
		Date date2 = ts2.getDate();
		assertNotNull(date2);
		assertEquals(JAVA_TIME_2002_FRAC, date2.getTime());
	}

	// *** Static Conversion Method Tests ***

	@Test
	public void GetTimeStaticMsb1BaseTest() {
		assertEquals(JAVA_TIME_1970, TimeStamp.getTime(NTP_TIME_1970));
	}

	@Test
	public void GetTimeStaticMsb0BaseTest() {
		assertEquals(JAVA_TIME_2040, TimeStamp.getTime(NTP_TIME_2040));
	}

	@Test
	public void GetTimeStaticFractionRoundingTest() {
		// Test with NTP_TIME_2002_FRAC (hex: c1a089bd.fc904f6d) which yields 987ms
		assertEquals(JAVA_TIME_2002_FRAC, TimeStamp.getTime(NTP_TIME_2002_FRAC));

		// Test with a different fraction to ensure rounding works generally
		// 0.5 seconds = 0x80000000 fraction
		long ntpHalfSecond = NTP_TIME_1970 | 0x80000000L; // NTP time for 1970-01-01 00:00:00.500
		// Expect Java time = 0L + 500ms
		assertEquals(500L, TimeStamp.getTime(ntpHalfSecond));

		// Test fraction slightly below 0.5ms threshold
		long ntpFracBelowHalfMs = NTP_TIME_1970 | 0x00008000L; // Corresponds to ~0.12ms
		// round(1000 * 0x8000 / 0x100000000) = round(1000 * 32768 / 4294967296) = round(0.007...) = 0
		assertEquals(JAVA_TIME_1970, TimeStamp.getTime(ntpFracBelowHalfMs));

		// Test fraction slightly above 0.5ms threshold
		long ntpFracAboveHalfMs = NTP_TIME_1970 | 0x00008888L; // Corresponds to ~0.13ms
		// round(1000 * 0x8888 / 0x100000000) = round(1000 * 34952 / 4294967296) = round(0.008...) = 0
		// Need a value that rounds to 1ms. 1ms = frac / 0x100000000 * 1000 => frac = 0x100000000 / 1000 = 4294967.296
		// Rounding threshold is 0.5ms, corresponding frac = 4294967.296 / 2 = 2147483.6... = 0x20C49B
		long ntpFracRoundUp = NTP_TIME_1970 | 0x20C49BL; // Should round to 1ms
		assertEquals(JAVA_TIME_1970 + 1L, TimeStamp.getTime(ntpFracRoundUp));

		long ntpFracRoundDown = NTP_TIME_1970 | 0x20C49AL; // Should round to 0ms
		assertEquals(JAVA_TIME_1970, TimeStamp.getTime(ntpFracRoundDown));
	}


	@Test
	public void GetTimeStaticZeroTest() {
		assertEquals(JAVA_TIME_NTP_ZERO, TimeStamp.getTime(NTP_TIME_ZERO));
	}

	@Test
	public void GetNtpTimeStaticBefore2036Test() {
		TimeStamp ts = TimeStamp.getNtpTime(JAVA_TIME_1970);
		assertEquals(NTP_TIME_1970, ts.ntpValue());
	}

	@Test
	public void GetNtpTimeStaticAfter2036Test() {
		TimeStamp ts = TimeStamp.getNtpTime(JAVA_TIME_2040);
		assertEquals(NTP_TIME_2040, ts.ntpValue());
	}

	@Test
	public void GetNtpTimeStaticWithFractionTest() {
		// Use the Java time input that resulted in a specific fractional NTP time
		TimeStamp ts = TimeStamp.getNtpTime(JAVA_TIME_2002_FRAC_INPUT); // 986ms
		assertEquals(NTP_TIME_2002_FRAC_OUTPUT, ts.ntpValue()); // Expect NTP for 986ms input
	}


	@Test
	public void GetCurrentTimeReturnsValidTimeStampTest() {
		long javaTimeBefore = System.currentTimeMillis();
		TimeStamp ts = TimeStamp.getCurrentTime();
		long javaTimeAfter = System.currentTimeMillis();

		assertNotNull(ts);
		long tsJavaTime = ts.getTime();

		// Check if the timestamp is reasonably close to the system time
		assertTrue("Timestamp should be close to current time", tsJavaTime >= javaTimeBefore && tsJavaTime <= javaTimeAfter + 1); // Allow slight difference
	}

	// *** Parsing/Decoding Tests ***

	@Test
	public void DecodeNtpHexStringValidWithDotTest() {
		assertEquals(NTP_TIME_2002_FRAC, TimeStamp.decodeNtpHexString(NTP_HEX_2002_FRAC));
	}

	@Test
	public void DecodeNtpHexStringValidNoDotTest() {
		String hexNoDot = "c1a089bd";
		long expectedNtp = 0xc1a089bd00000000L;
		assertEquals(expectedNtp, TimeStamp.decodeNtpHexString(hexNoDot));
	}

	@Test
	public void DecodeNtpHexStringValidZeroTest() {
		assertEquals(0L, TimeStamp.decodeNtpHexString("0"));
	}

	@Test
	public void DecodeNtpHexStringValidZeroWithDotTest() {
		assertEquals(0L, TimeStamp.decodeNtpHexString("0.0"));
	}

	@Test
	public void DecodeNtpHexStringValidEmptyTest() {
		assertEquals(0L, TimeStamp.decodeNtpHexString(""));
	}

	@Test(expected = NumberFormatException.class)
	public void DecodeNtpHexStringNullTest() {
		TimeStamp.decodeNtpHexString(null);
	}

	@Test(expected = NumberFormatException.class)
	public void DecodeNtpHexStringInvalidHexTest() {
		TimeStamp.decodeNtpHexString("invalid.hex");
	}

	@Test(expected = NumberFormatException.class)
	public void DecodeNtpHexStringInvalidCharsTest() {
		TimeStamp.decodeNtpHexString("c1a0-89bd.fc904f6d");
	}

	@Test(expected = NumberFormatException.class)
	public void DecodeNtpHexStringEmptySecondsTest() {
		// Long.parseLong throws NFE for empty string
		TimeStamp.decodeNtpHexString(".fc904f6d");
	}

	@Test(expected = NumberFormatException.class)
	public void DecodeNtpHexStringEmptyFractionTest() {
		// Long.parseLong throws NFE for empty string
		TimeStamp.decodeNtpHexString("c1a089bd.");
	}

	@Test
	public void ParseNtpStringValidTest() {
		TimeStamp ts = TimeStamp.parseNtpString(NTP_HEX_2002_FRAC);
		assertEquals(NTP_TIME_2002_FRAC, ts.ntpValue());
	}

	@Test(expected = NumberFormatException.class)
	public void ParseNtpStringInvalidTest() {
		TimeStamp.parseNtpString("invalid-string");
	}

	@Test(expected = NumberFormatException.class)
	public void ParseNtpStringNullTest() {
		TimeStamp.parseNtpString(null);
	}

	// *** Protected Conversion Method Tests (toNtpTime) ***

	@Test
	public void ToNtpTimeBefore2036Test() {
		assertEquals(NTP_TIME_1970, TimeStamp.toNtpTime(JAVA_TIME_1970));
	}

	@Test
	public void ToNtpTimeAfter2036Test() {
		assertEquals(NTP_TIME_2040, TimeStamp.toNtpTime(JAVA_TIME_2040));
	}

	@Test
	public void ToNtpTimeFractionCalculationTest() {
		// Test case where input Java time has milliseconds
		// Use JAVA_TIME_2002_FRAC_INPUT (ends in 986ms)
		assertEquals(NTP_TIME_2002_FRAC_OUTPUT, TimeStamp.toNtpTime(JAVA_TIME_2002_FRAC_INPUT));

		// Test case with exactly 500ms
		long javaHalfSecond = JAVA_TIME_1970 + 500L;
		long expectedNtp = NTP_TIME_1970 | 0x80000000L; // 0.5 seconds fraction
		assertEquals(expectedNtp, TimeStamp.toNtpTime(javaHalfSecond));
	}

	// *** Object Method Tests (hashCode, equals) ***

	@Test
	public void HashCodeConsistentTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		int hc1 = ts.hashCode();
		int hc2 = ts.hashCode();
		assertEquals(hc1, hc2);
	}

	@Test
	public void HashCodeEqualForEqualObjectsTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
		TimeStamp ts2 = new TimeStamp(NTP_TIME_1970);
		assertEquals(ts1.hashCode(), ts2.hashCode());
	}

	@Test
	public void HashCodeDifferentForDifferentObjectsTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
		TimeStamp ts2 = new TimeStamp(NTP_TIME_2040);
		// Note: Hashcodes *can* collide, but unlikely for these specific values
		assertNotEquals(ts1.hashCode(), ts2.hashCode());
	}

	@Test
	public void EqualsSameObjectTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
		assertTrue(ts1.equals(ts1));
	}

	@Test
	public void EqualsEqualObjectTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_2002_FRAC);
		TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
		assertTrue(ts1.equals(ts2));
		assertTrue(ts2.equals(ts1));
	}

	@Test
	public void EqualsDifferentObjectTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
		TimeStamp ts2 = new TimeStamp(NTP_TIME_2040);
		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));
	}

	@Test
	public void EqualsNullTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
		assertFalse(ts1.equals(null));
	}

	@Test
	public void EqualsDifferentTypeTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970);
		assertFalse(ts1.equals(new Object()));
		assertFalse(ts1.equals(Long.valueOf(NTP_TIME_1970)));
	}

	// *** String Formatting Tests ***

	@Test
	public void ToStringReturnsCorrectFormatTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_2002_FRAC);
		assertEquals(NTP_HEX_2002_FRAC, ts.toString());

		TimeStamp tsZero = new TimeStamp(NTP_TIME_ZERO);
		assertEquals(NTP_HEX_ZERO, tsZero.toString());
	}

	@Test
	public void ToStringPaddingTest() {
		TimeStamp ts = new TimeStamp(NTP_TIME_PADDING);
		assertEquals(NTP_HEX_PADDING, ts.toString());
	}

	@Test
	public void ToStringStaticReturnsCorrectFormatTest() {
		assertEquals(NTP_HEX_2002_FRAC, TimeStamp.toString(NTP_TIME_2002_FRAC));
		assertEquals(NTP_HEX_ZERO, TimeStamp.toString(NTP_TIME_ZERO));
	}

	@Test
	public void ToStringStaticPaddingTest() {
		assertEquals(NTP_HEX_PADDING, TimeStamp.toString(NTP_TIME_PADDING));

		// Test case with seconds needing padding
		long ntpSecPadding = 0x0000000100000000L;
		assertEquals("00000001.00000000", TimeStamp.toString(ntpSecPadding));

		// Test case with fraction needing padding
		long ntpFracPadding = 0x8000000000000001L;
		assertEquals("80000000.00000001", TimeStamp.toString(ntpFracPadding));
	}

	@Test
	public void ToDateStringFormatTest() {
		// Use Locale.ROOT and a fixed TimeZone to make the test deterministic
		Locale originalDefaultLocale = Locale.getDefault();
		TimeZone originalDefaultZone = TimeZone.getDefault();
		try {
			Locale.setDefault(Locale.US); // Format uses Locale.US internally
			TimeZone.setDefault(TimeZone.getTimeZone("GMT")); // Make output predictable

			TimeStamp ts = new TimeStamp(NTP_TIME_1970); // Jan 1 1970 00:00:00.000 GMT
			// Expected format: "EEE, MMM dd yyyy HH:mm:ss.SSS"
			String expected = "Thu, Jan 01 1970 00:00:00.000";
			assertEquals(expected, ts.toDateString());

			// Test again to ensure formatter initialization is handled correctly
			assertEquals(expected, ts.toDateString());

			TimeStamp tsFrac = new TimeStamp(NTP_TIME_2002_FRAC); // Dec 10 2002 10:41:49.987 GMT
			String expectedFrac = "Tue, Dec 10 2002 10:41:49.987";
			assertEquals(expectedFrac, tsFrac.toDateString());

		} finally {
			Locale.setDefault(originalDefaultLocale);
			TimeZone.setDefault(originalDefaultZone);
		}
	}

	@Test
	public void ToUTCStringFormatTest() {
		// UTC format should be independent of default Locale/TimeZone
		TimeStamp ts = new TimeStamp(NTP_TIME_1970); // Jan 1 1970 00:00:00.000 GMT
		// Expected format: "EEE, MMM dd yyyy HH:mm:ss.SSS 'UTC'"
		String expected = "Thu, Jan 01 1970 00:00:00.000 UTC";
		assertEquals(expected, ts.toUTCString());

		// Test again to ensure formatter initialization is handled correctly
		assertEquals(expected, ts.toUTCString());

		TimeStamp tsFrac = new TimeStamp(NTP_TIME_2002_FRAC); // Dec 10 2002 10:41:49.987 GMT
		String expectedFrac = "Tue, Dec 10 2002 10:41:49.987 UTC";
		assertEquals(expectedFrac, tsFrac.toUTCString());
	}

	// *** Comparison Test ***

	@Test
	public void CompareToEqualTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_2002_FRAC);
		TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC);
		assertEquals(0, ts1.compareTo(ts2));
		assertEquals(0, ts2.compareTo(ts1));
	}

	@Test
	public void CompareToLessThanTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_1970); // Earlier time
		TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC); // Later time
		assertTrue("ts1 should be less than ts2", ts1.compareTo(ts2) < 0);
	}

	@Test
	public void CompareToGreaterThanTest() {
		TimeStamp ts1 = new TimeStamp(NTP_TIME_2040); // Later time
		TimeStamp ts2 = new TimeStamp(NTP_TIME_2002_FRAC); // Earlier time
		assertTrue("ts1 should be greater than ts2", ts1.compareTo(ts2) > 0);
	}

}
```