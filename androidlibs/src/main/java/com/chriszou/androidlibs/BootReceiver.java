package com.chriszou.androidlibs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zouyong on 10/28/14.
 */
public class BootReceiver extends BroadcastReceiver {

    /*
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

    <receiver android:name="com.chriszou.androidlibs.BootReceiver">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED"/>
        </intent-filter>
    </receiver>
    */

    @Override
    public void onReceive(Context context, Intent intent) {
        if (sOnBootComplatedListener != null) {
            sOnBootComplatedListener.onBootCompleted(context, intent);
        }
    }

    private static OnBootCompletedListener sOnBootComplatedListener;
    public static void setOnBootCompletedListener(OnBootCompletedListener listener) {
        sOnBootComplatedListener = listener;
    }

    public static interface OnBootCompletedListener {
        public void onBootCompleted(Context context, Intent intent);
    }
}
