package com.new_zhuama.base;


import com.base.net.BaseResponse;
import com.base.net.ResponseConverterFactory;
import com.new_zhuama.BuildConfig;
import com.new_zhuama.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
    private static final String BASE_URL_DEBUG = "http://apps.drama.wang/indexv2/";

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

//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//                .setExclusionStrategies(new ExclusionStrategy() {   //把RealmObject排除在外,不然会报错。
//                    @Override
//                    public boolean shouldSkipField(FieldAttributes f) {
//                        return f.getDeclaringClass().equals(RealmObject.class);
//                    }
//
//                    @Override
//                    public boolean shouldSkipClass(Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .create();

        mRetrofit = new Retrofit.Builder()
                .client(mHttpBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(ResponseConverterFactory.create())
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
