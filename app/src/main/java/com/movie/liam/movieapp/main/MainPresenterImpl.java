package com.movie.liam.movieapp.main;

import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.model.Configuration;
import com.movie.liam.movieapp.model.Genres;
import com.movie.liam.movieapp.model.Movies;
import com.movie.liam.movieapp.model.Results;
import com.movie.liam.movieapp.utils.ConfigurationManager;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lduf0001 on 06/10/2016.
 * presenter for handeling business logic of MainFragment
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

        if (!ConfigurationManager.hasConfiguration()) {
            api.getConfiguration()
                    .flatMap(this::getGenresObservable)
                    .flatMap(this::getMovieList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(mainScheduler())
                    .subscribe(this::setResponse,
                            throwable -> view.showToast(throwable.toString()),
                            () -> view.setProgressVisible(View.GONE));
        } else {
            api.list(movies.getPage())
                    .subscribeOn(Schedulers.io())
                    .debounce(3, TimeUnit.SECONDS)
                    .observeOn(mainScheduler())
                    .subscribe(this::setResponse,
                            throwable -> view.showToast(throwable.toString()),
                            () -> view.setProgressVisible(View.GONE));
        }
    }

    public Scheduler mainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    private Observable<Movies> getMovieList(Genres genres) {
        ConfigurationManager.setGenres(genres);
        return api.list(movies.getPage());
    }

    private Observable<Genres> getGenresObservable(Configuration configuration) {
        ConfigurationManager.setConfiguration(configuration);
        return api.getGenres();
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

    @Override
    public void sortAndInvalidate(List<Results> list) {
        Collections.sort(list, (o1, o2) -> o1.getPopularity().compareTo(o2.getPopularity()));
        view.invalidateAdapter();
        view.showToast("Sorted By Popularity");
    }

    @Override
    public List<Results> removeDuplicates(List<Results> list) {
        Set<Results> noDups = new HashSet<>(list);
        return new ArrayList<Results>(noDups);
    }
}
