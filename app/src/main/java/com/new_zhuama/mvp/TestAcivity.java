package com.new_zhuama.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.new_zhuama.R;
import com.new_zhuama.mvp.entity.MovieEntity;
import com.new_zhuama.mvp.net.HttpMethod;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by zhuama on 16/5/17.
 */
public class TestAcivity extends Activity {


    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {

        getMovie();
    }

    private void getMovie() {


        Subscriber<MovieEntity> subscriber=new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(TestAcivity.this, "oncompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText("onerror");
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                resultTV.setText("onnext");
            }
        };

        HttpMethod.getInstance().getTopMovie(subscriber,0,10);

//        String baseUrl="https://api.douban.com/v2/movie/";
//
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加Rxjava
//                .build();
//
//        MovieService movieService=retrofit.create(MovieService.class);
//
//        movieService.getTopMovie(0,10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieEntity>() {
//
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(TestAcivity.this,"oncompleted",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        resultTV.setText("onerror");
//                    }
//
//                    @Override
//                    public void onNext(MovieEntity movieEntity) {
//                        resultTV.setText("onnext");
//                    }
//                });
//        Call<MovieEntity> call=movieService.getTopMovie(0,10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                resultTV.setText(t.getMessage());
//            }
//        });
    }


}
