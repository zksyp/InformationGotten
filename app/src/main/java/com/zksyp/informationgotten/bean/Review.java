package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午5:20
 * Desc:影评信息
 */

public class Review implements Serializable {
    @SerializedName("id")
    @Expose
    public String id;//影评id

    @SerializedName("title")
    @Expose
    public String title;//影评名

    @SerializedName("alt")
    @Expose
    public String alt;//影评url

    @SerializedName("created_at")
    @Expose
    public String createdAt;//发布日期

    @SerializedName("updated_at")
    @Expose
    public String updatedAt;//更新日期

    @SerializedName("subject_id")
    @Expose
    public String subjectId;//条目id

    @SerializedName("author")
    @Expose
    public User author;//上传用户

    @SerializedName("rating")
    @Expose
    public Rating rating;//影评评分

    @SerializedName("summary")
    @Expose
    public String summary;//影评摘要

    @SerializedName("useful_count")
    @Expose
    public int usefulCount;//有用数

    @SerializedName("useless_count")
    @Expose
    public int uselessCount;//无用数

    @SerializedName("comments_count")
    @Expose
    public int commentsCount;//评论数
}
