package com.zksyp.informationgot.data;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:上午11:46
 * Desc:
 */

public interface ErrorVerify {
    void call(String code, String desc);

    void netError(Throwable throwable);
}
