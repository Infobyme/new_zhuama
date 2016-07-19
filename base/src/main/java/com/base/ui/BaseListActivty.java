package com.base.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.base.R;
import com.base.weight.LoadingFoot;
import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.cundong.recyclerview.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tongyang on 16/7/15.
 */
public abstract class BaseListActivty<T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<T> mData = new ArrayList<>();
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapterl;


    private int mPage = 1;
    private int mNumber = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourcesId());

        mRecyclerView = (RecyclerView) findViewById(R.id.base_recycler);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_swiperefresh);

        mHeaderAndFooterRecyclerViewAdapterl = new HeaderAndFooterRecyclerViewAdapter(getRecycleViewAdapter());
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapterl);
        mRecyclerView.setLayoutManager(getRecycleViewLayoutManager());

        addRecycleViewHeadView(mRecyclerView);
        RecyclerViewUtils.setFooterView(mRecyclerView, new LoadingFoot(this));

        mRecyclerView.setOnScrollListener(onLoadMoreScrollListener);

        init();
        dealWithRequest();

    }

    public void init() {

    }

    public void addRecycleViewHeadView(RecyclerView recyclerView) {
    }


    public int getLayoutResourcesId() {
        return R.layout.layout_baselist;
    }


    private void dealWithRequest() {
        Observable<T> observable = sendRequest();
        observable
                .subscribe(getSubscriber());

    }

    public RecyclerView getRecycleView() {
        return mRecyclerView;
    }

    public HeaderAndFooterRecyclerViewAdapter getAdapter() {
        return mHeaderAndFooterRecyclerViewAdapterl;
    }

    public abstract RecyclerView.LayoutManager getRecycleViewLayoutManager();

    public abstract RecyclerView.Adapter getRecycleViewAdapter();

    public abstract Observable<T> sendRequest();

    public abstract Subscriber<T> getSubscriber();


    public void stopRefresh() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onRefresh() {
        mPage = 1;
        dealWithRequest();
    }

    private EndlessRecyclerOnScrollListener onLoadMoreScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            if (mHeaderAndFooterRecyclerViewAdapterl.getFooterViewsCount() > 0) {
                LoadingFoot foot = (LoadingFoot) mHeaderAndFooterRecyclerViewAdapterl.getFooterView();
                foot.setVisibility(View.VISIBLE);
            }
            mPage++;
            dealWithRequest();
        }
    };
}
