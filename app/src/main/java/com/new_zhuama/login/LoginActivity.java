package com.new_zhuama.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.base.base.ui.BaseActivity;
import com.new_zhuama.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 * Created by tongyang on 16/7/19.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {


    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    private LoginPresenter mPresenter;


    @Override
    protected int getLayoutResources() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        super.initView();
        mPresenter=new LoginPresenter(mActivity,this);
    }

    @OnClick(R.id.btn_submit)
    public void submit(){

        mPresenter.login(getUserNmae(),getPassWord());
    }

    @Override
    public String getPassWord() {
        return etPwd.getText().toString();
    }

    @Override
    public String getUserNmae() {
        return etName.getText().toString();
    }

    @Override
    public void toNextPage() {
        Toast.makeText(mActivity,"toNextPage",Toast.LENGTH_SHORT).show();
    }
}
