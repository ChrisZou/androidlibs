/**
 * AlarmRunner.java
 *
 * Created by zouyong on Aug 19, 2014,2014
 */
package com.chriszou.androidlibs;

import android.content.Context;
import android.content.Intent;

/**
 * When setting an alarm using AlarmHelper, this is the class that get called when the alarm goes off
 * @author zouyong
 *
 */
public interface AlarmRunner {
    public void run(Context context, Intent intent);
}
