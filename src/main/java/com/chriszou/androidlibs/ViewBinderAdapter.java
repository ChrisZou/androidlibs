/**
 * ViewBinderAdapter.java
 *
 * Created by zouyong on May 16, 2014,2014
 */
package com.chriszou.androidlibs;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zouyong
 *
 */
public class ViewBinderAdapter<E> extends BaseListAdapter<E>{
    private ViewBinder<E> mViewBinder;
    private int mItemLayoutRes;
	/**
	 * @param context
	 * @param data
	 */
	public ViewBinderAdapter(Context context, List<E> data, int itemLayoutRes, ViewBinder<E> viewBinder) {
		super(context, data);
        mItemLayoutRes = itemLayoutRes;
        mViewBinder = viewBinder;
	}

    protected ViewBinder<E> getViewBinder() {
        return mViewBinder;
    }

    public ViewBinderAdapter(Context context, List<E> data, ViewBinder<E> viewBinder) {
    	this(context, data, android.R.layout.simple_list_item_1,viewBinder);
        mItemLayoutRes = viewBinder.getLayoutRes()==0 ? android.R.layout.simple_list_item_1 : viewBinder.getLayoutRes();
    }
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
        	convertView = mInflater.inflate(mItemLayoutRes, null);
        }

        mViewBinder.bindView(position, convertView, getItem(position), parent);
        return convertView;
	}

	public static interface ViewBinder<E> {
        public int getLayoutRes();
		public void bindView(int position, View view, E item, ViewGroup parent);
	}

}
