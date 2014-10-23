/**
 * TimeHelper.java
 *
 * Created by zouyong on Sep 29, 2014,2014
 */
package com.chriszou.androidlibs;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Helper class for dealing with date&time
 * @author zouyong
 *
 */
public class TimeHelper {
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
}
