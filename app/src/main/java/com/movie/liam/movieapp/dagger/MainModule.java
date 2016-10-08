package com.movie.liam.movieapp.dagger;

import android.support.v4.app.FragmentActivity;

import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.main.MainContract;
import com.movie.liam.movieapp.main.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lduf0001 on 06/10/2016.
 */

@Module
public class MainModule {

    private final FragmentActivity activity;

    public MainModule(FragmentActivity activity) {
        this.activity = activity;
    }

    @Provides
    MainContract.MainPresenter providesMainPresenter(Api api) {
        return new MainPresenterImpl(api);
    }
}