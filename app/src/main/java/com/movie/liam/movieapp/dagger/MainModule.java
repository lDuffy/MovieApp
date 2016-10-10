package com.movie.liam.movieapp.dagger;

import android.support.v7.app.AppCompatActivity;

import com.movie.liam.movieapp.adapter.RecyclerViewAdapter;
import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.main.MainContract;
import com.movie.liam.movieapp.main.MainPresenterImpl;
import com.movie.liam.movieapp.utils.AppLauncher;
import com.movie.liam.movieapp.utils.ConfigurationManager;
import com.movie.liam.movieapp.utils.Launcher;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lduf0001 on 06/10/2016.
 * Module for per Activity objects
 */

@Module
public class MainModule {

    private final AppCompatActivity activity;

    public MainModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    Launcher providesLauncher() {
        return new AppLauncher(activity);
    }

    @Provides
    MainContract.MainPresenter providesMainPresenter(Api api) {
        return new MainPresenterImpl(api);
    }

    @Provides
    RecyclerViewAdapter providesRecycleViewAdapter(ConfigurationManager configurationManager){
        return new RecyclerViewAdapter(activity, configurationManager);
    }
}