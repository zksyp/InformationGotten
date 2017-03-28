package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午4:45
 * Desc:电影人物信息
 */

public class Celebrity implements Serializable {

    @SerializedName("id")
    @Expose
    public String id;//条目id

    @SerializedName("name")
    @Expose
    public String name;//中文名

    @SerializedName("name_en")
    @Expose
    public String enName;//英文名

    @SerializedName("alt")
    @Expose
    public String alt;//

    @SerializedName("mobile_url")
    @Expose
    public String mobileUrl;//条目id

    @SerializedName("avatars")
    @Expose
    public Images avatars;//头像

    @SerializedName("summary")
    @Expose
    public String summary;//简介

    @SerializedName("aka")
    @Expose
    public String[] aka;//更多中文名

    @SerializedName("aka_en")
    @Expose
    public String[] enAka;//更多英文名

    @SerializedName("website")
    @Expose
    public String website;//官方网站

    @SerializedName("gender")
    @Expose
    public String gender;//性别

    @SerializedName("birthday")
    @Expose
    public String birthday;//出生日期

    @SerializedName("born_place")
    @Expose
    public String bornPlace;//出生地

    @SerializedName("professions")
    @Expose
    public String professions;//职业

    @SerializedName("constellation")
    @Expose
    public String constellation;//星座

    @SerializedName("photos")
    @Expose
    public ArrayList<Photo> photos;//影人剧照

    @SerializedName("works")
    @Expose
    public ArrayList<MovieSubject> works;//影人作品
}
