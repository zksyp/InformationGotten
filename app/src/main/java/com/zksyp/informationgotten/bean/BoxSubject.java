package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午5:57
 * Desc:票房信息
 */

public class BoxSubject implements Serializable {

    @SerializedName("rank")
    @Expose
    public int rank;//排名

    @SerializedName("box")
    @Expose
    public int box;//票房

    @SerializedName("new")
    @Expose
    public boolean newMovie;//是否新上映

    @SerializedName("subject")
    @Expose
    public MovieSubject movie;

}
