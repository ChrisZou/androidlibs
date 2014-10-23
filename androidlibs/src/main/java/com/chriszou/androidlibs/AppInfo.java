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
 * @author zouyong
 *
 */
public class AppInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String pkgName;

    public String name;

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
