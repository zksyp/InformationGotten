package com.zksyp.informationgotten.data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:39
 * Desc:获取请求
 */

public class AsyncOkHttpClient {
    public static final String TAG = AsyncOkHttpClient.class.getSimpleName();

    private static final int TIMEOUT = 20_000;

    private static OkHttpClient SINGLETON;

    private AsyncOkHttpClient() {
    }

    public static OkHttpClient getClient() {
        if (SINGLETON == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            SINGLETON = new OkHttpClient
                    .Builder()
//                    .certificatePinner(getCertificate())
                    //日志打印拦截工具
                    .addInterceptor(httpLoggingInterceptor)
//                    .addInterceptor(new NetworkErrorInterceptor())
                    //超时设置
                    .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .build();
            SINGLETON.dispatcher().setMaxRequestsPerHost(20);
        }
        return SINGLETON;
    }

//    public static CertificatePinner getCertificate() {
//        // 证书绑定
//        CertificatePinner certificatePinner;
//        if (!HttpUtil.isTestHttp) {
//            certificatePinner = CertificatePinner.DEFAULT;
//        } else {
//            certificatePinner = new CertificatePinner.Builder()
//                    .add("b.maihaoche.com", "sha256/rgzUC/YRcpxI4EPw0akTuVGsoJgGbKIWCVmhLIHguus=")
//                    .add("b.maihaoche.com", "sha256/v6YI7/qsxNtsyNEl+PXg4HLO6jpFydw52mHy8nvKceg=")
//                    .add("b.maihaoche.com", "sha256/2xXABitSDzGKGdrP7NZPnno/vmCf1YZ5byCuAo6OMFg=")
//                    .build();
//        }
//        return certificatePinner;
//    }


}
