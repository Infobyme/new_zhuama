package com.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 测量Util
 * Created by tongyang on 16/4/19.
 */
public class MeasureUtil {


    /**
     * 获取屏幕宽高
     *
     * @param context
     * @return 0:屏幕宽度   1:屏幕高度
     */
    public static int[] getScreenSize(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return new int[]{dm.widthPixels, dm.heightPixels};
    }


    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
