package com.movie.liam.movieapp.base;

import android.support.v4.app.Fragment;

import com.movie.liam.movieapp.dagger.ComponentProvider;

/**
 * Created by lduf0001 on 08/10/2016.
 * Abstract fragment to reduce boiler plate code for registering class with dagger
 */

public abstract class InjectedFragment<T> extends Fragment implements ComponentProvider<T> {

    @Override
    public T getComponent() {
        ComponentProvider<T> componentComponentProvider = (ComponentProvider<T>) getActivity();
        return componentComponentProvider.getComponent();
    }

}
