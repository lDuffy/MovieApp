package com.movie.liam.movieapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.movie.liam.movieapp.R;
import com.movie.liam.movieapp.adapter.RecyclerViewAdapter;
import com.movie.liam.movieapp.base.InjectedFragment;
import com.movie.liam.movieapp.dagger.MainComponent;
import com.movie.liam.movieapp.model.Movies;
import com.movie.liam.movieapp.model.Results;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lduf0001 on 06/10/2016.
 */
public class MainFragment extends InjectedFragment<MainComponent> implements MainContract.MainView {
    @Bind(R.id.progress) public ProgressBar progressIndicator;
    @Bind(R.id.search_result) public RecyclerView rView;

    @Inject MainContract.MainPresenter mainPresenter;

    List<Results> itemList = new ArrayList<>();
    RecyclerViewAdapter viewAdapter;


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
        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);

        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        viewAdapter = new RecyclerViewAdapter(getActivity(), itemList);
        rView.setAdapter(viewAdapter);
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
        itemList.clear();
        itemList.addAll(movies.getResults());
        viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setProgressVisible(int visibility) {
        progressIndicator.setVisibility(visibility);
    }
}
