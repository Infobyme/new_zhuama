package com.new_zhuama.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 测量Util
 * Created by tongyang on 16/4/19.
 */
public class MeasureUtil {


    /**
     * 获取屏幕宽高
     * @param context
     * @return 0:屏幕宽度   1:屏幕高度
     */
    public static int[] getScreenSize(Context context){
        DisplayMetrics dm=context.getResources().getDisplayMetrics();
        return new int[]{dm.widthPixels,dm.heightPixels};
    }


}
