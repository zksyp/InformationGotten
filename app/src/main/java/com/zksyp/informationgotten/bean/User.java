package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午5:23
 * Desc:用户信息
 */

public class User implements Serializable{

    @SerializedName("id")
    @Expose
    public String id;//用户id

    @SerializedName("name")
    @Expose
    public String name;//用户名

    @SerializedName("uid")
    @Expose
    public String uid;//用户唯一标示

    @SerializedName("signature")
    @Expose
    public String signature;//用户签名

    @SerializedName("alt")
    @Expose
    public String alt;//用户个人主页

    @SerializedName("avatar")
    @Expose
    public String avatar;//用户头像
}
