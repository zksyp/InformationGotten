package com.zksyp.informationgotten.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zksyp.informationgotten.R;

import butterknife.ButterKnife;


/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/27
 * Time:下午8:01
 * Desc:
 */

public abstract class AbsFragment extends AbsPagerFragment {

    protected ViewGroup contentView;
    protected ViewGroup mProgressView;
    protected ViewGroup mEmptyView;
    protected ViewGroup mErrorView;
    protected TextView mReloadNote;

    private int mPageIndex;

    /**
     * 要实现的view
     */
    protected ViewGroup mMainView;


    public int getPageIndex() {
        return mPageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.mPageIndex = pageIndex;
    }

    /**
     * 是否需要控制
     */
    public boolean isAlways() {
        return isAlways;
    }

    public void setAlways(boolean always) {
        isAlways = always;
    }

    //是否始终刷新页面，在onResumes时刷新,一般用在page
    private boolean isAlways = false;

    /**
     * 刷新数据
     * 错误页面重加载,可以下拉刷新时调用
     * 同时设置isAlways为true时,会在onResume()方法中调用
     */
    abstract public void reloadData();

    /**
     * ViewPager切换时会调用此方法,会在BasePagersFragmentActivity中调用
     * 而在AbsFragment中不会调用此方法而是会通过判断isAlways和isLoad判断是都调用{@link #reloadData()}
     * 具体逻辑可以看两个类{@link AbsPagersActivity}和{@link BasePagerFragmentActivity}
     */
    @Override
    public final void loadData(int position) {

    }

    protected abstract int getContentViewId();

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof AbsPagersActivity) {
            if (isAlways && ((AbsPagersActivity) getActivity()).getCurrentTab() == getPageIndex())
                reloadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = $$(R.layout.fragment_abs);
        mProgressView = $(contentView, R.id.progress_view);
        mEmptyView = $(contentView, R.id.empty_view);
        mErrorView = $(contentView, R.id.error_view);
        mReloadNote = $(contentView, R.id.error_load_note);
        $(R.id.reload).setOnClickListener(view -> reloadData());
//        View netWork = $(R.id.network);
//        netWork.setVisibility(View.VISIBLE);
//        netWork.setOnClickListener(view -> {
//            try {
//                ModuleServiceManager.getPlatformModule().checkNetwork(getActivity());
//            } catch (ModuleNotAssembledException e) {
//                e.printStackTrace();
//                AlertToast.show(e.getModuleNoExistStr());
//            }
//        });
        mMainView = $$(getContentViewId());
        contentView.addView(mMainView);
        ButterKnife.bind(this, contentView);
        afterCreate();
        return contentView;
    }

    /**
     * fragment的初始化工作
     */
    protected void afterCreate() {

    }

    private void hideAll() {
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        if (mMainView != null)
            mMainView.setVisibility(View.GONE);
    }

    public void setEmptyView(View emptyView) {
        mEmptyView.removeAllViews();
        mEmptyView.addView(emptyView);
    }

    public void setEmptyIcon(int resId) {
        ImageView mIcon = $(mEmptyView, R.id.empty_view_icon);
        mIcon.setImageResource(resId);
    }

    public void setEmptyText(int resId) {
        TextView mText = $(mEmptyView, R.id.empty_view_text);
        mText.setText(resId);
    }

    public void setEmptyDesc(int resId) {
        TextView mText = $(mEmptyView, R.id.empty_view_desc);
        mText.setVisibility(View.VISIBLE);
        mText.setText(resId);
    }

    public void setEmptyContent(int resId) {
        TextView mText = $(mEmptyView, R.id.empty_view_content);
        mText.setVisibility(View.VISIBLE);
        mText.setText(resId);
    }

    /**
     * 空页面添加按钮及点击事件
     */
    public void setEmptyBtn(int resId, View.OnClickListener listener) {
        TextView mBtn = $(mEmptyView, R.id.empty_view_btn);
        mBtn.setVisibility(View.VISIBLE);
        mBtn.setText(resId);
        mBtn.setOnClickListener(listener);
    }

    public void showEmptyView() {
        hideAll();
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public void showNetErrorView() {
        showNetErrorView(-1);
    }

    /**
     * 显示网络请求失败的信息。并设置上面的margin
     *
     * @param marginTopPix
     */
    public void showNetErrorView(int marginTopPix) {
        hideAll();
        if (marginTopPix >= 0) {
            ViewGroup.LayoutParams layoutParams = mReloadNote.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) layoutParams).topMargin = marginTopPix;
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).topMargin = marginTopPix;
            }
            mReloadNote.setLayoutParams(layoutParams);
        }
        mReloadNote.setText(R.string.common_net_error);
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showServerErrorView() {
        showServerErrorView(null, -1);
    }


    public void showServerErrorView(String text) {
        showServerErrorView(text, -1);
    }

    public void showServerErrorView(int marginTopPix) {
        showServerErrorView(null, marginTopPix);
    }

    public void showServerErrorView(String text, int marginTopPix) {
        hideAll();
        if (marginTopPix >= 0) {
            ViewGroup.LayoutParams layoutParams = mReloadNote.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) layoutParams).topMargin = marginTopPix;
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).topMargin = marginTopPix;
            }
            mReloadNote.setLayoutParams(layoutParams);
        }
        if (!TextUtils.isEmpty(text)) {
            mReloadNote.setText(text);
        } else {
            mReloadNote.setText(R.string.common_server_error);
        }
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showProgressView() {
        hideAll();
        if (mProgressView != null) {
            mProgressView.setVisibility(View.VISIBLE);
        }
    }

    public void showProgressViewNoHintAnything() {
        mProgressView.bringToFront();
        mProgressView.setVisibility(View.VISIBLE);
    }

    public void hideProgressView() {
        mProgressView.setVisibility(View.GONE);
    }

    public void showContentView() {
        hideAll();
        if (mMainView != null) {
            mMainView.setVisibility(View.VISIBLE);
        }
        showPagerTab();
    }

    protected void showPagerTab() {
        if (getActivity() instanceof AbsPagersActivity) {
            AbsPagersActivity activity = (AbsPagersActivity) getActivity();
            if (activity.isLoadWithFirstFragment()) {
                activity.showTab();
                activity.getViewPager().setViewTouchMode(false);
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $$(int id) {
        return (T) getActivity().getLayoutInflater().inflate(id, null);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {
        return (T) contentView.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(View view, int id) {
        return (T) view.findViewById(id);
    }
}
