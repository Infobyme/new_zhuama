package com.base.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.utils.ImageLoadUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 通用的RecycleViewHolder
 * Created by tongyang on 16/7/18.
 */
public class CommonRecycleViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    private CommonRecycleViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static CommonRecycleViewHolder get(Context context, ViewGroup parent, int layoutId) {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);

        CommonRecycleViewHolder holder = new CommonRecycleViewHolder(itemView, context);

        return holder;

    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }


    public void setTextViewText(int viewId,String text){
        TextView view=getView(viewId);
        view.setText(text);
    }

    public void setImageViewResource(int viewId, int resourceId) {
        ImageView view = getView(viewId);
        view.setImageResource(resourceId);
    }

    public void setImageViewByImageLoad(int viewId,String httpUrl){
        ImageView view = getView(viewId);
        view.setTag(httpUrl);
        ImageLoader.getInstance().displayImage(httpUrl,view, ImageLoadUtil.getOpitons());
    }
}
