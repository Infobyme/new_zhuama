package com.base.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhuama on 16/7/14.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected List<T> mDatas;
    protected Context mContext;
    protected int mItemLayoutId;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent, mItemLayoutId);

        convert(holder, getItem(position), position);

        return holder.getConverView();
    }

    public abstract void convert(CommonViewHolder holder, T item, int position);


    private CommonViewHolder getViewHolder(View converView, ViewGroup parent, int itemLayoutId) {
        return CommonViewHolder.get(mContext, converView, parent, itemLayoutId);
    }
}
