package com.zksyp.informationgot.bean.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:37
 * Desc:request 基类
 */

public class BaseRequest {

    @SerializedName("ttid")
    @Expose
    public String ttid;

    @SerializedName("sessionId")
    @Expose
    public String sessionId;

    public BaseRequest updateSessionId() {
//        sessionId = BentleyApp.getSessionId();
        return this;
    }
}
