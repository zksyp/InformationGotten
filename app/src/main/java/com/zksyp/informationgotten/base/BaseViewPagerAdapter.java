package com.zksyp.informationgotten.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.view.ViewGroup;

import com.zksyp.informationgotten.bean.TabInfo;
import com.zksyp.informationgotten.util.LogUtil;

import java.util.List;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/28
 * Time:上午10:23
 * Desc:
 */

public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = BaseViewPagerAdapter.class.getSimpleName();

    private List<TabInfo> mList = null;
    private Context mContext = null;


    public BaseViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseViewPagerAdapter(Context context, FragmentManager fm, List<TabInfo> mList) {
        super(fm);
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList == null ? "" : mList.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        AbsPagerFragment fragment = mList.get(position).newInstance();
        if (position == 0 && fragment != null) {
            fragment.setAutoLoad();
        }
        LogUtil.v(TAG, "getItem position = " + position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
