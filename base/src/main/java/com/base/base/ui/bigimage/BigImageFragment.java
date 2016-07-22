package com.base.base.ui.bigimage;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.R;
import com.base.utils.ImageLoadUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by tongyang on 16/7/22.
 */
public class BigImageFragment extends Fragment {

    private ImageView iv_sigle_image;
    private String mShowUrl;

    public BigImageFragment(String url) {
        this.mShowUrl = url;
    }

    public BigImageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_sigle_image, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_sigle_image = (ImageView) view.findViewById(R.id.iv_sigle_image);
        iv_sigle_image.setTag(mShowUrl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv_sigle_image.setTransitionName(mShowUrl);
        }
        ImageLoader.getInstance().displayImage(mShowUrl, iv_sigle_image, ImageLoadUtil.getOpitons());
    }


    public ImageView getImageView() {
        return iv_sigle_image;
    }
}
