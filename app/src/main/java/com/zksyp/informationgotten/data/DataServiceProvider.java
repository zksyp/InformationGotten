package com.zksyp.informationgotten.data;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:28
 * Desc:
 */

public class DataServiceProvider {
    private Retrofit retrofit;

    private static DataServiceProvider provider;

    public static DataServiceProvider getInstance() {
        if (provider == null) {
            synchronized (DataServiceProvider.class) {
                if (provider == null) {
                    provider = new DataServiceProvider();
                }
            }
        }
        return provider;
    }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.douban.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(RsaGsonConverterFactory.create())
                    .client(AsyncOkHttpClient.getClient())
                    .build();
        }
        return retrofit;
    }
    private HashMap<String, Object> dataServiceMap;

    /**
     * 切换环境的时候,重新初始化retrofit
     */
    public DataServiceProvider reloadService(String address) {
        if(dataServiceMap== null){
            dataServiceMap = new HashMap<>();
        }
        dataServiceMap.clear();
        retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(RsaGsonConverterFactory.create())
                .client(AsyncOkHttpClient.getClient())
                .build();
        return this;
    }


    public <T> T provide(Class<T> tClass) {
        if (dataServiceMap == null) {
            dataServiceMap = new HashMap<>();
        }
        if (!dataServiceMap.containsKey(tClass.getName())
                || dataServiceMap.get(tClass.getName()) == null) {
            dataServiceMap.put(tClass.getName(), getRetrofit().create(tClass));
        }
        return (T) dataServiceMap.get(tClass.getName());
    }

}
