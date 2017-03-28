package com.zksyp.informationgotten.data;

import rx.Subscription;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:53
 * Desc:
 */

public class EmptySubscription implements Subscription {
    @Override
    public void unsubscribe() {

    }

    @Override
    public boolean isUnsubscribed() {
        return false;
    }
}
