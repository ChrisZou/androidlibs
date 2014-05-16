/**
 * CalendarUtil.java
 * 
 * Created by zouyong on 11:45:50 AM, 2014
 */
package com.chriszou.androidlibs;

import java.util.Calendar;

/**
 * A utility of the java.util.Calendar class
 * @author zouyong
 *
 */
public class CalendarUtil {
    /**
     * Get the current hour in 24-hour format
     * @return
     */
    public static int getCurrentHour() {
        return getHour24(System.currentTimeMillis());
    }
    
    /**
     * Get the hour field of a given time in millisecond
     * @param time
     * @return
     */
    public static int getHour24(long time) {
    	Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * Get the current minute in an hour
     * @return
     */
    public static int getCurrentMinute() {
    	return getMinute(System.currentTimeMillis());
    }
    
    /**
     * Get the minute field of a given time millisecond
     * @param time
     * @return
     */
    public static int getMinute(long time) {
    	Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.MINUTE);
    }
}
