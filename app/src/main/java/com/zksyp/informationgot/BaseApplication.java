package com.zksyp.informationgot;

import android.support.multidex.MultiDexApplication;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/20
 * Time:下午4:30
 * Desc:
 */

public class BaseApplication extends MultiDexApplication {
    protected static BaseApplication sApp;


    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static BaseApplication getApp() {
        return sApp;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        sApp = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        sApp = this;
    }


}
