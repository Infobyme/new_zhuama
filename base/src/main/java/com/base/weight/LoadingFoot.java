package com.base.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by tongyang on 16/7/15.
 */
public class LoadingFoot extends RelativeLayout {

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
}
