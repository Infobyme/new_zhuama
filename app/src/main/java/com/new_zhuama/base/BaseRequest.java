package com.new_zhuama.base;


import com.base.base.net.BaseResponse;
import com.base.base.net.ResponseConverterFactory;
import com.base.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.new_zhuama.BuildConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tongyang on 16/5/18.
 */
public class BaseRequest {

    private static final String BASE_URL = "http://app.drama.wang/indexv2/";
    private static final String BASE_URL_DEBUG = "http://apps.drama.wang/indexv3/";

    private static final int TIME_OUT_DEfAULT = 10;
    private Retrofit mRetrofit;
    private ApiServices mApiServices;


    private BaseRequest() {

        String baseUrl = BASE_URL;

        if (BuildConfig.DEBUG) {
            baseUrl = BASE_URL_DEBUG;
        }

        OkHttpClient.Builder mHttpBuilder = new OkHttpClient.Builder();
        mHttpBuilder.connectTimeout(TIME_OUT_DEfAULT, TimeUnit.SECONDS);

        mHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        mRetrofit = new Retrofit.Builder()
                .client(mHttpBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(ResponseConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiServices = mRetrofit.create(ApiServices.class);

    }


    private static class SingletonHodler {
        private static final BaseRequest INSTANCE = new BaseRequest();
    }

    public static BaseRequest getInstance() {
        return SingletonHodler.INSTANCE;
    }


    public Observable<BaseResponse<User>> login(String name, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", name);
        map.put("pwd", pwd);
        map.put("ext", "+86");
        map.put("language", "0");
        map.put("mtype", "huawei");
        map.put("sversion", "1.0");
        map.put("channel", "xiaomi");
        map.put("devicetoken", "1");
        map.put("ver", "1");
        map.put("dtype", "2");
        Observable<BaseResponse<User>> observable = mApiServices.login(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }


    public ApiServices getApiService() {
        return mApiServices;
    }


}
