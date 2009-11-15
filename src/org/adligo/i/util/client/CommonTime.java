package org.adligo.i.util.client;

import java.util.Date;


public class CommonTime {
	public static final String DEFAULT_TIME_FORMAT = "h:mm a";
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yy";
	public static final String DEFAULT_DATE_TIME_FORMAT = "MM/dd/yy h:mm a SSS";
	public static final String DEFAULT_DATE_TIME_TIMEZONE_FORMAT = "MM/dd/yy h:mm a SSS Z";
	
	public static final int ONE_MINUTE = 60 * 1000;
	public static final int THREE_MINUTES = 3 * 60 * 1000;
	public static final int FIVE_MINUTES = 5 * 60 * 1000;
	public static final int TEN_MINUTES = 10 * 60 * 1000;
	public static final int ONE_HOUR = 60 * 60 * 1000;
	public static final int ONE_DAY = 24 * 60 * 60 * 1000;
	
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
}
