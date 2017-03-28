package com.zksyp.informationgotten.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午4:20
 * Desc:电影条目信息
 */

public class MovieSubject implements Serializable {


    @SerializedName("id")
    @Expose
    public String id;//条目id

    @SerializedName("title")
    @Expose
    public String title;//中文名

    @SerializedName("original_title")
    @Expose
    public String originalTitle;//原名

    @SerializedName("aka")
    @Expose
    public String[] akas;//别名

    @SerializedName("alt")
    @Expose
    public String altUrl;//条目页URL

    @SerializedName("mobile_url")
    @Expose
    public String mobileUrl;//移动版条目页URL

    @SerializedName("rating")
    @Expose
    public Rating rating;

    @SerializedName("ratings_count")
    @Expose
    public int ratingsCount;//评分人数

    @SerializedName("wish_count")
    @Expose
    public int wishCount;//想看人数

    @SerializedName("collect_count")
    @Expose
    public int collectCount;//收藏人数

    @SerializedName("do_count")
    @Expose
    public int doCount;//在看人数，如果是电视剧，默认值为0，如果是电影值为null

    @SerializedName("images")
    @Expose
    public Images poster;

    @SerializedName("subtype")
    @Expose
    public String subType;//条目分类, movie或者tv

    @SerializedName("directors")
    @Expose
    public ArrayList<Celebrity> directors;//导演

    @SerializedName("casts")
    @Expose
    public ArrayList<Celebrity> casts;//主演

    @SerializedName("writers")
    @Expose
    public ArrayList<Celebrity> writers;//编剧

    @SerializedName("website")
    @Expose
    public String website;//官方网站

    @SerializedName("douban_site")
    @Expose
    public String doubanSite;//豆瓣小站

//    @SerializedName("pubdates")
//    @Expose
//    public String[] pubDates;//上映日期

    @SerializedName("mainland_pubdate")
    @Expose
    public String mainlandPubDate;//大陆上映日期

    @SerializedName("year")
    @Expose
    public String year;//年代

    @SerializedName("languages")
    @Expose
    public String[] languages;//语言

//    @SerializedName("durations")
//    @Expose
//    public String[] durations;//时长

    @SerializedName("genres")
    @Expose
    public String[] genres;//影片类型

    @SerializedName("countries")
    @Expose
    public String[] countries;//国家

    @SerializedName("summary")
    @Expose
    public String summary;//简介

    @SerializedName("comments_count")
    @Expose
    public int commentCount;//短评数量

    @SerializedName("reviews_count")
    @Expose
    public int reviewsCount;//影评数量

    @SerializedName("seasons_count")
    @Expose
    public int seasonCount;//总季数（tv only)

    @SerializedName("current_season")
    @Expose
    public int currentSeason;//当前季数

    @SerializedName("episodes_count")
    @Expose
    public int episodesCount;//当前季的集数

    @SerializedName("schedule_url")
    @Expose
    public String scheduleUrl;//影讯页的URL

    @SerializedName("photos")
    @Expose
    public ArrayList<Photo> photos;//剧照

    @SerializedName("popular_reviews")
    @Expose
    public ArrayList<Review> popularReviews;//影评

}
