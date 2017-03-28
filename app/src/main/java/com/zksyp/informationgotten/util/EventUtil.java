package com.zksyp.informationgotten.util;

import android.view.MotionEvent;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/21
 * Time:上午10:29
 * Desc:多次点击事件优化
 */

public class EventUtil {
    /**
     * 最大同时点击数
     */
    private static final int MAX_POINT_NUMBER = 2;

    /**
     * 两次点击最小时间间隔
     */
    private static final int MIN_TIME_INTERVAL = 300;

    /**
     * 记录上一次Touch Down的时间
     */
    private static long mLastTouchDownTime;

    public static boolean isAllowDispatchEvent(MotionEvent event) {
        // forbid touch event according point index
        if (event.getActionIndex() >= MAX_POINT_NUMBER) {
            return false; // 屏蔽多点事件
        }
        // time interval
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            if (Math.abs(event.getDownTime() - mLastTouchDownTime) < MIN_TIME_INTERVAL) {
                return false; // 两次点击时间间隔过短，屏蔽该事件
            } else {
                mLastTouchDownTime = event.getDownTime(); // 记录第一次Touch Down时间点
            }
        }
        return true;
    }

}
