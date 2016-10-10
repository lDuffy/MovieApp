package com.movie.liam.movieapp.base;

import android.support.v7.app.AppCompatActivity;

import com.movie.liam.movieapp.AppApplication;
import com.movie.liam.movieapp.dagger.AppComponent;
import com.movie.liam.movieapp.dagger.ComponentProvider;

/**
 * Created by lduf0001 on 08/10/2016.
 * Abstract activity to reduce boiler plate code for registering class with dagger
 */

public abstract class InjectedActivity<T> extends AppCompatActivity
        implements ComponentProvider<T> {

    protected AppComponent getAppComponent() {
        AppApplication application = (AppApplication) getApplication();
        return application.getAppComponent();
    }
}
