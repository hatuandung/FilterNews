package com.example.ex7t3hhomework.api;

import com.example.ex7t3hhomework.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("everything")
    Call<NewsResponse> getNews(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );

}
