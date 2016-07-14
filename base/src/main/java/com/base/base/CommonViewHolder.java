package com.base.base;

import android.content.Context;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ListView GridView 通用ViewHolder
 * from hongyang
 * Created by tongyang on 16/7/14.
 */
public class CommonViewHolder {


    private SparseArray<View> mViews;
    private View mConvertView;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId) {

        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);

        mConvertView.setTag(this);

    }


    public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId) {

        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId);
        }

        return (CommonViewHolder) convertView.getTag();
    }


    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }


    public void setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
    }

    public void setText(int viewId, SpannableString spannableString) {
        TextView view = (TextView) getView(viewId);
        view.setText(spannableString);
    }

    public void setImageViewResource(int viewId, int resourceId) {
        ImageView view = getView(viewId);
        view.setImageResource(resourceId);
    }


    public View getConverView() {
        return mConvertView;
    }


}
