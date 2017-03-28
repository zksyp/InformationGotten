package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午5:30
 * Desc:短评
 */

public class Comment implements Serializable {

    @SerializedName("id")
    @Expose
    public String id;//短评id

    @SerializedName("created_at")
    @Expose
    public String createdAt;//短评发布日期

    @SerializedName("subject_id")
    @Expose
    public String subjectId;//短评条目id

    @SerializedName("author")
    @Expose
    public User author;//上传用户

    @SerializedName("content")
    @Expose
    public String content;//短评内容

    @SerializedName("rating")
    @Expose
    public Rating rating;//短评评分

    @SerializedName("useful_count")
    @Expose
    public int usefulCount;//有用数
}
