package com.zksyp.informationgotten.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zksyp.informationgotten.R;
import com.zksyp.informationgotten.util.LogUtil;

import java.util.HashSet;
import java.util.Iterator;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/27
 * Time:下午8:14
 * Desc:
 */

public class AbsPagerFragment extends HandlerProviderFragment {

    public static final String TAG = AbsPagerFragment.class.getSimpleName();
    protected String mStrData = null;
    protected boolean mIsLoaded = false;
    protected boolean mAutoLoad = false;
    protected String isSupervisor = AbsPagerFragment.TAG;

    protected Subscription mSubscription;

    protected final CompositeSubscription mPendingSubscriptions = new CompositeSubscription();

    private final HashSet<BroadcastReceiver> mLocalBR = new HashSet<>();

    public void setIsLoaded(boolean mIsLoaded) {
        this.mIsLoaded = mIsLoaded;
    }

    public boolean isLoaded() {
        return mIsLoaded;
    }

    /**
     * 可以配合tabInfo使用
     */
    public void setStringData(String data) {
        LogUtil.i(TAG, "setStringData data = " + data);
        this.mStrData = data;
    }

    /**
     * 获取一个recycleView使用的LinearLayoutManager
     */
    protected LinearLayoutManager getLinearLayoutManager() {
        return getLinearLayoutManager(LinearLayoutManager.VERTICAL);
    }

    /**
     * 获取一个recycleView使用的LinearLayoutManager
     */
    protected LinearLayoutManager getLinearLayoutManager(int orientation) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(orientation);
        return layoutManager;
    }

    public String getStringData() {
        return this.mStrData;
    }

    public void loadData(int position) {

    }

    public void setAutoLoad() {
        mAutoLoad = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtil.e("-------------------》" + this.getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(mStrData) && savedInstanceState != null) {
            this.mStrData = savedInstanceState.getString("strData");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("strData", mStrData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mPendingSubscriptions.clear();
        synchronized (mLocalBR) {
            Iterator iterator = mLocalBR.iterator();
            while (iterator.hasNext()) {
                BroadcastReceiver broadcastReceiver = (BroadcastReceiver) iterator.next();
                if (broadcastReceiver != null) {
                    LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
                }
            }
            mLocalBR.clear();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * 初始化标题栏,只有在fragment布局文件包含toolbar时可以调用,可以自定义左侧的返回按钮逻辑
     */
    protected void initializeHeader(View view, String header, View.OnClickListener listener) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setNavigationOnClickListener(v -> getActivity().finish());
        view.findViewById(R.id.left_btn).setVisibility(View.VISIBLE);
        TextView title = (TextView) view.findViewById(R.id.title);
        if (title != null) {
            title.setText(header);
        }
        view.findViewById(R.id.left_btn).setOnClickListener(listener);
    }

    protected void initializeHeader(View view, String header) {
        initializeHeader(view, header, v -> getActivity().onBackPressed());
    }

    protected void initializeHeader(View view, int resId, View.OnClickListener listener) {
        initializeHeader(view, getResources().getString(resId), listener);
    }

    protected void initializeHeader(View view, int resId) {
        initializeHeader(view, getString(resId));
    }

    /**
     * 标题栏右侧文字和点击事件,只有在fragment布局文件包含toolbar时可以调用
     */
    protected void setToolRightText(View view, int resId, View.OnClickListener listener) {
        TextView tv = (TextView) view.findViewById(R.id.right_txt);
        tv.setVisibility(View.VISIBLE);
        tv.setText(resId);
        tv.setOnClickListener(listener);
    }

    /**
     * 标题栏右侧文字颜色,只有在fragment布局文件包含toolbar时可以调用
     */
    protected void setToolRightColor(View view, int resId) {
        TextView tv = (TextView) view.findViewById(R.id.right_txt);
        tv.setTextColor(ActivityCompat.getColor(getActivity(), resId));
    }

    /**
     * 标题栏按钮图标,只有在fragment布局文件包含toolbar时可以调用
     */
    protected void setToolRightImage(View view, int resId, View.OnClickListener listener) {
        ImageView iv = (ImageView) view.findViewById(R.id.right_btn);
        iv.setVisibility(View.VISIBLE);
        iv.setImageResource(resId);
        iv.setOnClickListener(listener);
    }


    /**
     * 设置右侧按钮2的文字
     *
     * @param textResId 文本的资源id
     * @param listener  点击事件
     */
    protected void setToolRightText2(View view, int textResId, View.OnClickListener listener) {
        TextView tv = (TextView) view.findViewById(R.id.right_txt2);
        tv.setVisibility(View.VISIBLE);
        tv.setText(textResId);
        tv.setOnClickListener(listener);
    }

    /**
     * 注册本地广播
     */
    protected final void registerLocalBR(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (broadcastReceiver != null) {
            synchronized (mLocalBR) {
                mLocalBR.add(broadcastReceiver);
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, intentFilter);
            }
        }
    }

//    /**
//     * 页面自定义埋点方法
//     *
//     * @param buriedStr 埋点字符串，定义在BuriedHelper中
//     */
//    protected void sendCustomBuriedEvent(String buriedStr) {
//        BuriedHelper.sendCustomBuriedEvent(getActivity(), new MANHitBuilders.MANCustomHitBuilder(buriedStr));
//    }
}
