package com.chriszou.androidlibs;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

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


    public static int dpToPixel(Context context, float dip) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int length = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm);
        return length;
    }

}
