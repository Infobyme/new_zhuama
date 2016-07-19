package com.base.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tongyang on 16/7/18.
 */
public abstract class CommonRecycleAdapter<T> extends RecyclerView.Adapter<CommonRecycleViewHolder> {

    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private LayoutInflater mInflater;

    public CommonRecycleAdapter(Context context, int layoutId, List<T> data) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
        this.mData = data;
    }

    @Override
    public CommonRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonRecycleViewHolder holder = CommonRecycleViewHolder.get(mContext, parent, mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonRecycleViewHolder holder, int position) {
        convert(holder, mData.get(position), position);
    }

    public abstract void convert(CommonRecycleViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void addAll(List<T> allData) {
        mData.clear();
        mData.addAll(allData);
        notifyDataSetChanged();
    }
}
