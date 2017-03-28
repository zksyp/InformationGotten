package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午5:41
 * Desc:电影列表信息
 */

public class MovieData implements Serializable {

    @SerializedName("title")
    @Expose
    public String title;//

    @SerializedName("total")
    @Expose
    public int total;//

    @SerializedName("start")
    @Expose
    public int start;

    @SerializedName("count")
    @Expose
    public int count;//

    @SerializedName("subjects")
    @Expose
    public ArrayList<MovieSubject> movieSubjects;
}
