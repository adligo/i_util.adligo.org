package org.adligo.i.util.client;


/**
 * a immutable date time
 * which provides much of the functionality 
 * of the Gegorian Calander in jse, 
 * but which is also availabe on other platforms
 * (GWT)
 * JME doesn't have a date parser, so if you really need business logic on 
 * a cell phone I would suggest using GWT
 * 
 * JME is still a good choice for video games on phones.
 * 
 * @author scott
 *
 */
public class DateTime {
	public static final String THE_MONTH_PASSED_IN_TO_GET_DAYS_IN_MONT_IS_NOT_BETWEEN_1_AND_12 = "The month passed in to getDaysInMont is not between 1 and 12?";
	public static final String DEFAULT_TIME_FORMAT = "h:mm a";
	public static final String DEFAULT_TIME_DETAIL_FORMAT = "h:mm:ss SSS a";
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	public static final String FULL_YEAR_DATE_FORMAT = "MM/dd/yyyy";
	public static final String DEFAULT_DATE_TIME_FORMAT = "MM/dd/yyyy hh:mm a SSS";
	public static final String FULL_YEAR_TIME_FORMAT = "MM/dd/yyyy hh:mm a SSS";
	public static final String DEFAULT_DATE_TIME_TIMEZONE_FORMAT = "MM/dd/yyyy hh:mm a SSS Z";
	
	public static final int ONE_MINUTE = 60 * 1000;
	public static final int THREE_MINUTES = 3 * 60 * 1000;
	public static final int FIVE_MINUTES = 5 * 60 * 1000;
	public static final int TEN_MINUTES = 10 * 60 * 1000;
	public static final int ONE_HOUR = 60 * 60 * 1000;
	public static final long ONE_DAY = 24 * 60 * 60 * 1000;
	
	public static final short JANUARY = 1;
	public static final short FEBUARY = 2;
	public static final short MARCH = 3;
	public static final short APRIL = 4;
	public static final short MAY = 5;
	public static final short JUNE = 6;
	public static final short JULY = 7;
	public static final short AUGUST = 8;
	public static final short SEPTEMBER = 9;
	public static final short OCTOBER = 10;
	public static final short NOVEMBER = 11;
	public static final short DECEMBER = 12;
	
	public static final short MONDAY = 1;
	public static final short TUESDAY = 2;
	public static final short WENSDAY = 3;
	public static final short THURSDAY = 4;
	public static final short FRIDAY = 5;
	public static final short SATURDAY = 6;
	public static final short SUNDAY = 7;
	
	
	private long timestamp;

	public DateTime(Long p) {
		if (p == null) {
			throw new NullPointerException("DateTime requires non null constructor parameter.");
		}
		timestamp = p.longValue();
	}
	
	public DateTime(long p) {
		timestamp = p;
	}
	
	public DateTime(String value) throws IllegalArgumentException {
		I_DateTimeParser parser = DateTimeParserFactory.create();
		timestamp = parser.parse(DEFAULT_DATE_TIME_FORMAT, value);
	}
	
