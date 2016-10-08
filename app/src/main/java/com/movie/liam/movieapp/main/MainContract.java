package com.movie.liam.movieapp.main;

import java.util.List;

import com.movie.liam.movieapp.model.Results;

/**
 * Created by lduf0001 on 06/10/2016.
 */

public class MainContract {

    interface MainView {
        void showError(String error);
        void populateList(List<Results> movies);
        void setProgressVisible(int visibility);
    }

    public interface MainPresenter {
        void onViewAttached(MainView view);
        void onViewDetached();
        void fetchDate();
        void fetchMoreData();
    }
}