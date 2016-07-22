package com.base.base.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.base.utils.ActivityUtil;

import butterknife.ButterKnife;

/**
 * acivity 基类
 * Created by tongyang on 16/5/13.
 */
public abstract class BaseActivity extends FragmentActivity {


    public Activity mActivity;
    private Bundle mBundle;
    public View mParentView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setContentView(initParentView());
        ButterKnife.bind(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(setStatusBarColor());
//        }
//        EventBus.getDefault().register(this);
        ActivityUtil.getInstance().addActivity(this);
        mBundle = getIntentBundle();
        initView();
    }

    public void initView() {

    }

    private Bundle getIntentBundle() {
        Intent intent = getIntent();
        return intent.getExtras();
    }

    private View initParentView() {
        mParentView = LayoutInflater.from(mActivity).inflate(getLayoutResources(), null);
        return mParentView;
    }

    public int setStatusBarColor() {
        return Color.parseColor("#f0f0f0");
    }

    public Object getParamet(String key, Object defaultObject, boolean isSingle) {


        if (isSingle) {
            String type = defaultObject.getClass().getSimpleName();
            if (mBundle.containsKey(key)) {
                if ("String".equals(type)) {
                    return mBundle.getString(key, defaultObject.toString());
                } else if ("Integer".equals(type)) {
                    return mBundle.getInt(key, (Integer) defaultObject);
                } else if ("Boolean".equals(type)) {
                    return mBundle.getBoolean(key, (Boolean) defaultObject);
                } else if ("ArrayList".equals(type)) {
                    return mBundle.getStringArrayList(key);
                }
            }
        } else {
            return mBundle.getSerializable(key);
        }
        return null;
    }

    protected abstract int getLayoutResources();

    public void showProgressDialog() {

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("正在加载中....");
        }
        mProgressDialog.show();
    }

    public void dissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
        ActivityUtil.getInstance().removeActivity(this);
    }
}
