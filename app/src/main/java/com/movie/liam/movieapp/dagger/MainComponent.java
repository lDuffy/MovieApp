package com.movie.liam.movieapp.dagger;

import com.movie.liam.movieapp.main.MainFragment;

import dagger.Component;

/**
 * Created by lduf0001 on 06/10/2016.
 */

@PerActivity
@Component(modules = MainModule.class, dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainFragment mainFragment);
}