package com.chriszou.androidlibs;

import android.view.View;

/**
 * Created by zouyong on 10/31/14.
 */
public class ViewUtils {
    public static String getModeName(int mode) {
        switch (mode) {
            case View.MeasureSpec.UNSPECIFIED: return "Unspecified";
            case View.MeasureSpec.EXACTLY: return "exactly";
            case View.MeasureSpec.AT_MOST: return "at most";
        }
        return "Unknown";
    }
}
