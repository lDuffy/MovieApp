package com.movie.liam.movieapp.dagger;

import android.content.Context;

import javax.inject.Singleton;

import com.movie.liam.movieapp.api.Api;

import dagger.Component;

/**
 * Created by lduf0001 on 06/10/2016.
 * Component for application global objects
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();
    Api api();
}