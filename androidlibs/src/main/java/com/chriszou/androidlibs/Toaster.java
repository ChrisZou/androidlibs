package com.chriszou.androidlibs;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * A helper class for showing toast
 */
public class Toaster {
    /**
     * Show a toast in short time, using Toast.LENGTH_SHORT
     * @param context
     * @param textResId
     */
	public static void s(final Activity context, final int textResId) {
        s(context, context.getString(textResId));
    }

    /**
     * Show a toast in LENGTH_SHORT;
     * @param context
     * @param msg
     */
	public static void s(final Activity context, final String msg) {
        toastOnUiThread(context, msg, Toast.LENGTH_SHORT);
	}

    /**
     * Show a toast in LENGTH_LONG;
     * @param context
     * @param stringResId
     */
    public static void l(Activity context, int stringResId) {
        l(context, context.getString(stringResId));
    }

    /**
     * Show a toast in LENGTH_LONG;
     * @param context
     * @param msg
     */
    public static void l(Activity context, String msg) {
        toastOnUiThread(context, msg, Toast.LENGTH_LONG);
    }

    /**
     * Make a toast on the UIThread of the activity.
     * @param activity
     * @param str
     * @param length
     */
    private static void toastOnUiThread(final Activity activity, final String str, int length) {
        final int len = length==Toast.LENGTH_SHORT ? length : Toast.LENGTH_LONG;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, str, len).show();
            }
        });
    }
}
