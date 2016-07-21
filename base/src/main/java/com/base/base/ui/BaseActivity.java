package com.base.base.ui;

import android.app.Activity;
import android.app.ProgressDialog;
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
        initView();
    }

    public void initView() {
    }

    public View initParentView() {
        mParentView = LayoutInflater.from(mActivity).inflate(getLayoutResources(), null);
        return mParentView;
    }

    public int setStatusBarColor() {
        return Color.parseColor("#f0f0f0");
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
        ActivityUtil.getInstance().removeActivity(this);
    }
}
