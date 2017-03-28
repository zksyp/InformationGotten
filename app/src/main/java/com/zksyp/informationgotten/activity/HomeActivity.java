package com.zksyp.informationgotten.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.zksyp.informationgotten.R;
import com.zksyp.informationgotten.base.AbsActivity;
import com.zksyp.informationgotten.view.PagerSlidingTabStrip;
import com.zksyp.informationgotten.view.ViewPagerCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AbsActivity {

    public static final int TAB_IN_THEATERS = 0;
    public static final int TAB_COMING_SOON = 1;
    public static final int TAB_TOP_250 = 2;
    public static final int TAB_WEEKLY = 3;
    public static final int TAB_US_BOX = 4;
    public static final int TAB_NEW_MOVIES = 5;


    @BindView(R.id.home_info_tab_view)
    PagerSlidingTabStrip mTabView;

    @BindView(R.id.home_view_pager)
    ViewPagerCompat mViewPager;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawerLayout;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    private static final CharSequence[] myTabTitles = new CharSequence[]{
            "正在热映",
            "即将上映",
            "Top250",
            "口碑榜",
            "北美票房榜",
            "新片榜"
    };

    private int mCurrentTabId = 0;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        super.afterCreate(savedInstanceState);
        ButterKnife.bind(this);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initializeHeader("豆瓣电影", R.color.green_63DEC9, R.drawable.ic_more_tool, R.color.white
                , v -> {
                    if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                        mDrawerLayout.closeDrawer(Gravity.START);
                        setToolLeftImage(R.drawable.ic_more_tool);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.START);
                        setToolLeftImage(R.drawable.topbar_icon_back);
                    }
                });
        initView();
        initData();

    }

    private void initView() {

    }

    private void initData() {

    }
}
