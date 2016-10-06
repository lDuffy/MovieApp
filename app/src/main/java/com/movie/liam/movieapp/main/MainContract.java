package com.movie.liam.movieapp.main;

/**
 * Created by lduf0001 on 06/10/2016.
 */

public class MainContract {

    interface MainView {
        void showError();
        void showToastComplete();
    }

    public interface MainPresenter {
        void onViewAttached(MainView view);
        void onViewDetached();
        void fetchDate();
    }
}