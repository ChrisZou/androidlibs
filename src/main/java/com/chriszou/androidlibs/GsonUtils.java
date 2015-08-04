package com.chriszou.androidlibs;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Chris on 1/27/15.
 */
public class GsonUtils {
    private static Gson sGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public static void setGson(Gson gson) {
        sGson = gson;
    }

    public static <T> T fromJson(JSONObject object, Class<T> clazz) {
        return sGson.fromJson(object.toString(), clazz);
    }

    public static String toJson(Object obj) {
        return sGson.toJson(obj);
    }

    public static JSONObject toJSONObject(Object obj) throws JSONException {
        return new JSONObject(toJson(obj));
    }


    public static <T> T fromJson(String userStr, Class<T> clazz) throws JSONException {
        if(userStr==null) return null;
        return fromJson(new JSONObject(userStr), clazz);
    }

    public static List fromJson(String cache, Type type) {
        return sGson.fromJson(cache, type);
    }
}
