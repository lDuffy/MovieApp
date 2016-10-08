package com.movie.liam.movieapp.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class Configuration {
    @SerializedName("change_keys")
    private String[] changeKeys;

    private Image images;

    public String[] getChangeKeys() {
        return changeKeys;
    }

    public Image getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "changeKeys=" + Arrays.toString(changeKeys) +
                ", images=" + images +
                '}';
    }
}