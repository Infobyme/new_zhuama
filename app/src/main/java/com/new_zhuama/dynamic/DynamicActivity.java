package com.new_zhuama.dynamic;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.base.base.adapter.CommonRecycleAdapter;
import com.base.base.adapter.CommonRecycleViewHolder;
import com.base.base.ui.BaseActivity;
import com.base.base.ui.bigimage.BigImageActivity;
import com.base.utils.ImageLoadUtil;
import com.new_zhuama.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 动态页面
 * Created by tongyang on 16/7/22.
 */
//@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DynamicActivity extends BaseActivity {


    private List<String> mData = new ArrayList<>();


    @Bind(R.id.dynamic_recyclerview)
    RecyclerView dynamic_recyclerview;


    private Bundle mTmpReenterState;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_dynamic;
    }

    @Override
    public void initView() {
        super.initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            SharedElementCallback mCallback = new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    super.onMapSharedElements(names, sharedElements);

                    if (mTmpReenterState != null) {
                        int startPosition = mTmpReenterState.getInt(BigImageActivity.EXTRA_STARTING_ALBUM_POSITION);
                        int currentPosition = mTmpReenterState.getInt(BigImageActivity.EXTRA_CURRENT_ALBUM_POSITION);

                        if (startPosition != currentPosition) {
                            String shareName = mData.get(currentPosition);
                            ImageView shareElement = (ImageView) dynamic_recyclerview.findViewWithTag(shareName);

                            if (shareElement != null) {
                                names.clear();
                                sharedElements.clear();
                                names.add(shareName);
                                sharedElements.put(shareName, shareElement);
                            }
                        }
                        mTmpReenterState = null;
                    }

                }
            };

            setExitSharedElementCallback(mCallback);
        }

        for (int i = 0; i < ImageLoadUtil.imageUrls.length; i++) {
            String imageUrl = ImageLoadUtil.imageUrls[i];
            mData.add(imageUrl);
        }
        dynamic_recyclerview.setLayoutManager(new GridLayoutManager(mActivity, 2));
        dynamic_recyclerview.setAdapter(new CommonRecycleAdapter<String>(mActivity, R.layout.item_text, mData) {

            @Override
            public void convert(final CommonRecycleViewHolder holder, final String s, final int position) {
                holder.setImageViewByImageLoad(R.id.image, s);
                holder.getView(R.id.image).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, BigImageActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("imageUrl", (ArrayList<String>) mData);
                        bundle.putInt("position", position);
                        intent.putExtras(bundle);
                        ActivityOptions options = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            options = ActivityOptions
                                    .makeSceneTransitionAnimation(mActivity, holder.getView(R.id.image), s);
                        }
                        startActivity(intent, options.toBundle());
                    }
                });
            }
        });


    }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startPostponedEnterTransition();
        }
        mTmpReenterState = data.getExtras();
        int startPosition = mTmpReenterState.getInt(BigImageActivity.EXTRA_STARTING_ALBUM_POSITION);
        int currentPosition = mTmpReenterState.getInt(BigImageActivity.EXTRA_CURRENT_ALBUM_POSITION);

        if (startPosition != currentPosition) {
            dynamic_recyclerview.scrollToPosition(currentPosition);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            postponeEnterTransition();
//
//            dynamic_recyclerview.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                @Override
//                public boolean onPreDraw() {
//                    dynamic_recyclerview.getViewTreeObserver().removeOnPreDrawListener(this);
//                    // TODO: figure out why it is necessary to request layout here in order to get a smooth transition.
//                    dynamic_recyclerview.requestLayout();
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        startPostponedEnterTransition();
//                    }
//                    return true;
//                }
//            });
//        }

    }
}
