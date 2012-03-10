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
	private long timestamp;

	public DateTime(long p) {
		timestamp = p;
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
			total = total + CommonTime.getDaysInMonth(i, year);
		}
		total = total + dayOfMonth;
		return total;
	}
	
	/**
	 * maps to a CommonTime.MONDAY exc
	 * @return
	 */
	public short getDayOfWeek() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String dowString = formatter.formatDate("EEE", timestamp);
		if ("Mon".equals(dowString)) {
			return CommonTime.MONDAY;
		} else if ("Tue".equals(dowString)) {
			return CommonTime.TUESDAY;
		} else if ("Wed".equals(dowString)) {
			return CommonTime.WENSDAY;
		} else if ("Thu".equals(dowString)) {
			return CommonTime.THURSDAY;
		} else if ("Fri".equals(dowString)) {
			return CommonTime.FRIDAY;
		} else if ("Sat".equals(dowString)) {
			return CommonTime.SATURDAY;
		} else {
			return CommonTime.SUNDAY;
		}
		
	}
	public String toString() {
		I_TextFormatter formatter = TextFormatter.getInstance();
		String toRet = formatter.formatDate(CommonTime.FULL_YEAR_TIME_FORMAT, timestamp);	
		return toRet;
	}
}
