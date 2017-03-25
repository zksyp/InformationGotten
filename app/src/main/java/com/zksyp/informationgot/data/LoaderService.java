package com.zksyp.informationgot.data;

import com.zksyp.informationgot.bean.request.BaseRequest;
import com.zksyp.informationgot.bean.response.BaseResponse;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:47
 * Desc:网络请求接口
 */

public interface LoaderService {
    @POST("/v1/common/loadStartPage.json")
    Observable<BaseResponse<ArrayList<String>>> loadStartPage(@Body BaseRequest request);//获取应用启动页
}
