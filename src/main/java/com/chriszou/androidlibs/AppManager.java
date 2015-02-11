/**
 * AppManager.java
 *
 * Created by zouyong on 6:56:55 AM, 2014
 */
package com.chriszou.androidlibs;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

/**
 * Helper class for retrieving application information from device
 * @author zouyong
 *
 */
public class AppManager{
    private Context mContext;
    private PackageManager mPackageManager;
    private AppManager(Context context){
        mContext = context;
        mPackageManager = mContext.getPackageManager();
    }

    public static AppManager getInstance(Context context) {
        return new AppManager(context);
    }
    /**
     * Get all the apps installed on this device
     * @param context
     * @return
     */
    public List<AppInfo> getAllApps(Context context) {

        //get a list of installed apps.
        List<ApplicationInfo> packages = mPackageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        List<AppInfo> results = new ArrayList<AppInfo>();
        for (ApplicationInfo applicationInfo : packages) {
            Intent startIntent = mPackageManager.getLaunchIntentForPackage(applicationInfo.packageName);
            if(startIntent==null) {
                continue;
            }

            AppInfo appInfo = new AppInfo(applicationInfo.packageName, context);
            results.add(appInfo);
        }

        return results;
    }

    /**
     * Get the app icon for the given package name
     * @param packageName
     * @return
     */
    public Drawable getAppIcon(String packageName){
        try {
            return mPackageManager.getApplicationIcon(packageName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the app name from given package name
     * @param pkgName
     * @return
     */
    public String getAppNameFromPkg(String pkgName){
        try {
            ApplicationInfo appInfo = mPackageManager.getApplicationInfo(pkgName, 0);
            return appInfo.loadLabel(mPackageManager).toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Get the start intent for the given package name
     * @param pkg
     * @return
     */
    public Intent getStartIntent(String pkg) {
        return mPackageManager.getLaunchIntentForPackage(pkg);
    }

}
