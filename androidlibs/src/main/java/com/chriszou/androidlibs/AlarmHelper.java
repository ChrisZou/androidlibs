/**
 * AlarmHelper.java
 *
 * Created by zouyong on Aug 19, 2014,2014
 */
package com.chriszou.androidlibs;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * A helper class to set/remove alarm
 * @author zouyong
 *
 */
public class AlarmHelper {

	private Context mContext;
	public static final String EXTRA_EXTRA = "extra_extra";

	public AlarmHelper(Context context){
		this.mContext = context;
	}

    /**
     * Set up an alarm
     * @param id the alarm id
     * @param time the time in millisecond at when this alarm should go off
     * @param runnerClassName name of a subclass of AlarmRunner, this will get called when the alarm goes off.
     * @param extras extra data that will be set to the intent delivered to the AlarmRunner when the alarm goes off.
     */
	public void setAlarm(int id, long time, String runnerClassName, Bundle extras) {
		Intent intent = new Intent(mContext, AlarmReceiver.class);
		intent.putExtra(EXTRA_EXTRA, extras);
		intent.putExtra(AlarmReceiver.EXTRA_STRING_RUNNER, runnerClassName);
		PendingIntent pi = PendingIntent.getBroadcast(mContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager aManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
		aManager.set(AlarmManager.RTC, time, pi);

		Logger.log(mContext, "Set alarm on: "+CalendarUtil.getDateTimeString(time));
		L.l("set alarm on: "+CalendarUtil.getDateTimeString(time));
	}
}
