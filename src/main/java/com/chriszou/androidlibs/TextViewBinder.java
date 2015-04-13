package com.chriszou.androidlibs;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Chris on 3/19/15.
 */
public class TextViewBinder {
    public static final String INTERPOLATION_START = "#{";
    public static final String INTERPOLATION_END = "}";

    public static void bindViewsUsingTag(Hashable hash, View view) {
        if (hash==null || view==null) return;
        if (view instanceof TextView) {bindText(hash, view); return;}
        if (view instanceof ImageView) { loadImageView(hash, view); return;}

        if (! (view instanceof ViewGroup)) return;

        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            bindViewsUsingTag(hash, viewGroup.getChildAt(i));
        }
    }

    private static void loadImageView(Hashable hash, View view) {
        if (hash==null || view==null || !(view instanceof ImageView) || view.getTag()==null || !(view.getTag() instanceof String)) return;

        String tag = (String) view.getTag();
        String url = hash.get(tag);
        if (url==null) return;

        Picasso.with(view.getContext()).load(url).into((ImageView) view);
    }

    private static void bindText(Hashable hash, View view) {
        if (hash==null || view==null || !(view instanceof TextView)) return;

        Object tagObj = view.getTag();
        if (tagObj==null || !(tagObj instanceof String)) return;

        final String tag = (String) tagObj;
        final String key = getKey(tag);
        String value = hash.get(key);
        String text = hasInterpolation(tag) ? getFormatedText(tag, key, value) : value;
        if (text==null) return;
        ((TextView)view).setText(text);
    }

    private static String getFormatedText(String tag, String key, String value) {
        if (tag==null || value ==null || key==null) return null;
        return tag.replace(INTERPOLATION_START+key+INTERPOLATION_END, value);
    }

    //Get the string inclosed by "#{" and "}" as the key, if no #{ and } pair found，then return the string itself
    //For example: pass "#{hello}， world" should return hello, pass "hello, world" should return "hello, world"
    private static String getKey(String tag) {
        if (tag==null) return "N/A";
        if(hasInterpolation(tag)) { return tag.substring(tag.indexOf(INTERPOLATION_START)+2, tag.indexOf(INTERPOLATION_END, tag.indexOf(INTERPOLATION_START))); }
        return tag;
    }

    public static boolean hasInterpolation(String tag) {
        if (tag==null) return false;
        return tag.contains(INTERPOLATION_START) && tag.indexOf(INTERPOLATION_END, tag.indexOf(INTERPOLATION_START)) > 0;
    }
}
