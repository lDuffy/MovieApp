package com.movie.liam.movieapp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.main.MainFragment;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class AppLauncher implements Launcher {
    private final AppCompatActivity activity;

    public AppLauncher(AppCompatActivity activity) {
        this.activity = activity;
    }

    private void showFragment(Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void openHome() {
        showFragment(MainFragment.newInstance());
    }

    @Override
    public void openDetail() {

    }
}
