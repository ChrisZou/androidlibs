package com.chriszou.androidlibs;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ResUtils {

	public static Resources getResources() {
		return UtilApplication.getContext().getResources();
	}

	public static int getColor(int colorResId) {
		return getResources().getColor(colorResId);
	}

	public static String getString(int stringResId) {
		return getResources().getString(stringResId);
	}

    public static int getDimensionPixelOffset(int dimenResId) {
        return getResources().getDimensionPixelOffset(dimenResId);
    }

    public static Drawable getDrawable(int drawableResId) {
        Drawable drawable = getResources().getDrawable(drawableResId);
        drawable.setBounds(new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()));
        return drawable;
    }

    public static String getResourceId(View v) {
        if (v == null || v.getId()==View.NO_ID) return null;

        return getResources().getResourceEntryName(v.getId());
    }

    public static ColorStateList getColorStateList(int colorRes) {
        return getResources().getColorStateList(colorRes);
    }
}
