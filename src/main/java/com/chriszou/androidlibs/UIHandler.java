package com.chriszou.androidlibs;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Chris on 12/2/14.
 */
public class UIHandler {
    private static final Handler sUiHandler = new Handler(Looper.getMainLooper());
    public static void postDelayed(long millis, Runnable run) {
        sUiHandler.postDelayed(run, millis);
    }

    public static void post(Runnable r) {
        sUiHandler.post(r);
    }
}
