package com.new_zhuama.login;

import com.new_zhuama.base.BasePresenter;
import com.new_zhuama.base.BaseView;

/**
 * Created by tongyang on 16/7/19.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        String getPassWord();

        String getUserNmae();

        void toNextPage();
    }


    interface Presenter extends BasePresenter {

        void login(String name, String pwd);
    }
}
