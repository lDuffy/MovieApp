package com.movie.liam.movieapp;

import android.app.Application;

import com.movie.liam.movieapp.dagger.AppComponent;
import com.movie.liam.movieapp.dagger.AppModule;
import com.movie.liam.movieapp.dagger.DaggerAppComponent;

/**
 * Created by lduf0001 on 08/10/2016.
 * Global application class.
 */

public class AppApplication extends Application {

    /**
     * App module instantiated at construction time. NOTE: do not use this instance before onCreate is called!!!!
     * */
    private final AppModule appModule;
    private AppComponent appComponent;


    public AppApplication() {
        appModule = new AppModule(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(appModule).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
