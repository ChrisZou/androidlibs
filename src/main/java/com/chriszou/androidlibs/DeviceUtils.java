package com.chriszou.androidlibs;

import android.content.Context;

/**
 * Created by zouyong on 10/31/14.
 */
public class DeviceUtils {
    /**
     * Get screen width in pixels
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * Get screen height in pixels
     */
    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
