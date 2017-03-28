package com.zksyp.informationgotten.data;

import com.zksyp.informationgotten.bean.MovieSubject;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/25
 * Time:下午2:47
 * Desc:网络请求接口
 */

public interface LoaderService {

    @GET("/v2/movie/subject/{id}")
    Observable<MovieSubject> getMovieSubject(@Path("id") String id);//获取单个电影详情

}
