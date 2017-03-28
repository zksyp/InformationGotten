package com.zksyp.informationgotten.data;

import com.zksyp.informationgotten.R;
import com.zksyp.informationgotten.util.AlertToast;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:51
 * Desc:
 */

public class SimpleErrorVerify implements ErrorVerify {
    @Override
    public void call(String code, String desc) {
        AlertToast.show(desc);
    }

    @Override
    public void netError(Throwable throwable) {
        AlertToast.show(R.string.warn_net_error);
    }
}
