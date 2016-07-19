package com.base.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by tongyang on 16/7/15.
 */
public class LoadingFoot extends LinearLayout {


    public LoadingFoot(Context context) {
        this(context, null, 0);
    }

    public LoadingFoot(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFoot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    public enum LoadingState {
        STATE_NORMAL, STATE_LOADING
    }
}
