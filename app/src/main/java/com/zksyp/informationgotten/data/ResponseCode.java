package com.zksyp.informationgotten.data;

import android.text.TextUtils;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:52
 * Desc:请求返回错误码
 */

public enum ResponseCode {
    RESULT_OK(200, "请求成功"),
    RESULT_CREATED(201, "创建成功"),
    RESULT_ACCEPTED(202, "更新成功"),
    RESULT_BAD_REQUEST(400, "请求格式错误或请求地址不存在"),
    RESULT_UNAUTHORIZED(401, "未授权"),
    RESULT_FORBIDDEN(403, "被禁止访问"),
    RESULT_NOT_FOUND(404, "请求的资源不存在"),
    RESULT_INTERNAL_SERVER_ERROR(500, "内部错误");

    private Integer mCode;

    private String mReason;

    ResponseCode(Integer code, String reason) {
        this.mCode = code;
        this.mReason = reason;
    }

    private static ResponseCode valueOf(Integer code) {
        switch (code) {
            case 200:
                return RESULT_OK;
            case 201:
                return RESULT_CREATED;
            case 202:
                return RESULT_ACCEPTED;
            case 400:
                return RESULT_BAD_REQUEST;
            case 401:
                return RESULT_UNAUTHORIZED;
            case 403:
                return RESULT_FORBIDDEN;
            case 404:
                return RESULT_NOT_FOUND;
            case 500:
                return RESULT_INTERNAL_SERVER_ERROR;
            default:
                return RESULT_BAD_REQUEST;
        }

    }


    public static ResponseCode toEnum(String code) {
        if (TextUtils.isEmpty(code) || !TextUtils.isDigitsOnly(code)) {
            return RESULT_BAD_REQUEST;
        }
        return valueOf(Integer.parseInt(code));
    }

}
