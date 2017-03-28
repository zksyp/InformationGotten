package com.zksyp.informationgotten.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/21
 * Time:上午10:39
 * Desc:
 */

public class AbsDialog extends Dialog {

    public AbsDialog(@NonNull Context context) {
        super(context);
    }

    public AbsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected AbsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $$(int id) {
        return (T) getLayoutInflater().inflate(id, null);
    }

    public <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    public <T extends View> T $(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

}
