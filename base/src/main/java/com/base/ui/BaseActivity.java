package com.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by zhuama on 16/5/13.
 */
public abstract class BaseActivity extends FragmentActivity {


    private Context mContxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContxt=this;
    }

//    public abstract void init();

}
