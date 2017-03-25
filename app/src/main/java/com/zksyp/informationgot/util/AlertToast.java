package com.zksyp.informationgot.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zksyp.informationgot.BaseApplication;
import com.zksyp.informationgot.R;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:上午11:46
 * Desc:通用toast提示
 */

public class AlertToast {
    private static Toast sToast = null;
    public static final int SHOW_TIME = 1_500;

    /**
     * 通用toast提示
     *
     * @param resId
     */
    public static void show(int resId) {
        showBlack(resId, SHOW_TIME);
    }

    /**
     * 通用toast提示
     *
     * @param text
     */
    public static void show(String text) {
        showBlack(text, SHOW_TIME);
    }

    /**
     * 线程中使用的toast提示
     *
     * @param text
     */
    public static void showAsync(String text) {
        showBlackFormThread(text, 3000);
    }

    /**
     * @param text
     * @param duration
     * @deprecated 旧的toast提示
     */
    private static void show(String text, int duration) {
        if (StringUtil.isEmpty(text)) {
            return;
        }
        View layout = LayoutInflater.from(BaseApplication.getApp()).inflate(R.layout.view_alert_toast, null);

        TextView toastText = (TextView) layout.findViewById(R.id.toast_alert_txt);
        toastText.setText(text);

        Toast customToast = new Toast(BaseApplication.getApp());
        customToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 150);
        customToast.setDuration(duration);
        customToast.setView(layout);
        customToast.show();
    }

    private static void showBlack(String text, int duration) {
        if (StringUtil.isEmpty(text)) {
            return;
        }
        if (sToast == null) {
            View layout = LayoutInflater.from(BaseApplication.getApp()).inflate(R.layout.view_alert_toast_black, null);
            sToast = new Toast(BaseApplication.getApp());
            sToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            sToast.setDuration(duration);
            sToast.setView(layout);
        }
        TextView tv = (TextView) sToast.getView().findViewById(R.id.toast_alert_txt);
        tv.setText(text);
        sToast.show();
    }

    private static void showBlack(int resId, int duration) {
        if (sToast == null) {
            View layout = LayoutInflater.from(BaseApplication.getApp()).inflate(R.layout.view_alert_toast_black, null);
            sToast = new Toast(BaseApplication.getApp());
            sToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            sToast.setDuration(duration);
            sToast.setView(layout);
        }
        TextView tv = (TextView) sToast.getView().findViewById(R.id.toast_alert_txt);
        tv.setText(resId);
        sToast.show();
    }

    private static void showBlackFormThread(String text, int duration) {
        if (StringUtil.isEmpty(text)) {
            return;
        }
        Message msg = mHandler.obtainMessage();
        msg.obj = text;
        msg.arg1 = duration;
        mHandler.sendMessage(msg);
    }

    private static Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            showBlack((String) msg.obj, msg.arg1);
        }
    };
}
