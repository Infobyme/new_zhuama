package com.new_zhuama.net;

import com.new_zhuama.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhuama on 16/5/18.
 */
public class HttpRequest {

    private static HttpRequest mRequest;

    private static final String BASE_URL = "";
    private static final String BASE_URL_DEBUG = "";

    private static final int TIME_OUT_DEfAULT = 10;
    private Retrofit mRetrofit;
    private ApiServices mApiServices;


    private HttpRequest() {

        String baseUrl = BASE_URL;

        if (BuildConfig.DEBUG) {
            baseUrl = BASE_URL_DEBUG;
        }

        OkHttpClient.Builder mHttpBuilder=new OkHttpClient.Builder();
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
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiServices =mRetrofit.create(ApiServices.class);

    }


    public static HttpRequest getInstance(){
        if (mRequest==null){

            synchronized (HttpRequest.class){
                if (mRequest==null){
                    mRequest=new HttpRequest();
                }
            }
        }
        return  mRequest;
    }


    public ApiServices getApiService(){
        return  mApiServices;
    }



}
