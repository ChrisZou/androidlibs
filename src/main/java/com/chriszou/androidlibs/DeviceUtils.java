package com.chriszou.androidlibs;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
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

    public static String OSVersion() {
        return VERSION.RELEASE==null ? "HOW COULD THIS BE NULL!!! YOU FOOL!!!" : VERSION.RELEASE;
    }

    public static String deviceId(Context context) {
        String deviceId = null;
        try {
            deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception e) { }

        try {
            if (TextUtils.isEmpty(deviceId) || deviceId.startsWith("000000")) { deviceId = getPseudoDeviceId(); }
            if (TextUtils.isEmpty(deviceId)) { deviceId = android.os.Build.SERIAL; }
            if (TextUtils.isEmpty(deviceId)) { deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID); }
            return deviceId==null ? "WFT" : deviceId;
        } catch (Exception e) { }
        return "WTF";
    }


    private static String PSUDO_DEVICE_DI = null;
    private static String getPseudoDeviceId() {
        if (PSUDO_DEVICE_DI == null) {
            try {
                PSUDO_DEVICE_DI = "35" + //we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                    Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
            } catch (Exception e) {}
        }
        return PSUDO_DEVICE_DI;
    }
}
