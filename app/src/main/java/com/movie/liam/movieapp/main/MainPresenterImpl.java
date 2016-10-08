package com.movie.liam.movieapp.main;

import android.view.View;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.model.Movies;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lduf0001 on 06/10/2016.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {

    public static final String FIRST_PAGE = "1";
    private MainContract.MainView view;
    private Movies movies;
    private Api api;


    @Inject
    public MainPresenterImpl(Api api) {
        this.api = api;
        movies = new Movies();
    }

    @Override
    public void onViewAttached(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void fetchDate() {
        view.setProgressVisible(View.VISIBLE);

        api.list(movies.getPage())
                .subscribeOn(Schedulers.newThread())
                .debounce(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setResponse,
                        throwable -> view.showError(throwable.toString()),
                        () -> view.setProgressVisible(View.GONE));
    }

    private void setResponse(Movies response) {
        setMovieToResponse(response);
        view.populateList(response.getResults());
    }

    private void setMovieToResponse(Movies response) {
        if (FIRST_PAGE.equals(response.getPage())) {
            movies = response;
        }
    }

    @Override
    public void fetchMoreData() {
        movies.incrementPage();
        fetchDate();
    }
}
