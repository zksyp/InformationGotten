package com.zksyp.informationgotten.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.zksyp.informationgotten.BaseApplication;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/20
 * Time:下午4:30
 * Desc:
 */

public class ActivityUtil {
    private static LinkedList<Activity> mActivityList = new LinkedList<>();

    public static LinkedList<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }

    public static void finishAllActivity() {
        if (mActivityList != null) {
            for (Activity activity : mActivityList) {
                activity.finish();
            }
        }
    }

    /**
     * 获取页面最前面的activity
     */
    public static Activity getForegroundActivity() {
        if (mActivityList != null) {
            return mActivityList.get(mActivityList.size() - 1);
        }
        return null;
    }

    public static boolean isTheOnlyActivity() {
        int aliveNum = 0;
        LinkedList<Activity> activities = getActivityList();
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).isFinishing()) {
                continue;
            } else {
                aliveNum++;
            }
        }
        return aliveNum <= 1;
    }

    /**
     * 判断app是否在前台运行
     */
    public static boolean isAppOnForeground() {

        ActivityManager activityManager =
                (ActivityManager) BaseApplication.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = BaseApplication.getApp().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }
}
