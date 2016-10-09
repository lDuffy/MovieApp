package com.movie.liam.movieapp.main;

import android.os.Bundle;

import javax.inject.Inject;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.base.InjectedActivity;
import com.movie.liam.movieapp.dagger.DaggerMainComponent;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.dagger.MainModule;
import com.movie.liam.movieapp.utils.Launcher;

public class MainActivity extends InjectedActivity<MainComponent> {

    @Inject Launcher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        if (null == savedInstanceState) {
            launcher.openHome();
        }
    }

    @Override
    public MainComponent getComponent() {
        return DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .appComponent(getAppComponent())
                .build();
    }
}
