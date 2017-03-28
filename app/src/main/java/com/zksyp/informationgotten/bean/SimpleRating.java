package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午5:52
 * Desc:
 */

public class SimpleRating implements Serializable {
    @SerializedName("min")
    @Expose
    public int minPoint;

    @SerializedName("max")
    @Expose
    public int maxPoint;

    @SerializedName("average")
    @Expose
    public float average;//评分

    @SerializedName("stars")
    @Expose
    public int stars;//评星数
}
