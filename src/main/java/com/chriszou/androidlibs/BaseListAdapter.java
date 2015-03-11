/**
 * BaseListAdapter.java
 *
 * Created by zouyong on 7:10:15 AM, 2014
 */
package com.chriszou.androidlibs;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An implementation of BaseAdapter that uses a List as data source.
 * @author zouyong
 *
 */
public abstract class BaseListAdapter<E> extends BaseAdapter {

    protected List<E> mData;
    protected LayoutInflater mInflater;
    private Context mContext;

    public BaseListAdapter(Context context, List<E> data) {
        mData = new ArrayList<E>();
        if (data != null) {
            for(E e: data){
                mData.add(e);
            }
        }

        mContext = context;
    	mInflater = LayoutInflater.from(mContext);
    }

    protected Context getContext() {
        return mContext;
    }

    /**
     * Add an element
     * @param e
     */
    public void add(E e){
    	mData.add(e);
        notifyDataSetChanged();
    }

    /**
     * Add en element at the given position
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        mData.add(index, e);
        notifyDataSetChanged();
    }
    /**
     * Add several elements from an array
     * @param es
     */
    public void addAll(E[] es){
        if (es != null) {
            for(E e: es) {
                mData.add(e);
            }
            notifyDataSetChanged();
        }
    }
    /**
     * Add several elements from a collection
     * @param es
     */
    public void addAll(Collection<E> es) {
        if (es != null) {
            mData.addAll(es);
            notifyDataSetChanged();
        }
    }

    /**
     * Add several elements from a collection
     * @param es
     */
    public void addAll(int index, Collection<E> es) {
        mData.addAll(index, es);
        notifyDataSetChanged();
    }
    /**
     * Remove the element at the given index
     * @param index
     */
    public void remove(int index) {
    	mData.remove(index);
        notifyDataSetChanged();
    }

    /**
     * Return the index of the item
     * @param e
     * @return
     */
    public int indexOf(E e) {
        return mData.indexOf(e);
    }

    /**
     * Remove the given item from the adapter
     * @param e
     */
    public void remove(E e) {
        mData.remove(e);
        notifyDataSetChanged();
    }

    /**
     * Remove all the items from the adapter
     */
    public void removeAll() {
        mData.clear();
        notifyDataSetChanged();
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

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

    public boolean contains(E item) {
        return mData==null ? false : mData.contains(item);
    }
}
