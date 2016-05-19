package com.new_zhuama.mvp.net;

import com.new_zhuama.mvp.entity.MovieEntity;
import com.new_zhuama.mvp.interfaces.MovieService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhuama on 16/5/17.
 */
public class HttpMethod {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";


    private static final int DEFAULT_TIMEOUT = 5;


    private Retrofit retrofit;
    private MovieService movieService;


    private HttpMethod() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(MovieService.class);

    }

    private static class SingletonHodler {
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    public static HttpMethod getInstance(){
        return SingletonHodler.INSTANCE;
    }



    public void getTopMovie(Subscriber<MovieEntity> subscriber,int start ,int count){

        movieService.getTopMovie(0,10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}
