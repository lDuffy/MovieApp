package com.movie.liam.movieapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.base.InjectedFragment;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.model.Movies;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lduf0001 on 06/10/2016.
 */
public class MainFragment extends InjectedFragment<MainComponent> implements MainContract.MainView {
    @Bind(R.id.progress)
    public ProgressBar progressIndicator;
    @Inject MainContract.MainPresenter mainPresenter;

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.onViewAttached(this);
        mainPresenter.fetchDate();

    }

    @Override
    public void onPause() {
        super.onPause();
        mainPresenter.onViewDetached();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void populateList(Movies movies) {

    }

    @Override
    public void setProgressVisible(int visibility) {
        progressIndicator.setVisibility(visibility);
    }
}
