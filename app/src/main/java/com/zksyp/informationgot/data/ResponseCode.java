package com.zksyp.informationgot.data;

import android.text.TextUtils;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:52
 * Desc:请求返回错误码
 */

public enum ResponseCode {
    RESULT_SUC(1, "请求成功"),
    RESULT_SESSION_INVALID(2, "session过期"),
    RESULT_ACCOUNT_ERROR(9, "账号异常"),
    RESULT_SESSION_NOT_FOUND(101, "session不合法"),
    RESULT_FAILED(3, "请求异常"),
    RESULT_NOT_REGISTER(8, "账号尚未注册");

    private Integer mCode;

    private String mReason;

    ResponseCode(Integer code, String reason) {
        this.mCode = code;
        this.mReason = reason;
    }

    private static ResponseCode valueOf(Integer code) {
        switch (code) {
            case 1:
                return RESULT_SUC;
            case 2:
                return RESULT_SESSION_INVALID;
            case 9:
                return RESULT_ACCOUNT_ERROR;
            case 101:
                return RESULT_SESSION_NOT_FOUND;
            case 3:
                return RESULT_FAILED;
            case 8:
                return RESULT_NOT_REGISTER;
            default:
                return RESULT_FAILED;
        }

    }


    public static ResponseCode toEnum(String code) {
        if (TextUtils.isEmpty(code) || !TextUtils.isDigitsOnly(code)) {
            return RESULT_FAILED;
        }
        return valueOf(Integer.parseInt(code));
    }

    /**
     * 是否返回结果到业务层
     */
    public boolean isForResult() {
        return this == RESULT_SUC || this == RESULT_FAILED;
    }


    public boolean isToLogin() {
        return this == RESULT_SESSION_INVALID || this == RESULT_ACCOUNT_ERROR || this == RESULT_SESSION_NOT_FOUND;
    }

    public boolean isAccountError() {
        return this == RESULT_ACCOUNT_ERROR;
    }

    public boolean isSuccess() {
        return this == RESULT_SUC;
    }

    public boolean isToRegister() {
        return this == RESULT_NOT_REGISTER;
    }
}
