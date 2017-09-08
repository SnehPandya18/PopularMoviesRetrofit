package com.snehpandya.popularmovies.apiservice;

import com.snehpandya.popularmovies.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sneh.pandya on 08/09/17.
 */

public interface TMDBApi {

    @GET("3/movie/popular")
    Call<Response> popularResponse(@Query("api_key") String key, @Query("page") int page);

    @GET("3/movie/top_rated")
    Call<Response> topRatedResponse(@Query("api_key") String key, @Query("page") int page);
}
