package com.example.darki.movies.api;

import com.example.darki.movies.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("movie/popular")
    Call<MoviesResponse> getPoplarMovies(@Query("ced9bc018ee91f7fa4fc7f417d3af19c") String apikey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("ced9bc018ee91f7fa4fc7f417d3af19c") String apikey);

}
