/**
 * Prefs.java
 * 
 * Created by zouyong on Jun 4, 2014,2014
 */
package com.chriszou.androidlibs;

import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

/**
 * @author zouyong
 * Default Preferences helper class.
 * To use it, call its init() in customized {@link Application} class's onCreate() method
 */
public class Prefs {
    private static final String PREF_BOOL_FIRST_INSTALL = "pref_bool_first_install";

    private static SharedPreferences sPreferences;
    
    /**
     * Call this in customized {@link Application} class's onCreate() method before any usage of this class
     * @param context
     */
    static void init(Context context) {
		sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	}
    
    public static int getInt(String key, int defValue) {
    	return sPreferences.getInt(key, defValue);
    }
    
    public static void putInt(String key, int value) {
    	sPreferences.edit().putInt(key, value).commit();
    }
    
    public static boolean getBoolean(String key, boolean defValue)  {
    	return sPreferences.getBoolean(key, defValue);
    }
    
    public static void putBoolean(String key, boolean value) {
    	sPreferences.edit().putBoolean(key, value).commit();
    }
    
    public static String getString(String key, String defValue){
        return sPreferences.getString(key, defValue);
    }

    public static String getString(String key){
        return getString(key, null);
    }

    public static void putString(String key, String value){
    	sPreferences.edit().putString(key, value).commit();
    }
    
    public static Set<String> getStringSet(String key, Set<String>defValues ) {
    	return sPreferences.getStringSet(key, defValues);
    }
    
    public static void putStringSet(String key, Set<String>values ) {
    	sPreferences.edit().putStringSet(key, values).commit();
    }

    public static void remove(String key) {
        sPreferences.edit().remove(key).commit();
    }

    public static void firstTimeInstallInit(Runnable run) {
        boolean firstInstall = Prefs.getBoolean(PREF_BOOL_FIRST_INSTALL, true);
        if (firstInstall) {
            run.run();

            Prefs.putBoolean(PREF_BOOL_FIRST_INSTALL, false);
        }
    }

}
