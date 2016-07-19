package com.new_zhuama.login;

import android.content.Context;
import android.widget.Toast;

import com.base.net.BaseResponse;
import com.new_zhuama.base.BaseRequest;
import com.new_zhuama.entity.User;

import rx.Observable;
import rx.Subscriber;

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

        Subscriber<BaseResponse<User>> subscriber=new Subscriber<BaseResponse<User>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(BaseResponse<User> userBaseResponse) {
                mLoginView.toNextPage();
            }
        };

        Observable<BaseResponse<User>> observable = BaseRequest.getInstance().login(name, pwd);
        observable.subscribe(subscriber);
    }

    @Override
    public void start() {

    }
}
