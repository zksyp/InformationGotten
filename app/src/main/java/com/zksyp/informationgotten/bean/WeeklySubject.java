package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午6:00
 * Desc:
 */

public class WeeklySubject implements Serializable {

    @SerializedName("rank")
    @Expose
    public int rank;//排名
    @SerializedName("delta")
    @Expose
    public int delta;//排名改变量

}
