package com.movie.liam.movieapp.main;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.model.Configuration;
import com.movie.liam.movieapp.model.Genres;
import com.movie.liam.movieapp.model.Movies;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by lduf0001 on 10/10/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {

    MainPresenterImpl presenter;

    @Mock Api api;
    @Mock MainContract.MainView view;
    @Before

    public void setUp() throws Exception {
        presenter = spy(new MainPresenterImpl(api));
        doReturn(Schedulers.immediate()).when(presenter).mainScheduler();
    }
    
    @Test
    public void testFetchData(){
        presenter.onViewAttached(view);
        Configuration configuration = mock(Configuration.class);
        Genres genres = mock(Genres.class);
        Movies movies = mock(Movies.class);
        doReturn(Observable.just(configuration)).when(api).getConfiguration();
        doReturn(Observable.just(genres)).when(api).getGenres();
        doReturn(Observable.just(movies)).when(api).list(anyString());
        presenter.fetchDate();
        verify(view).populateList(anyList());

    }

}