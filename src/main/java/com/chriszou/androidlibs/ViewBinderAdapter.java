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
import android.widget.TextView;

/**
 * @author zouyong
 *
 */
public class ViewBinderAdapter<E> extends BaseListAdapter<E>{
    private ViewBinder<E> mViewBinder = sDefaultBinder;
    private int mItemLayoutRes;
    /**
     * @param context
     * @param data
     */
    public ViewBinderAdapter(Context context, List<E> data) {
        this(context, data, sDefaultBinder);
    }

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

        try {
            //Typically if exception were thrown here, you don't have much to do but to let the app crash.
            //So we do a try catch here to prevent this
            mViewBinder.bindView(position, convertView, getItem(position), parent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
	}

	public static interface ViewBinder<E> {
        public int getLayoutRes();
		public void bindView(int position, View view, E item, ViewGroup parent);
	}

    private static ViewBinder sDefaultBinder = new ViewBinder() {
        @Override
        public int getLayoutRes() {
            return 0;
        }

        @Override
        public void bindView(int position, View view, Object item, ViewGroup parent) {
            TextView textView = (TextView) view;
            textView.setText(item.toString());
        }
    };
}
