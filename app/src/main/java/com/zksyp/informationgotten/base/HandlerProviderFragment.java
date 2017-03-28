package com.zksyp.informationgotten.base;

import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/28
 * Time:上午10:17
 * Desc:
 */

public class HandlerProviderFragment extends Fragment {

    /**
     * activity, fragment 的基类里面维护的Handler,避免不正确的使用造成的内存泄漏。
     * Note: 这里不使用static类型的handler这里不使用static类型的handler,是为了多个每个Activity都拥有一个Handler实例,这样可以让多个activity各自同时处理自己的消息
     * (比如一个存活的后台的activity在用handler做一个延时任务,此时,前台的activity也需要用handler,这种情况下,static的Handler就会产生冲突)。
     */
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerProviderFragment.this.handleMessage(msg);
        }
    };

    protected Handler getHandler() {
        return mHandler;
    }

    protected void handleMessage(Message msg) {
        //可以被重写的方法,用来处理不同的msg。
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);//子类必须要调用super.onDestroy()。activity,fragment释放的时候,移除mHandler中的所有消息队列。防止内存泄漏
    }
}
