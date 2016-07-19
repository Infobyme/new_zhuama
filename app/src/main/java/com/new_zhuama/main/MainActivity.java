package com.new_zhuama.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.base.ui.BaseActivity;
import com.new_zhuama.R;
import com.new_zhuama.main.fragment.BrokeFragment;
import com.new_zhuama.main.fragment.DynamicFragment;
import com.new_zhuama.main.fragment.FindFragment;
import com.new_zhuama.main.fragment.MeFragment;
import com.new_zhuama.main.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


    @Bind(R.id.main_viewpage)
    ViewPager mViewpage;
    @Bind(R.id.main_tablayout)
    TabLayout mTablayout;

    private MainFragmentAdapter mViewPageAdapter;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabs;

    private TabLayout.Tab mFindTab, mDynamicTab, mBrokeTab, mMessageTab, mMeTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    public void init() {

        initFragment();//初始化fragment

        mTabs = Arrays.asList(getResources().getStringArray(R.array.main_tabs));
        mViewPageAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mFragments, mTabs);
        mViewpage.setAdapter(mViewPageAdapter);
        mTablayout.setupWithViewPager(mViewpage);

        initTab();//初始化tab

    }

    private void initTab() {

        mFindTab = mTablayout.getTabAt(0);
        mDynamicTab = mTablayout.getTabAt(1);
        mBrokeTab = mTablayout.getTabAt(2);
        mMessageTab = mTablayout.getTabAt(3);
        mMeTab = mTablayout.getTabAt(4);


        mFindTab.setIcon(R.drawable.selecor_find);
        mDynamicTab.setIcon(R.drawable.selecor_dynamic);
        mBrokeTab.setIcon(R.drawable.selecor_broke);
        mMessageTab.setIcon(R.drawable.selecor_message);
        mMeTab.setIcon(R.drawable.selecor_me);

    }

    private void initFragment() {
        mFragments.add(new FindFragment());
        mFragments.add(new DynamicFragment());
        mFragments.add(new BrokeFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MeFragment());
    }


}
