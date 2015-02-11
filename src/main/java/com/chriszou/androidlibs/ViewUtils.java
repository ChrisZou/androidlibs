package com.chriszou.androidlibs;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

    public static void hideView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    public static void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * Set the imageView with the given image resource id, or hide it if the resource id is 0
     * @param imageView
     * @param imageRes
     */
    public static void setImageOrHide(ImageView imageView, int imageRes) {
        if(imageRes!=0) {
            imageView.setImageResource(imageRes);
            imageView.setVisibility(View.VISIBLE);
        } else {
            hideView(imageView);
        }
    }

    /**
     * Set the textView with the given text, or hide it if the given text if null
     * @param textView
     * @param text
     */
    public static void setTextOrHide(TextView textView, String text) {
        if (textView == null) {
            return;
        }

        if(text!=null) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        } else {
            ViewUtils.hideView(textView);
        }
    }

    /**
     * Check if the edittext input is empty, NOTE: All spaces will
     * @param editText
     */
    public static boolean inputNotEmpty(EditText... editText) {
        if(editText==null || editText.length ==0) {
            return false;
        }

        for(EditText edit : editText) {
            String input = edit.getText().toString().trim();
            if(input.length()==0) {
                return false;
            }
        }

        return true;
    }

    public static String getTextTrimed(EditText editText) {
        return editText.getText().toString().trim();
    }


    public static void hideView(View view, boolean hide) {
        if (hide) {
            hideView(view);
        } else {
            showView(view);
        }
    }

    public static void showView(View view, boolean show) {
        hideView(view, !show);
    }
}
