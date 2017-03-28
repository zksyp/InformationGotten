package com.zksyp.informationgotten.data;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:53
 * Desc:
 */

public class EmptyErrorVerify implements ErrorVerify {
    @Override
    public void call(String code, String desc) {
    }

    @Override
    public void netError(Throwable throwable) {
    }
}
