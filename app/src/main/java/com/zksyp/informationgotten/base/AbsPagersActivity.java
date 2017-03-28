package com.zksyp.informationgotten.base;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;

import com.zksyp.informationgotten.R;
import com.zksyp.informationgotten.bean.TabInfo;
import com.zksyp.informationgotten.util.SizeUtil;
import com.zksyp.informationgotten.view.PagerSlidingTabStrip;
import com.zksyp.informationgotten.view.ViewPagerCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/28
 * Time:上午10:22
 * Desc:
 */

public abstract class AbsPagersActivity extends AbsActivity
        implements ViewPager.OnPageChangeListener {

    private static final String TAG = AbsPagersActivity.class.getSimpleName();

    protected ArrayList<TabInfo> mTabs = new ArrayList<>();
    protected int mCurrentTab = 0;
    public BaseViewPagerAdapter mAdapter;

    private boolean isLoadAlways = false;

    protected ViewPagerCompat mViewPager;
    protected PagerSlidingTabStrip mPagerSlidingTabStrip;

    protected FragmentManager mFragmentManager;

    protected boolean isCreateAddPager = true;

    public ViewPagerCompat getViewPager() {
        return mViewPager;
    }

    private boolean isLoadAlways() {
        return isLoadAlways;
    }


    @Override
    protected void reloadData() {
    }

    public int getCurrentTab() {
        return mCurrentTab;
    }

    public boolean isLoadWithFirstFragment() {
        return false;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_abs_pagers;
    }

    /**
     * for local define
     */
    abstract protected int supplyTabs(List<TabInfo> tabs);

    protected int getCurrentTabIndex() {
        return mViewPager == null ? 0 : mViewPager.getCurrentItem();
    }

    public void setTabInfo(ArrayList<TabInfo> list) {
        this.mTabs = list;
    }

    public List<TabInfo> getTabInfo() {
        return this.mTabs;
    }

    public PagerSlidingTabStrip makeTab(PagerSlidingTabStrip tab) {
        if (tab == null) return null;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        tab.setMinimumWidth(dm.widthPixels);
        tab.setShouldExpand(true);
        tab.setDividerColor(Color.TRANSPARENT); //设置每个标签之间的间隔线颜色 ->透明
        tab.setTabPaddingLeftRight(0);
        tab.setIndicatorColor(ContextCompat.getColor(this, R.color.orange_FF9933));
        tab.setTextSize(SizeUtil.dip2px(15));//设置字体大小
        tab.setIndicatorHeight(SizeUtil.dip2px(2)); //设置Indicator 游标 高度，单位像素
        tab.setUnderlineHeight(0); //设置标签栏下边的间隔线高度，单位像素
        return tab;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isCreateAddPager) {
            initPager();
        }
    }

    protected void initPager() {
        mFragmentManager = getFragmentManager();
        mCurrentTab = supplyTabs(mTabs);
        makeTab(mPagerSlidingTabStrip);
        mAdapter = new BaseViewPagerAdapter(this, mFragmentManager, mTabs);
        mViewPager.setAdapter(mAdapter);
        int size = mTabs.size();
        mViewPager.setOffscreenPageLimit(size < 3 ? 1 : size - 1);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);

        if (isLoadWithFirstFragment()) {
            mPagerSlidingTabStrip.setVisibility(View.GONE);
            mViewPager.setViewTouchMode(true);
        }
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        mViewPager = $(R.id.pager);
        mPagerSlidingTabStrip = $(R.id.tabs);
    }

    public void showTab() {
        mPagerSlidingTabStrip.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentTab = position;
        if (mAdapter != null) {
            AbsFragment fragment = (AbsFragment) mAdapter.instantiateItem(mViewPager, position);
            if (!fragment.isAlways() || !fragment.isLoaded()) {
                fragment.reloadData();
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
