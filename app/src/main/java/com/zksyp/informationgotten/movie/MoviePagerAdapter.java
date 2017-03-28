package com.zksyp.informationgotten.movie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zksyp.informationgotten.view.PagerSlidingTabStrip;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/27
 * Time:下午7:58
 * Desc:
 */

public class MoviePagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.ExtraInfoProvider {


    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getExtraInfoProvider(int position) {
        return null;
    }
}
