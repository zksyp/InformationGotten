package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午4:41
 * Desc:海报
 */

public class Images implements Serializable {

    @SerializedName("small")
    @Expose
    public String smallUrl;

    @SerializedName("medium")
    @Expose
    public String mediumUrl;

    @SerializedName("large")
    @Expose
    public String largeUrl;
}
