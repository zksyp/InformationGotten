package com.zksyp.informationgot.data;

import android.content.Context;
import android.util.Log;

import com.zksyp.informationgot.bean.response.BaseResponse;
import com.zksyp.informationgot.util.AlertToast;
import com.zksyp.informationgot.util.LogUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:51
 * Desc:Retrofit合并RxJava的下载帮助类
 */

public class RxTransformerHelper {
    /**
     * 简单的请求业务错误过滤器（弹出toast）
     */
    public static Func1<BaseResponse, Boolean> verifySimpleBusiness() {
        return verifyBusiness(new SimpleErrorVerify());
    }

    /**
     * 业务错误过滤器（自定义）
     */
    public static <T> Func1<T, Boolean> verifyBusiness(ErrorVerify errorVerify) {
        return response -> {
            if (response instanceof BaseResponse) {
                BaseResponse baseResponse = (BaseResponse) response;
                ResponseCode responseCode = ResponseCode.toEnum(baseResponse.mResultCode+"");
                boolean isSuccess = responseCode == ResponseCode.RESULT_SUC;
                if (!isSuccess) {
                    if (errorVerify != null) {
                        LogUtil.e("返回错误码：" + baseResponse.mResultCode + "\t\t\t错误信息：" + baseResponse.mErrDesc);
                        errorVerify.call(baseResponse.mResultCode+"", baseResponse.mErrDesc);
                    }
                }
                return isSuccess;
            } else {
                return false;
            }
        };
    }

    /**
     * 非空过滤器（自定义）
     */
    public static <T> Func1<T, Boolean> verifyNotEmpty() {
        return response -> response != null;
    }

    /**
     * sessionId 过期的过滤器
     */
    public static <T> Func1<T, Boolean> verify(Context context) {
        return response -> {
            if (response instanceof BaseResponse) {
                BaseResponse baseResponse = (BaseResponse) response;
                ResponseCode responseCode = ResponseCode.toEnum(baseResponse.mResultCode);
                if (responseCode.isToLogin()) {
                    if (responseCode.isAccountError()) {
                        AlertToast.show("账号异常，请拨打客服热线:4000390717");
                    }
                    LogUtil.e("sessionId过期");
//                    try {
//                        if (context != null && (context instanceof Activity)) {
//                            ModuleServiceManager.getPlatformModule().toLogin(context);
//                        } else {
//                            ModuleServiceManager.getPlatformModule().toLogin(BaseApplication.getApp());
//                        }
//                    } catch (ModuleNotAssembledException e) {
//                        e.printStackTrace();
//                    }
//                    PushHelper.unbindAccount();
                    return false;
                }
                return true;
            } else {
                return false;
            }
        };
    }


    /**
     * 优先使用这个，可以继续使用操作符
     */
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 聚合了session过滤器及合并操作
     */
    public static Observable.Transformer<BaseResponse, BaseResponse>
    applySchedulersAndSessionFilter(Context context, VerifyError verifyError) {
        return observable -> observable
                .compose(applySchedulers())
                .filter(verify(context));
    }

    /**
     * 聚合了session过滤器,业务过滤器及合并操作
     */
    public static <T> Observable.Transformer<T, T>
    applySchedulersAndAllFilter(Context context, ErrorVerify errorVerify) {
        return observable -> observable
                .compose(applySchedulers())
                .onErrorReturn(throwable -> {
                    LogUtil.e(Log.getStackTraceString(throwable));
                    throwable.printStackTrace();
                    if (errorVerify != null) {
                        errorVerify.netError(throwable);
                    }
                    return null;
                })
                .filter(verifyNotEmpty())
                .filter(verify(context))
                .filter(verifyBusiness(errorVerify));
    }

    /**
     * 聚合了session过滤器,简单业务过滤器及合并操作及自定义的错误返回
     */
    public static <T> Observable.Transformer<BaseResponse<T>, T>
    applySchedulersResult(Context context, ErrorVerify errorVerify) {
        return observable -> observable
                .compose(applySchedulersAndAllFilter(context, errorVerify))
                .map(t -> t.result)
//                .map((Func1<T, T>) t -> fillNullData(t))
                ;
    }


//    /**
//     * 将response的result的第一层数据填充非空（为避免递归情况）,填充的规则是使用了@SerializedName标注，
//     * 注意请不要在result使用Array数组，如使用数组，可能会导致部分数据无法填充
//     *
//     * @param a
//     * @param <T>
//     * @return
//     */
//    private static <T> T fillNullData(Object a) {
//        try {
//            Class<T> tClass = (Class<T>) a.getClass();
//            Field[] fields = tClass.getDeclaredFields();
//            for (Field field : fields) {
//                try {
//                    field.getDeclaredAnnotations();
//                    Annotation an = field.getAnnotation(SerializedName.class);
//                    if (an != null) {
//                        field.setAccessible(true);
//                        if (field.get(a) == null) {
//                            if (field.getType() != Integer.class && field.getType() != Long.class) {
//                                if (field.getType() == List.class) {
//                                    field.set(a, Collections.EMPTY_LIST);
//                                } else
//                                    field.set(a, field.getType().newInstance());
//                            }
//                        }
//                        field.setAccessible(false);
//                    }
//                } catch (Exception e1) {
//                    continue;
//                }
//
//            }
//
//        } catch (Exception e) {
//
//        } finally {
//            return (T) a;
//        }
//    }


    /**
     * 聚合了session过滤器,简单业务过滤器及合并操作
     */
    public static <T> Observable.Transformer<T, T> applySchedulersAndAllFilter(Context context) {
        return applySchedulersAndAllFilter(context, new SimpleErrorVerify());
    }
}