	public int getYear() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String yearString = formatter.formatDate("yyyy", timestamp);
		int year = Integer.parseInt(yearString);
		return year;
	}
	
	public short getMonth() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String monthString = formatter.formatDate("MM", timestamp);
		short month = Short.parseShort(monthString);
		return month;
	}
	
	public short getDayOfMonth() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String dayOfMonthString = formatter.formatDate("dd", timestamp);
		short dayOfMonth = Short.parseShort(dayOfMonthString);
		return dayOfMonth;
	}
	
	public short getHourOfDay() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String hourOfDayString = formatter.formatDate("hh", timestamp);
		short hourOfDay = Short.parseShort(hourOfDayString);
		return hourOfDay;
	}
	
	public short getMinuteOfHour() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String minuteOfHourString = formatter.formatDate("mm", timestamp);
		short minuteOfHour = Short.parseShort(minuteOfHourString);
		return minuteOfHour;
	}
	
	public short getSecondOfMinute() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String secondOfMinuteString = formatter.formatDate("ss", timestamp);
		short secondOfMinute = Short.parseShort(secondOfMinuteString);
		return secondOfMinute;
	}

	public short getMillisecondOfSecond() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String millisecondOfSecondString = formatter.formatDate("SSS", timestamp);
		short millisecondOfSecond = Short.parseShort(millisecondOfSecondString);
		return millisecondOfSecond;
	}
	
	public int getDayOfYear() {
		int year = getYear();
		short month = getMonth();
		short dayOfMonth = getDayOfMonth();
		int total = 0;
		for (short i = 1; i < month; i++) {
			total = total + getDaysInMonth(i, year);
		}
		total = total + dayOfMonth;
		return total;
	}
	
	/**
	 * maps to a MONDAY exc
	 * @return
	 */
	public short getDayOfWeek() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String dowString = formatter.formatDate("EEE", timestamp);
		if ("Mon".equals(dowString)) {
			return MONDAY;
		} else if ("Tue".equals(dowString)) {
			return TUESDAY;
		} else if ("Wed".equals(dowString)) {
			return WENSDAY;
		} else if ("Thu".equals(dowString)) {
			return THURSDAY;
		} else if ("Fri".equals(dowString)) {
			return FRIDAY;
		} else if ("Sat".equals(dowString)) {
			return SATURDAY;
		} else {
			return SUNDAY;
		}
		
	}
	public String toString() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String toRet = formatter.formatDate(FULL_YEAR_TIME_FORMAT, timestamp);
		return toStringFix(toRet);
	}

	/**
	 * note this appears to be a bug introduced in GWT 2.4.0 which differs from
	 * the doc, and CLDC 1.1 doesn't have replaceAll grr
	 * @param toRet
	 * @return
	 */
	protected String toStringFix(String toRet) {
		//note this appears to be a bug introduced in GWT 2.4.0 which differs from
		//the doc, and CLDC 1.1 doesn't have replaceAll grr
		int indexStart = toRet.indexOf("ap.m.");
		if (indexStart == -1) {
			indexStart = toRet.indexOf("aa.m.");
			if (indexStart != -1) {
				toRet = toRet.substring(0, indexStart) + "AM"
					+ toRet.substring(indexStart + 5, toRet.length());
			}
		} else {
			toRet = toRet.substring(0, indexStart) + "PM"
			+ toRet.substring(indexStart + 5, toRet.length());
		}
		return toRet;
	}
	
	public static String formatDateTime(long date) {
		return formatDate(DEFAULT_DATE_TIME_FORMAT, date);
	}
	
	public static String formatDate(long date) {
		return formatDate(DEFAULT_DATE_FORMAT, date);
	}
	
	public static String formatDate(String format, long date) {
		I_TextFormatter formatter = TextFormatter.getInstance();
		return formatter.formatDate(format, date);
	}
	
	public static short getDaysInMonth(short month, int year) {
		switch (month) {
			case JANUARY:
				return 31;
			case FEBUARY:
				if (isLeapYear(year)) {
					return 29;
				}
				return 28;
			case MARCH:
				return 31;
			case APRIL:
				return 30;
			case MAY:
				return 31;
			case JUNE:
				return 30;
			case JULY:
				return 31;
			case AUGUST:
				return 31;
			case SEPTEMBER:
				return 30;
			case OCTOBER:
				return 31;
			case NOVEMBER:
				return 30;
			case DECEMBER:
				return 31;
		}
		throw new IllegalArgumentException(
				THE_MONTH_PASSED_IN_TO_GET_DAYS_IN_MONT_IS_NOT_BETWEEN_1_AND_12);
	}
	
	public static boolean isLeapYear(int year) {
		double diff = 0;
		if (year == 2000) {
			return true;
		} else if (year > 2000) {
			diff = year - 2000;
		} else if (year < 2000 && year >= 0) {
			diff = 2000 - year;
		} else {
			//year less than zero
			int neg = year * -1;
			diff = neg + 2000;
		}
		double result = diff / 4;
		String asString = Double.toString(result);
		int indexOfZero = asString.indexOf(".");
		if (indexOfZero != -1) {
			String remainder = asString.substring(
					indexOfZero + 1, asString.length());
			int rem = Integer.parseInt(remainder);
			if (rem > 0) {
				return false;
			}
		}
		return true;
	}
	
	public static int getDaysInYear(int year) {
		int toRet = 337 + getDaysInMonth(FEBUARY, year);
		return toRet;
	}
	
	public long getTime() {
		return timestamp;
	}
	
	public Long getTimeLong() {
		return new Long(timestamp);
	}
	
	public long getDayStart() {
		short month = getMonth();
		short dom = getDayOfMonth();
		int year = getYear();
		return new DateTime("" + month + "/" + dom + "/" + year + " 12:00 AM 000").getTime();
	}
	
	public long getDayEnd() {
		DateTime dt = new DateTime(new Long(getTime() + ONE_DAY));
		short month = dt.getMonth();
		short dom = dt.getDayOfMonth();
		int year = dt.getYear();
		return new DateTime("" + month + "/" + dom + "/" + year + " 12:00 AM 000").getTime() - 1L;
	}
}
