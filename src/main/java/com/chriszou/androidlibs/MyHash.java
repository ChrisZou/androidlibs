package com.chriszou.androidlibs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Chris on 4/7/15.
 */
public class MyHash extends JSONObject implements Serializable{
    public MyHash() {}
    public MyHash(String jsonString) throws JSONException {
        super(jsonString);
    }

    public static MyHash toHash(Object obj) {
        MyHash hash;

        try {
            hash = new MyHash(GsonUtils.toJson(obj));
        } catch (JSONException e) {
            e.printStackTrace();
            hash = new MyHash();
        }
        return hash;
    }
}
