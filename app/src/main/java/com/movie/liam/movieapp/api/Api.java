package com.movie.liam.movieapp.api;

import com.movie.liam.movieapp.model.Configuration;
import com.movie.liam.movieapp.model.Movies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lduf0001 on 06/10/2016.
 */
public interface Api {
    @GET("3/movie/popular")
    Observable<Movies> list(@Query("page") String page);

    @GET("3/configuration")
    Observable<Configuration> getConfiguration();
}

