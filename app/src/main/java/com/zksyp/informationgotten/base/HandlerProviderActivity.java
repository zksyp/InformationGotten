package com.zksyp.informationgotten.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/20
 * Time:下午4:16
 * Desc:只维护一个handler以防止内存泄漏
 */

public class HandlerProviderActivity extends AppCompatActivity {
    private final Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerProviderActivity.this.handleMessage(msg);
        }
    };

    protected Handler getHandler() {
        return mHandler;
    }

    protected void handleMessage(Message msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
