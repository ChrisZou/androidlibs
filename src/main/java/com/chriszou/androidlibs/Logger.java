/**
 * Logger.java
 *
 * Created by zouyong on Sep 17, 2014,2014
 */
package com.chriszou.androidlibs;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

/**
 * Log app running statistics to file
 * @author zouyong
 *
 */
public class Logger {

	private static final String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath()+"/my_app_logs/";
    static {
        File f = new File(LOG_DIR);
        if(!f.exists()) {
            f.mkdirs();
        }
    }

	private static String getLogFilePath(Context context) {
		String appName = context.getPackageName().replace(" ", "_");
		return (LOG_DIR+"/"+appName+".log");
	}

	public static void log(Context context, String msg) {
        try {
            msg = "\n"+ currentTime()+": "+msg+"\n";
            FileUtils.append(getLogFilePath(context), msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static String currentTime() {
		return CalendarUtil.getDateTimeString();
	}
}
