package com.zksyp.informationgot.data;

import com.zksyp.informationgot.R;
import com.zksyp.informationgot.util.AlertToast;

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
