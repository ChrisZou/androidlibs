package com.chriszou.androidlibs;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chris on 1/27/15.
 */
public class GsonUtils {
    private static final Gson sGson = new Gson();

    public static <T> T fromJson(JSONObject object, Class<T> clazz) {
        return sGson.fromJson(object.toString(), clazz);
    }

    public static String toJson(Object obj) {
        return sGson.toJson(obj);
    }

    public static <T> T fromJson(String userStr, Class<T> clazz) throws JSONException {
        if(userStr==null) return null;
        return fromJson(new JSONObject(userStr), clazz);
    }
}
