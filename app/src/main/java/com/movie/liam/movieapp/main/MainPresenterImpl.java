package com.movie.liam.movieapp.main;

import android.view.View;

import javax.inject.Inject;

import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.model.Movies;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lduf0001 on 06/10/2016.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {

    Api api;
    MainContract.MainView view;
    Movies movies;


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

        api.list("1").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movies>() {
                    @Override
                    public void onCompleted() {
                        view.setProgressVisible(View.GONE);
                    }

                    @Override
                    public final void onError(Throwable e) {
                        view.showError(e.toString());
                    }

                    @Override
                    public final void onNext(Movies response) {
                        movies = response;
                        view.populateList(response);
                    }
                });
    }
}
