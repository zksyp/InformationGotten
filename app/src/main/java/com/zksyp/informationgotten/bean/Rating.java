package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午4:36
 * Desc:评分
 */

public class Rating implements Serializable {

    @SerializedName("min")
    @Expose
    public int minPoint;

    @SerializedName("max")
    @Expose
    public int maxPoint;

    @SerializedName("value")
    @Expose
    public int value;//评分
}
