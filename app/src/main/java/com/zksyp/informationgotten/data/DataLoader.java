package com.zksyp.informationgotten.data;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:46
 * Desc:
 */

public class DataLoader {

    public static LoaderService getInstance() {
        return DataServiceProvider.getInstance().provide(LoaderService.class);
    }
}
