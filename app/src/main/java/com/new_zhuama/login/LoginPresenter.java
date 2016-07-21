package com.new_zhuama.login;

import android.content.Context;
import android.widget.Toast;

import com.base.base.net.AbsAPICallback;
import com.base.base.net.ApiException;
import com.base.base.net.BaseResponse;
import com.new_zhuama.base.BaseRequest;
import com.base.entity.User;

import rx.Observable;

/**
 * Created by tongyang on 16/7/19.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;
    private Context mContext;

    public LoginPresenter(Context context,LoginContract.View loginView) {
        this.mLoginView = loginView;
        mContext=context;
    }

    @Override
    public void login(String name, String pwd) {

        AbsAPICallback<BaseResponse<User>> subscriber=new AbsAPICallback<BaseResponse<User>>("网络出错","网络出错","网络出错") {
            @Override
            protected void onError(ApiException ex) {
                Toast.makeText(mContext,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            protected void onPermissionError(ApiException ex) {
                Toast.makeText(mContext,ex.getMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            protected void onResultError(ApiException ex) {
                Toast.makeText(mContext,ex.getMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(BaseResponse<User> userBaseResponse) {
                Toast.makeText(mContext,"onNext",Toast.LENGTH_SHORT).show();

            }
        };
        Observable<BaseResponse<User>> observable = BaseRequest.getInstance().login(name, pwd);
        observable.subscribe(subscriber);
    }

    @Override
    public void start() {

    }
}
