package com.yinp.fortunatereader.web.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRetrofit {
//    http://api.pingcc.cn/fiction/search/title/全职高手/1/10
    @GET("/fiction/search/{option}/{key}/{from}/{size}")
    Observable<BaseRetrofitData> getNovelList(@Path("option") String option,@Path("key") String key,@Path("from") int from,@Path("size") int size);
}
