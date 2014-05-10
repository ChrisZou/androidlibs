/**
 * OVM.java
 * 
 * Created by zouyong on 10:59:06 AM, 2014
 */
package com.chriszou.androidlibs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Object-View Mapping annotation
 * @author zouyong
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OVM {
    public int viewId();
}
