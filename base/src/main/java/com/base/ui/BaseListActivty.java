package com.base.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.base.R;
import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;

/**
 * Created by tongyang on 16/7/15.
 */
public abstract class BaseListActivty extends BaseActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapterl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourcesId());

        mRecyclerView = (RecyclerView) findViewById(R.id.base_recycler);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_swiperefresh);

        mHeaderAndFooterRecyclerViewAdapterl=new HeaderAndFooterRecyclerViewAdapter(getRecycleViewAdapter());
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapterl);
        mRecyclerView.setLayoutManager(getRecycleViewLayoutManager());

        addRecycleViewHeadView(mRecyclerView);
    }

    public void addRecycleViewHeadView(RecyclerView recyclerView){}

    public int getLayoutResourcesId() {
        return R.layout.layout_baselist;
    }

    public RecyclerView getRecycleView() {
        return mRecyclerView;
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onlistener) {
        mSwipeRefreshLayout.setOnRefreshListener(onlistener);
    }

    abstract RecyclerView.LayoutManager getRecycleViewLayoutManager();

    abstract RecyclerView.Adapter getRecycleViewAdapter();

}
