package com.movie.liam.movieapp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.movie.liam.movieapp.R;

public class MainActivity extends AppCompatActivity {

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

}
