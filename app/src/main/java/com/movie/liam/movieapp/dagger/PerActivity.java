package com.movie.liam.movieapp.dagger;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lduf0001 on 06/10/2016.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}