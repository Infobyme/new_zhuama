package com.base.utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;


/**
 * 图片加载类
 * Created by tongyang on 16/7/14.
 */
public class ImageLoadUtil {


    private DisplayImageOptions getOpitons() {
        // 3.DisplayImageOptions实例对象的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.app_default_icon) // 设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.mipmap.app_default_icon)// 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.mipmap.app_default_icon) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
//                .displayer(new FadeInBitmapDisplayer(600))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成

        return options;
    }
}
