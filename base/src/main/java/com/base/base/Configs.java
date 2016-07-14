package com.base.base;

import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by tongyang on 16/7/14.
 */
public class Configs {

    public static final String cachePath = "new_zhuama/image";

    public static final File cacheDir = StorageUtils.getOwnCacheDirectory(BaseApplication.getApplication(), cachePath);

}
