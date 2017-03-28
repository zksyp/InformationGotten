package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午4:54
 * Desc:剧照
 */

public class Photo implements Serializable {

    @SerializedName("id")
    @Expose
    public String id;//图片id

    @SerializedName("subject_id")
    @Expose
    public String subjectId;//条目id

    @SerializedName("alt")
    @Expose
    public String alt;//图片展示页url

    @SerializedName("image")
    @Expose
    public String image;//图片地址

    @SerializedName("icon")
    @Expose
    public String icon;//icon图片地址

    @SerializedName("thumb")
    @Expose
    public String thumb;//thumb图片地址

    @SerializedName("cover")
    @Expose
    public String cover;//cover图片地址

    @SerializedName("created_at")
    @Expose
    public String createdAt;//发布时间

    @SerializedName("desc")
    @Expose
    public String desc;//图片描述

    @SerializedName("author")
    @Expose
    public User author;//发布者

    @SerializedName("album_id")
    @Expose
    public String albumId;//相册id

    @SerializedName("album_title")
    @Expose
    public String albumTitle;//相册名称

    @SerializedName("album_url")
    @Expose
    public String albumUrl;//相册地址

    @SerializedName("next_photo")
    @Expose
    public String nextPhoto;//下一张

    @SerializedName("prev_photo")
    @Expose
    public String prevPhoto;//上一张

    @SerializedName("position")
    @Expose
    public int position;//在相册中的位置

    @SerializedName("comments_count")
    @Expose
    public int commentsCount;//评论数

    @SerializedName("photos_count")
    @Expose
    public int photosCount;//全部剧照数量

}
