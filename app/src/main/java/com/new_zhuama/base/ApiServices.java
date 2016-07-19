package com.new_zhuama.base;


import com.base.net.BaseResponse;
import com.new_zhuama.entity.User;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zhuama on 16/5/18.
 */
public interface ApiServices {

    @GET("login")
    Observable<BaseResponse<User>> login(@QueryMap Map<String,String> options);

}
