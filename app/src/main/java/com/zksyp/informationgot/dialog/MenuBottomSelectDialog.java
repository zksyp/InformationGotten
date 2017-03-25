package com.zksyp.informationgot.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zksyp.informationgot.base.AbsDialog;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/21
 * Time:上午10:39
 * Desc:
 */

public class MenuBottomSelectDialog extends AbsDialog {
    public MenuBottomSelectDialog(@NonNull Context context) {
        super(context);
    }

//    private List<AbsActivity.MenuInfo> mMenuInfos;
//    private MenuAdapter adapter;
//    private String mTitle = "";
//    private TextView mTitleText;
//
//    public MenuBottomSelectDialog(
//            Context context, List<AbsActivity.MenuInfo> menuInfos) {
//        super(context, R.style.Dialog_Tip);
//        mMenuInfos = menuInfos;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog_bottom_select);
//        mTitleText = $(R.id.tv_title_select);
//        mTitleText.setText("更多操作");
//        RecyclerView recyclerView = $(R.id.rv_bottom_select);
//        $(R.id.tv_bottom_select_cancel).setOnClickListener(view -> cancel());
//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        adapter = new MenuAdapter(getContext());
//        adapter.addData(mMenuInfos);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setNestedScrollingEnabled(false);
//        configBottomDialog(this);
//    }
//
//    public interface OnClickListener {
//        void onItemClick(int pos, String text);
//    }
//
//    public void setTitle(String title) {
//        mTitle = title;
//        if (mTitleText != null) {
//            mTitleText.setText(mTitle);
//        }
//    }
//
//    public void setMenuInfos(List<AbsActivity.MenuInfo> menuInfos) {
//        mMenuInfos = menuInfos;
//        if (adapter != null) {
//            adapter.clear();
//            adapter.addData(menuInfos);
//        }
//    }
//
//    private class MenuAdapter extends BaseRecyclerAdapter<AbsActivity.MenuInfo, MenuViewHolder> {
//
//        MenuAdapter(Context context) {
//            super(context);
//        }
//
//        @Override
//        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            return new MenuViewHolder(parent, R.layout.item_bottom_select);
//        }
//
//        @Override
//        public void onBindViewHolder(MenuViewHolder holder, int position) {
//            AbsActivity.MenuInfo menuInfo = getItem(position);
//            if (menuInfo != null) {
//                if (menuInfo.textResId > 0) {
//                    holder.mTextView.setText(menuInfo.textResId);
//                } else {
//                    holder.mTextView.setText(menuInfo.textStr);
//                }
//                holder.itemView.setOnClickListener(v -> {
//                    getItem(position).listener.onClick(v);
//                    MenuBottomSelectDialog.this.cancel();
//                });
//            }
//        }
//    }
//
//    static class MenuViewHolder extends RecyclerView.ViewHolder {
//
//        TextView mTextView;
//
//        MenuViewHolder(ViewGroup parent, @LayoutRes int res) {
//            super(parent, res);
//            mTextView = (TextView) $(R.id.tv_bottom_select_content);
//        }
//    }
//
//    private void configBottomDialog(Dialog dialog) {
//        Window dialogWindow = dialog.getWindow();
//        if (dialogWindow != null) {
//            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            dialogWindow.setGravity(Gravity.BOTTOM);
//            dialogWindow.setWindowAnimations(R.style.anim_slide_share_from_bottom);
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            dialog.setCancelable(true);
//        }
//    }
}
