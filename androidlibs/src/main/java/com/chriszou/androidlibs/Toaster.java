package com.chriszou.androidlibs;

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
	public static void s(Context context, int textResId) {
		Toast.makeText(context, textResId, Toast.LENGTH_SHORT).show();
	}

    /**
     * Show a toast in LENGTH_SHORT;
     * @param context
     * @param msg
     */
	public static void s(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

    /**
     * Show a toast in LENGTH_LONG;
     * @param context
     * @param stringResId
     */
    public static void l(Context context, int stringResId) {
        Toast.makeText(context, stringResId, Toast.LENGTH_LONG).show();
    }

    /**
     * Show a toast in LENGTH_LONG;
     * @param context
     * @param msg
     */
    public static void l(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
