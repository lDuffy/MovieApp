package com.movie.liam.movieapp.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.rest.RestService;
import com.movie.liam.movieapp.utils.ConfigurationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lduf0001 on 06/10/2016.
 * Module for application global objects
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Api providesApi() {
        return RestService.getApi();
    }

    @Provides
    @Singleton
    ConfigurationManager providesUrlGenerator(Context context){
        return new ConfigurationManager(context);
    }
}


