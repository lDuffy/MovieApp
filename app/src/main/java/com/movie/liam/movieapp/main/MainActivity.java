package com.movie.liam.movieapp.main;

import android.os.Bundle;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.base.InjectedActivity;
import com.movie.liam.movieapp.dagger.DaggerMainComponent;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.dagger.MainModule;

public class MainActivity extends InjectedActivity<MainComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.event_create_container, MainFragment.newInstance())
                    .commitAllowingStateLoss();
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
