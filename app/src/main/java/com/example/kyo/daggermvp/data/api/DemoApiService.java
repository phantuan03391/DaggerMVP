package com.example.kyo.daggermvp.data.api;

import com.example.kyo.daggermvp.data.model.Article;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DemoApiService {
    @GET("get-articles")
    Observable<List<Article>> getBlog(@Query("start") int start);

    @GET("get-article-detail")
    Observable<Article> getBlogDetail(@Query("id") String id);
}
