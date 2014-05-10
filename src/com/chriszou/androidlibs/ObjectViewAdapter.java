/**
 * ObjectViewAdapter.java
 * 
 * Created by zouyong on 11:01:47 AM, 2014
 */
package com.chriszou.androidlibs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @author zouyong
 *
 */
public class ObjectViewAdapter<E> extends BaseAdapter {
	private List<E> mData;
    private LayoutInflater mInflater;
    private int mLayoutResId;
    
	/**
     * Constructor of an ObjectViewAdapter
	 * @param context
	 * @param data The object list. These objects will be added to the container of this Adapter one by one. 
	 *     		so changes in the passed container will not effect the container of this Adapter. The objects' fields MUST have been decorated by {@link OVM}
	 * @param layoutResId
	 */
	public ObjectViewAdapter(Context context, List<E> data, int layoutResId) {
		mInflater = LayoutInflater.from(context);
        mLayoutResId = layoutResId;
        mData = new ArrayList<E>();
        mData.addAll(data);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
        return mData.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public E getItem(int position) {
        return mData.get(position);
	}

	/* 
	 * Return the position of the object directly
	 */
	@Override
	public long getItemId(int position) {
        return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null) {
			convertView = mInflater.inflate(mLayoutResId, null);
		}
        
		E item = getItem(position);
        Field[] fields = item.getClass().getFields();
        for(Field field: fields) {
        	OVM ovm = field.getAnnotation(OVM.class);
            if(ovm!=null) {
            	int viewId = ovm.viewId();
                View view = convertView.findViewById(viewId);
                Class<? extends View> c = view.getClass();
                Method method;
				try {
					method = c.getMethod("setText");
					method.invoke(c, field.get(item));
				} catch (Exception e) {
					e.printStackTrace();
				} 
            }
        }
        
        return convertView;
	}

}
