package com.zksyp.informationgotten.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zksyp.informationgotten.R;
import com.zksyp.informationgotten.data.ErrorVerify;
import com.zksyp.informationgotten.util.AlertToast;
import com.zksyp.informationgotten.util.LogUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/21
 * Time:上午10:36
 * Desc:新基类activity
 */

public abstract class AbsActivity extends BaseFragmentActivity {
    protected ViewGroup mProgressView;
    protected ViewGroup mEmptyView;
    protected ViewGroup mErrorView;
    protected TextView mReloadNote;
    protected ViewGroup mMainView;


    protected void reloadData() {

    }


    /**
     * 布局文件xml的resId,无需添加标题栏、加载、错误及空页面
     */
    @LayoutRes
    protected abstract int getContentViewId();

    /**
     * 隐藏除标题栏的所有界面,辅助显示各个页面的
     */
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
        if (mMainView != null) {
            mMainView.setVisibility(View.GONE);
        }
    }

//    public MenuBottomSelectDialog mMenuDialog;

    /**
     * 展示标题栏中隐藏的菜单
     *
     * @param list 菜单列表
     */
    public void showMenuDialog(List<MenuInfo> list) {
//        showMenuDialog(list, null);
    }

//    public void showMenuDialog(List<MenuInfo> list, String title) {
//        mMenuDialog = new MenuBottomSelectDialog(this, list);
//        if (!TextUtils.isEmpty(title)) {
//            mMenuDialog.setTitle(title);
//        }
//        mMenuDialog.show();
//    }

//    /**
//     * 隐藏菜单
//     */
//    public void hideMenuDialog() {
//        if (mMenuDialog != null && mMenuDialog.isShowing()) {
//            mMenuDialog.dismiss();
//        }
//    }

    /**
     * 菜单类,包含描述和点击事件
     */
    public static class MenuInfo {
        public View.OnClickListener listener;
        public int textResId;
        public String textStr = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);
        if (getContentViewId() != 0) {
            mMainView = $$(getContentViewId());
            ViewGroup viewGroup = $(R.id.content_view);
            viewGroup.addView(mMainView, 2);
        }
        mProgressView = $(R.id.progress_view);
        mEmptyView = $(R.id.empty_view);
        mErrorView = $(R.id.error_view);
        mReloadNote = $(R.id.error_load_note);
        $(R.id.reload).setOnClickListener(view -> reloadData());
        ButterKnife.bind(this);
        binding();
        afterCreate(savedInstanceState);
    }

    /**
     * 绑定view 的方法
     */
    protected void binding() {

    }

    /**
     * activity的初始化工作
     */
    protected void afterCreate(Bundle savedInstanceState) {
    }


//    /**
//     * 设置空页面的图标
//     */
//    protected void setEmptyIcon(int resId) {
//        ImageView mIcon = $(mEmptyView, R.id.empty_view_icon);
//        mIcon.setImageResource(resId);
//    }
//
//    /**
//     * 设置空页面的提示文案,类似标题
//     */
//    protected TextView setEmptyText(int resId) {
//        TextView mText = $(mEmptyView, R.id.empty_view_text);
//        mText.setText(resId);
//        return mText;
//    }
//
//    /**
//     * 设置空页面的描述性文案
//     */
//    protected TextView setEmptyDesc(int resId) {
//        TextView mText = $(mEmptyView, R.id.empty_view_desc);
//        mText.setVisibility(View.VISIBLE);
//        mText.setText(resId);
//        return mText;
//    }
//
//    /**
//     * 设置空页面的辅助性文案,在最下行
//     */
//    protected TextView setEmptyContent(int resId) {
//        TextView mText = $(mEmptyView, R.id.empty_view_content);
//        mText.setVisibility(View.VISIBLE);
//        mText.setText(resId);
//        return mText;
//    }
//
//    /**
//     * 设置空页面最底下文字
//     *
//     * @param resId
//     * @return
//     */
//    protected TextView setEmptyBottomContent(int resId) {
//        TextView mText = $(mEmptyView, R.id.tv_bottom);
//        mText.setVisibility(View.VISIBLE);
//        mText.setText(resId);
//        return mText;
//    }
//
//    /**
//     * 设置空页面的下按钮
//     */
//    protected void setEmptyBtn(int resId, View.OnClickListener listener) {
//        TextView mText = $(mEmptyView, R.id.empty_view_btn);
//        mText.setVisibility(View.VISIBLE);
//        mText.setText(resId);
//        mText.setOnClickListener(listener);
//    }
//
//    /**
//     * 展示空页面
//     */
//    protected void showEmptyView() {
//        hideAll();
//        mEmptyView.setVisibility(View.VISIBLE);
//    }
//
//    /**
//     * 展示网络错误页面
//     */
//    public void showNetErrorView() {
//        hideAll();
//        mReloadNote.setText(R.string.common_net_error);
//        mErrorView.setVisibility(View.VISIBLE);
//    }
//
//    /**
//     * 展示服务器错误页面,一般和ErrorVerify一起使用
//     */
//    public void showServerErrorView() {
//        hideAll();
//        mReloadNote.setText(R.string.common_server_error);
//        mErrorView.setVisibility(View.VISIBLE);
//    }

//    /**
//     * 展示服务器错误页面,包括返回的错误信息
//     */
//    public void showServerErrorView(String text) {
//        if (StringUtil.isEmpty(text)) {
//            showServerErrorView();
//            return;
//        }
//        hideAll();
//        mReloadNote.setText(text);
//        mErrorView.setVisibility(View.VISIBLE);
//    }

    protected void showServerErrorView(int resId) {
        hideAll();
        mReloadNote.setText(resId);
        mErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 展示加载齿轮页面
     */
    protected void showProgressView() {
        hideAll();
        mProgressView.setVisibility(View.VISIBLE);
    }

    /**
     * 展示加载界面,但不隐藏其他界面
     */
    public void showProgressViewNoHindAnything() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载界面,只能配合{@link #showProgressViewNoHindAnything()}方法使用
     */
    public void hideProgressView() {
        if (mProgressView != null) mProgressView.setVisibility(View.GONE);
    }

    /**
     * 展示内容页面
     */
    protected void showContentView() {
        hideAll();
        if (mMainView != null)
            mMainView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 返回普通的错误处理：网络连接失败则showNetErrorView。如果有错误描述，则弱提示；否则显示服务器异常。
     */
    protected ErrorVerify getNormalErrorVerify() {
        return new ErrorVerify() {
            @Override
            public void call(String code, String desc) {
                if (TextUtils.isEmpty(desc)) {
//                    showServerErrorView();
                } else {
                    AlertToast.show(desc);
                }
            }

            @Override
            public void netError(Throwable throwable) {
                LogUtil.e(throwable.toString());
//                showNetErrorView();
            }
        };
    }
}
