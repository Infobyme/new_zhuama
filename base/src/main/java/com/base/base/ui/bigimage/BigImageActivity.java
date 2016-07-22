package com.base.base.ui.bigimage;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.base.R;
import com.base.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查看大图页面
 * Created by tongyang on 16/7/22.
 */
public class BigImageActivity extends BaseActivity {

    private ViewPager vp_bigimage;
    private MyBigImagePageAdapter mAdapter;

    private int mPosition;
    private int mCurrentPosition;
    private List<String> mUrls;
    private boolean mIsReturning;

    public static final String EXTRA_STARTING_ALBUM_POSITION = "extra_starting_item_position";
    public static final String EXTRA_CURRENT_ALBUM_POSITION = "extra_current_item_position";


    @Override
    protected int getLayoutResources() {
        return R.layout.activity_bigimage;
    }

    @Override
    public void initView() {
        super.initView();
        vp_bigimage = (ViewPager) findViewById(R.id.vp_bigimage);
        mPosition = (int) getParamet("position", 0, true);
        mCurrentPosition = mPosition;
        mUrls = (List<String>) getParamet("imageUrl", new ArrayList<String>(), true);
        vp_bigimage.setAdapter(mAdapter = new MyBigImagePageAdapter(getSupportFragmentManager()));
        vp_bigimage.setCurrentItem(mPosition);

        vp_bigimage.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mCurrentPosition = position;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SharedElementCallback mCallback = new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);
                    if (mPosition != mCurrentPosition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ImageView sharedView = ((BigImageFragment) mAdapter.getItem(mCurrentPosition)).getImageView();

                            if (sharedView != null) {
                                names.clear();
                                sharedElements.clear();
                                names.add(sharedView.getTransitionName());
                                sharedElements.put(sharedView.getTransitionName(), sharedView);
                            }
                        }
                    }


                }
            };

            setEnterSharedElementCallback(mCallback);
        }

    }


    @Override
    public void finishAfterTransition() {

        Intent data = new Intent();
        data.putExtra(EXTRA_STARTING_ALBUM_POSITION, mPosition);
        data.putExtra(EXTRA_CURRENT_ALBUM_POSITION, mCurrentPosition);
        setResult(RESULT_OK, data);

        super.finishAfterTransition();
    }

    public class MyBigImagePageAdapter extends FragmentPagerAdapter {


        public MyBigImagePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BigImageFragment(mUrls.get(position));
        }

        @Override
        public int getCount() {
            return mUrls.size();
        }
    }
}
