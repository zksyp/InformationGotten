package com.zksyp.informationgot.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zksyp.informationgot.R;
import com.zksyp.informationgot.util.ActivityUtil;
import com.zksyp.informationgot.util.EventUtil;
import com.zksyp.informationgot.util.LogUtil;
import com.zksyp.informationgot.util.SizeUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/20
 * Time:下午4:20
 * Desc:Activity基类
 */

public class BaseFragmentActivity extends HandlerProviderActivity {

    public static final String INTENT_KEY_TO_ACTIVITY = "TO_ACTIVITY";

    public boolean mIsHidden;

    protected Subscription mSubscription;

    protected final CompositeSubscription mPendingSubscriptions = new CompositeSubscription();

    @SuppressWarnings("unchecked")
    public <T extends View> T $$(@LayoutRes int id) {
        return (T) getLayoutInflater().inflate(id, null);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    /**
     * 隐藏Header
     */
    protected void hideHeader() {
        $(R.id.left_btn).setVisibility(View.GONE);
        $(R.id.title).setVisibility(View.GONE);
        $(R.id.right_txt).setVisibility(View.GONE);
    }

    /**
     * 隐藏Header
     */
    protected void hideToolbar() {
        $(R.id.toolbar).setVisibility(View.GONE);
    }

    /**
     * 初始化标题栏
     *
     * @param header 标题文字
     */
    protected void initializeHeader(String header) {
        initializeHeader(header,
                v -> BaseFragmentActivity.this.onBackPressed());
    }

    /**
     * 初始化标题栏
     *
     * @param header   标题文字
     * @param listener 左侧图标返回事件
     */
    protected void initializeHeader(String header, View.OnClickListener listener) {
        Toolbar toolbar = $(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setNavigationOnClickListener(v -> this.finish());
        $(R.id.left_btn).setVisibility(View.VISIBLE);
        $(R.id.left_btn).setOnClickListener(listener);
        TextView title = $(R.id.title);
        if (title != null) {
            title.setText(header);
        }
    }

    protected void initializeHeader(int resId) {
        initializeHeader(getString(resId));
    }

    protected void initializeHeader(int resId, View.OnClickListener listener) {
        initializeHeader(getString(resId), listener);
    }

    /**
     * 初始化标题栏,主要用于深色标题栏设置
     *
     * @param header     标题文字
     * @param backGround 标题栏背景颜色
     * @param liftImage  左侧图标
     * @param titleColor 标题颜色
     */
    protected void initializeHeader(
            String header, int backGround, int liftImage, int titleColor) {
        initializeHeader(header, backGround, liftImage, titleColor,
                v -> BaseFragmentActivity.this.onBackPressed());
    }

    /**
     * 初始化标题栏,主要用于深色标题栏设置
     *
     * @param header     标题文字
     * @param backGround 标题栏背景颜色
     * @param liftImage  左侧图标
     * @param titleColor 标题颜色
     * @param listener   左侧图标的点击事件
     */
    protected void initializeHeader(
            String header, int backGround, int liftImage, int titleColor, View.OnClickListener listener) {
        Toolbar toolbar = $(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setNavigationOnClickListener(v -> this.finish());
        $(R.id.left_btn).setVisibility(View.VISIBLE);
        $(R.id.left_btn).setOnClickListener(listener);
        ((ImageView) $(R.id.left_btn)).setImageResource(liftImage);
        TextView title = $(R.id.title);
        if (title != null) {
            title.setText(header);
            if (titleColor != 0) {
                title.setTextColor(titleColor);
            }
        }
        toolbar.setBackgroundResource(backGround);
    }

    /**
     * 设置右侧按钮(文字)(不需要点击事件)
     */
    protected void setToolRightText(int resId) {
        TextView tv = $(R.id.right_txt);
        tv.setVisibility(View.VISIBLE);
        tv.setText(resId);
    }

    /**
     * 设置右侧按钮(文字)
     */
    protected void setToolRightText(String text, View.OnClickListener listener) {
        TextView tv = $(R.id.right_txt);
        tv.setVisibility(View.VISIBLE);
        tv.setText(text);
        tv.setOnClickListener(listener);
    }

    /**
     * 设置右侧按钮(文字)
     */
    protected void setToolRightText(int resId, View.OnClickListener listener) {
        setToolRightText(getResources().getString(resId), listener);
    }

    /**
     * 设置右侧文字按钮2的文字(添加点击事件)
     */
    protected void setToolRightText2(int textResId, View.OnClickListener textListener) {
        TextView tv = $(R.id.right_txt2);
        tv.setVisibility(View.VISIBLE);
        tv.setText(textResId);
        tv.setOnClickListener(textListener);
    }

    /**
     * 设置右侧文字按钮2的文字(添加点击事件)
     */
    protected void setToolRightText2(String str, View.OnClickListener textListener) {
        TextView tv = $(R.id.right_txt2);
        tv.setVisibility(View.VISIBLE);
        tv.setText(str);
        tv.setOnClickListener(textListener);
    }

    /**
     * 设置右侧按钮文字颜色
     */
    protected void setToolRightColor(int resId) {
        TextView tv = $(R.id.right_txt);
        tv.setTextColor(ActivityCompat.getColor(this, resId));
    }

    /**
     * 设置title文字颜色
     */
    protected void setToolTitleColor(int resId) {
        TextView tv = $(R.id.title);
        tv.setTextColor(ActivityCompat.getColor(this, resId));
    }

    /**
     * 设置搜索栏文字
     */
    protected void setToolSearchBtn(int resId, View.OnClickListener listener) {
//        TextView tv = $(R.id.search_btn);
//        tv.setVisibility(View.VISIBLE);
//        tv.setText(resId);
//        tv.setOnClickListener(listener);
    }

    /**
     * 隐藏右侧按钮
     */
    protected void clearToolRight() {
        $(R.id.right_txt).setVisibility(View.GONE);
        $(R.id.right_btn).setVisibility(View.GONE);
    }

    /**
     * 设置标题栏颜色
     */
    protected void setToolBackground(int resId) {
        $(R.id.toolbar).setBackgroundColor(ActivityCompat.getColor(this, resId));
    }

    /**
     * 设置标题栏右侧按钮图标,但无点击事件
     */
    protected void setToolRightImage(int resId) {
        ImageView iv = $(R.id.right_btn);
        iv.setVisibility(View.VISIBLE);
        iv.setImageResource(resId);
    }


    protected void setToolRightImage(int resId, View.OnClickListener listener) {
        ImageView iv = $(R.id.right_btn);
        iv.setVisibility(View.VISIBLE);
        iv.setImageResource(resId);
        iv.setOnClickListener(listener);
    }

    protected void hideToolRightImage() {
        ImageView iv = $(R.id.right_btn);
        iv.setVisibility(View.GONE);
    }

    protected void setToolRightImage2(int resId) {
        ImageView iv = $(R.id.right_btn2);
        iv.setVisibility(View.VISIBLE);
        iv.setImageResource(resId);
    }

    protected void setToolRightImage2(int resId, View.OnClickListener listener) {
        ImageView iv = $(R.id.right_btn2);
        iv.setVisibility(View.VISIBLE);
        iv.setImageResource(resId);
        iv.setOnClickListener(listener);
    }

//    /**
//     * 为搜索栏与清除按钮设置关联事件
//     */
//    public void initToolBarSearch(EditText searchEditText, ImageView cleanEditBtn) {
//        ViewUtil.setDeleteBtn(this, searchEditText, cleanEditBtn);
//    }


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("-------------------》" + this.getClass().getSimpleName());
        ActivityUtil.getActivityList().add(this);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // 这里有一篇http://www.jianshu.com/p/662c46cd3b5f文章,可以从Fragment的角度解决重启Activity导致的Fragment重叠问题
        if (savedInstanceState != null) {
            FragmentManager fragmentManager = getFragmentManager();
            try {
                Class<?> clazz = null;
                clazz = Class.forName("android.app.FragmentManagerImpl");
                Field activeField = null;
                activeField = clazz.getDeclaredField("mActive");
                activeField.setAccessible(true);
                Field addField = clazz.getDeclaredField("mAdded");
                addField.setAccessible(true);

                ArrayList<Fragment> mActive = (ArrayList<Fragment>) activeField.get(fragmentManager);
                ArrayList<Fragment> mAdded = (ArrayList<Fragment>) addField.get(fragmentManager);
                if (mActive != null && mActive.size() > 0) {
                    for (int i = 0, s = mActive.size(); i < s; i++) {
                        Fragment fragment = mActive.get(i);
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                }
                if (mAdded != null && mAdded.size() > 0) {
                    for (int i = 0, s = mAdded.size(); i < s; i++) {
                        Fragment fragment = mAdded.get(i);
                        fragmentManager.beginTransaction().remove(fragment).commit();
                    }
                }
                fragmentManager.executePendingTransactions();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * activity的初始化工作
     */
    protected void afterCreate(Bundle savedInstanceState) {
    }

    /**
     * 获取一个recycleView使用的竖直排列LinearLayoutManager
     */
    protected LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    /**
     * 获取一个recycleView使用的竖直排列GridLayoutManager
     */
    protected GridLayoutManager getGridLayoutManager(int spanCount) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //点击键盘外使键盘消失
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        }
        return EventUtil.isAllowDispatchEvent(ev) && super.dispatchTouchEvent(ev);
    }

    /**
     * 点击外部隐藏键盘必要的点击判断
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {

        if (v != null && (v instanceof EditText)) {
            int[] point = {0, 0};
            v.getLocationOnScreen(point);
            int left = point[0], top = point[1],
                    bottom = top + v.getHeight(), right = left + v.getWidth();
            //不判断右边界可以解决点击删除按钮宽度导致键盘隐藏，但会导致短的editText点击右边区域不会隐藏软键盘（可以接受）
            return !(event.getRawX() > left
                    && event.getRawY() > top
                    && event.getRawY() < right + SizeUtil.dip2px(15f)
                    && event.getRawY() < bottom);
        }
        //如果焦点不是EditText则忽略
        return false;
    }

    @Override
    public void onBackPressed() {
        if (checkBackToMain() || onBackPressedOverride()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 子类处理返回逻辑的时候，最好不要重写onBackPressed。替代的重写该方法。这样就不会导致checkBackToMain()无法调用。
     * 但是子类点击back的时候如果不返回，则可以重写onBackPressed。
     *
     * @return true:onBackPressed被占用。false：不占用onBackPressed。
     */
    protected boolean onBackPressedOverride() {
        return false;
    }

    /**
     * 检查是否需要返回到首页
     *
     * @return true
     */
    protected boolean checkBackToMain() {
        if (ActivityUtil.isTheOnlyActivity() && backToMainOnSingle()) {
//            try {
//                ModuleServiceManager.getPlatformModule().pushToMain(this);
//            } catch (ModuleNotAssembledException e) {
//                e.printStackTrace();
//            }
            finish();
            return true;
        }
        return false;
    }

    /**
     * 当前页面为独立页面的时候，是否要返回到主页面
     */
    public boolean backToMainOnSingle() {
        return true;
    }

    /**
     * 向队列中添加一个Subscription
     */
    public void pend(Subscription subscription) {
        if (subscription != null) {
            mPendingSubscriptions.add(subscription);
        }
    }

    /**
     * 只能注册一个的subscription
     */
    public void singlePend(Subscription subscription) {
        if (subscription != null) {
            if (mSubscription != null) {
                mSubscription.unsubscribe();
            }
            mSubscription = subscription;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsHidden = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mPendingSubscriptions.clear();

        if (ActivityUtil.getActivityList().contains(this)) {
            ActivityUtil.getActivityList().remove(this);
        }
    }
}
