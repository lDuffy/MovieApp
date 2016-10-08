package com.movie.liam.movieapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.movie.liam.movieapp.api.Api;
import com.movie.liam.movieapp.model.Configuration;
import com.movie.liam.movieapp.model.Image;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class ConfigurationManager {

    private static Configuration configuration;
    private final Context context;
    public final Api api;

    @Inject
    public ConfigurationManager(Context context, Api api) {
        this.api = api;
        this.context = context;
    }

    public void fetchConfiguration() {
        if (null == configuration) {
            api.getConfiguration().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(ConfigurationManager::setConfiguration);
        }
    }

    private static void setConfiguration(Configuration configuration) {
        ConfigurationManager.configuration = configuration;
    }

    public static List<Integer> getIntegerList(Collection<String> list) {
        list.remove("original");
        List<Integer> intList = new ArrayList<>();

        for (String item : list) {
            intList.add(Integer.valueOf(item.substring(1)));
        }
        return intList;
    }

    public String getUrl(String path, Image.Type type) {
        int density = getDensity();
        return configuration.getImage().getBaseUrl() + getSize(density, type) + path;
    }

    private static String getSize(int density, Image.Type type) {
        List<String> list;
        Image image =configuration.getImage();

        switch (type) {
            case POSTER:
                list = image.getPosterSizes();
                break;
            case BACKDROP:
                list = image.getBackdropSizes();
                break;
            case STILL:
                list = image.getStillSizes();
                break;
            case LOGO:
                list = image.getLogoSizes();
                break;
            default:
                list = image.getLogoSizes();
                break;

        }
        int index = getIndex(density, list);
        return configuration.getImage().getPosterSizes().get(index);
    }

    private static int getIndex(int density, Collection<String> list) {
        List<Integer> intList = getIntegerList(list);
        int index = Collections.binarySearch(intList, density);
        return -index - 1;
    }

    private int getDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }
}
