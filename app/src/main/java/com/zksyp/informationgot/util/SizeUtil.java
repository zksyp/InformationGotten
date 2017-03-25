package com.zksyp.informationgot.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.zksyp.informationgot.BaseApplication;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/21
 * Time:上午10:30
 * Desc:
 */

public class SizeUtil {

    public static int dip2px(float dipValue) {
        float reSize = BaseApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    /**
     * 获取某个dp的资源对应的pix值
     */
    public static int pxOfDpResId(int dpResId) {
        try {
            return BaseApplication.getApp().getResources().getDimensionPixelSize(dpResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static float sp2px(int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                BaseApplication.getApp().getResources().getDisplayMetrics());
    }

    public static float dp2sp(int dpValue) {
        DisplayMetrics displayMetrics = BaseApplication.getApp().getResources().getDisplayMetrics();
        return (float) dpValue / displayMetrics.density * displayMetrics.scaledDensity;
    }

    public static int px2dip(int pxValue) {
        float reSize = BaseApplication.getApp().getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }
}
