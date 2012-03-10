package org.adligo.i.util.client;

import java.util.Date;

/**
 * this class includes date formats which will be usable by 
 * jse SimpleDateFormat and gwt DateTimeFormat
 * 
 * it also has some methods for some simple date math
 * which would normally be done by a (Gregorian)Calendar in jse
 * but since that is NOT available on GWT
 * this provides a common date math for the java platforms (jse, jme, gwt client)
 * @author scott
 *
 */
public class CommonTime {
	public static final String THE_DAY_OF_THE_MONTH_MUST_BE_GREATER_THAN_ZERO = "The day of the month must be greater than zero.";
	public static final String MONTH_OVERFLOW_THREE = "the number of days in the month ";
	private static final String MONTH_OVERFLOW_TWO = " is greater than ";
	public static final String MONTH_OVERFLOW_ONE = "the day of the month ";
	public static final String DEFAULT_TIME_FORMAT = "h:mm a";
	public static final String DEFAULT_TIME_DETAIL_FORMAT = "h:mm:ss SSS a";
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yy";
	public static final String DEFAULT_DATE_TIME_FORMAT = "MM/dd/yy h:mm a SSS";
	public static final String DEFAULT_DATE_TIME_TIMEZONE_FORMAT = "MM/dd/yy h:mm a SSS Z";
	
	public static final int ONE_MINUTE = 60 * 1000;
	public static final int THREE_MINUTES = 3 * 60 * 1000;
	public static final int FIVE_MINUTES = 5 * 60 * 1000;
	public static final int TEN_MINUTES = 10 * 60 * 1000;
	public static final int ONE_HOUR = 60 * 60 * 1000;
	public static final int ONE_DAY = 24 * 60 * 60 * 1000;
	
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
	
	private CommonTime() {}
	
	public static String formatDateTime(Date date) {
		if (date == null) {
			return "null";
		}
		return formatDateTime(date.getTime());
	}
	
	public static String formatDate(Date date) {
		if (date == null) {
			return "null";
		}
		return formatDate(date.getTime());
	}
	
	public static String formatDateTime(long date) {
		I_TextFormatter formatter = TextFormatter.getInstance();
		return formatter.formatDate(DEFAULT_DATE_TIME_FORMAT, date);
	}
	
	public static String formatDate(long date) {
		I_TextFormatter formatter = TextFormatter.getInstance();
		return formatter.formatDate(DEFAULT_DATE_FORMAT, date);
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
		throw new IllegalArgumentException("The month passed in to getDaysInMont is not between 1 and 12?");
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
	
	public static int getDayOfYear( int year, int month, int dayOfMonth) {
		return getDayOfYear(year, (short) month, (short) dayOfMonth);
	}
	
	public static int getDayOfYear(int year, short month, short dayOfMonth) {
		if (dayOfMonth <= 0) {
			throw new IllegalArgumentException(THE_DAY_OF_THE_MONTH_MUST_BE_GREATER_THAN_ZERO);
		}
		int total = 0;
		for (short i = 1; i < month; i++) {
			total = total + getDaysInMonth(i, year);
		}
		short daysInMonth = getDaysInMonth(month, year);
		if (dayOfMonth > daysInMonth) {
			throw new IllegalArgumentException(MONTH_OVERFLOW_ONE + dayOfMonth + 
					MONTH_OVERFLOW_TWO +
					MONTH_OVERFLOW_THREE + daysInMonth);
		}
		total = total + dayOfMonth;
		return total;
	}
}
