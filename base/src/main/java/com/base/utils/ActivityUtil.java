package com.base.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongyang on 16/7/21.
 */
public class ActivityUtil {


    private static class SingleHolder {
        private static final ActivityUtil mActivity = new ActivityUtil();
    }

    public static ActivityUtil getInstance() {
        return SingleHolder.mActivity;
    }

    private static List<Activity> mActivitys = new ArrayList<>();


    public void addActivity(Activity activity) {
        mActivitys.add(activity);
    }


    public void removeActivity(Activity activity) {
        mActivitys.remove(activity);
    }


    public void removeAll() {

        for (Activity activity : mActivitys) {
            if (activity != null)
                activity.finish();
        }

    }

}
