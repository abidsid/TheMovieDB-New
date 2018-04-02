package com.example.abjs.moviedb.Interface;

import com.example.abjs.moviedb.Model.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ajuna on 3/1/2018.
 */

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key);
    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String api_key);
    @GET("search/movie")
    Call<MovieResponse>searchMovies(@Query("api_key") String api_key, @QueryMap Map<String,String> options);
    @GET("movie/{movie_id}/similar")
    Call<MovieResponse>getSimilarMovies(@Path("movie_id") String movie_id,@Query("api_key") String api_key);
    @GET("movie/now_playing")
    Call<MovieResponse>getnowplaying(@Query("api_key") String api_key);
    @GET("movie/upcoming")
    Call<MovieResponse>getupcoming(@Query("api_key") String api_key);
    @GET("genre/movie/list")
    Call<MovieResponse>getgenrelist(@Query("api_key") String api_key);
}
