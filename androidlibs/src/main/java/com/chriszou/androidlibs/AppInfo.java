/**
 * AppInfo.java
 *
 * Created by zouyong on 7:08:27 PM, 2014
 */
package com.chriszou.androidlibs;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Incapsulate an application's info
 * @author zouyong
 *
 */
public class AppInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * package name
     */
    public String pkgName;

    /**
     * Name of the application, as shown in a launcher application or home screen
     */
    public String name;

    /**
     * The icon of the app, shown in a launcher application or home screen
     */
    public Drawable icon;

    public AppInfo() {}

    public AppInfo(String pkg, Context context) {
        this.pkgName = pkg;
        AppManager appManager = AppManager.getInstance(context);
        name = appManager.getAppNameFromPkg(pkgName);
        icon = appManager.getAppIcon(pkgName);
    }

    @Override
    public boolean equals(Object o) {
        if(o!=null && o instanceof AppInfo) {
            return ((AppInfo) o).pkgName.equals(this.pkgName);
        }
        return false;
    }


}
