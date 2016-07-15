package com.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by zhuama on 16/5/13.
 */
public abstract class BaseActivity extends FragmentActivity {


    private Activity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
    }

//    public abstract void init();

}
