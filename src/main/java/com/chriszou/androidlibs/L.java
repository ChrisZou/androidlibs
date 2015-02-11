package com.chriszou.androidlibs;

import android.util.Log;

import java.util.MissingFormatArgumentException;
import java.util.Objects;


/**
 * A helper method for logging to logcat
 */
public class L {
    public static boolean debug = true;

    public static String TAG = "zyzy";
    private static final String CLASS_METHOD_LINE_FORMAT = "%s.%s() Line:%d---------%s";

    /**
     * Log using android.util.Log.d
     *
     * @param msg
     */
    public static void l(Objects msg) {
        if (debug) {
            String logText = getLogText(msg);
            Log.d(TAG, logText);
        }
    }

    /**
     * Log an error message using android.util.Log.e
     *
     * @param msg
     */
    public static void e(String msg) {
        if (debug) {
            String logText = getLogText(msg);
            Log.e(TAG, logText);
        }
    }

    /**
     * Trace the call stack of current method
     */
    public static void trace() {
        Log.d(TAG, "trace-------------------------------------------------------------------------");

        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        int len = traceElements.length;
        for (int i = len - 1; i >= 0; i--) {
            StackTraceElement traceElement = traceElements[i];
            String logText = formatLogText(traceElement, i);
            Log.d(TAG, logText);
        }

        Log.d(TAG, "end trace-------------------------------------------------------------------------");
    }

    /**
     * Throws an exception containing the detailed message. Should be used ONLY IN DEVELOPMENT AND TESTING.
     */
    public static void error(String msg) {
        if (debug) {
            throw new RuntimeException(msg);
        }
    }

    /**
     *
     */
    public static void l(String format, Object... obj) {
        String logText;
        if (obj == null || obj.length == 0) {
            logText = format;
        } else {
            String msg = String.format(format, obj);
            logText = getLogText(msg);
        }
        Log.d(TAG, logText);
    }

    private static String getLogText(Object obj) {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[4];
        return formatLogText(traceElement, obj);
    }

    private static String formatLogText(StackTraceElement traceElement, Object obj) {
        String className = traceElement.getClassName();
        String simpleClassName = className.contains(".") ? className.substring(className.lastIndexOf(".") + 1) : className;
        String methodName = traceElement.getMethodName();
        int lineNumber = traceElement.getLineNumber();

        String logText = String.format(CLASS_METHOD_LINE_FORMAT, simpleClassName, methodName, lineNumber, obj.toString());
        return logText;
    }
}
