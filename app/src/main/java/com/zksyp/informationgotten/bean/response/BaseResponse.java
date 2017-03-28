package com.zksyp.informationgotten.bean.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:37
 * Desc:
 */

public class BaseResponse<T> {
//    /**
//     * 返回码，见ResponseCode枚举
//     */
//    @SerializedName("resultCode")
//    @Expose
//    public String mResultCode;
//
//    //错误描述
//    @SerializedName("errDesc")
//    @Expose
//    public String mErrDesc;
//
//
//    @SerializedName("sessionId")
//    @Expose
//    public String mSessionId;

    @SerializedName("code")
    @Expose
    public String resultCode;

    @SerializedName("msg")
    @Expose
    public String errorDesc;


    @SerializedName("result")
    @Expose
    public T result;
//    @SerializedName("extraInfo")
//    @Expose
//    public Map<String,String> extraInfo;   //key为displayStatus显示状态，key为haveInvoicButton是否有选择发票的功能，key为arriveLocationId是目的城市id，key为startLocationId是起始城市id

}
