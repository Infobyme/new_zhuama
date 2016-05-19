package com.new_zhuama.mvp.interfaces;

import com.new_zhuama.mvp.entity.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuama on 16/5/17.
 */
public interface MovieService {

    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start,@Query("count") int count);
}
