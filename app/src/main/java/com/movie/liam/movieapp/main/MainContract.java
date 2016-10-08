package com.movie.liam.movieapp.main;

import com.movie.liam.movieapp.model.Movies;

/**
 * Created by lduf0001 on 06/10/2016.
 */

public class MainContract {

    interface MainView {
        void showError(String error);
        void populateList(Movies movies);
        void setProgressVisible(int visibility);
    }

    public interface MainPresenter {
        void onViewAttached(MainView view);
        void onViewDetached();
        void fetchDate();
    }
}