/**
 * TimeHelper.java
 *
 * Created by zouyong on Sep 29, 2014,2014
 */
package com.chriszou.androidlibs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A Helper class for dealing with date&time
 * @author zouyong
 *
 */
public class TimeHelper {
    public static final long MINUTE_DURATION_MILLIS = 60 * 1000;
    public static final long HOUR_DURATION_MILLIS = 60 * MINUTE_DURATION_MILLIS;
    public static final long DAY_DURATION_MILLIS = 24 * HOUR_DURATION_MILLIS;
    public static final long WEEK_DURATION_MILLIS = 7 * DAY_DURATION_MILLIS;

    /**
	 * Get the date String in the format 'yyyy-MM-dd' for today
	 * @return
	 */
	public static String getTodayString() {
		return getTimeFormat("yyyy-MM-dd", System.currentTimeMillis());
	}

    /**
     * Format the time and return a String in the given format
     * @param format
     * @param time millisecond in UTC
     * @return
     */
	public static String getTimeFormat(String format, long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(time));
	}

    public static long getNextHourAndMinite(int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        long millis = c.getTimeInMillis();
        while(millis<System.currentTimeMillis()) {
            millis += DAY_DURATION_MILLIS;
        }
        return millis;
    }
}
