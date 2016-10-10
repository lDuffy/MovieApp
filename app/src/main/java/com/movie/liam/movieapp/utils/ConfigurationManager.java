package com.movie.liam.movieapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.movie.liam.movieapp.model.Configuration;
import com.movie.liam.movieapp.model.Genres;
import com.movie.liam.movieapp.model.Image;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class ConfigurationManager {

    public static Configuration configuration = null;
    public static Genres genres = null;
    private final Context context;

    @Inject
    public ConfigurationManager(Context context) {
        this.context = context;
    }

    public static void setConfiguration(Configuration configuration) {
        ConfigurationManager.configuration = configuration;
    }

    public static boolean hasConfiguration() {
        return null != configuration;
    }

    public static void setGenres(Genres genres) {
        ConfigurationManager.genres = genres;
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
        Image image = configuration.getImage();

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
        int index = gerResourceSize(density, list);
        return configuration.getImage().getPosterSizes().get(index);
    }

    /*
     * Collections.binarySearch() will return a value greater then the size of the list
     * if the density argument is greater then the highest value in the list. This method accounts for that
     *
     */
    private static int accountForOutOfBoundsIndex(int size, int index) {
        int localIndex = index;
        if (index >= size) {
            localIndex -= 1;
        }
        return localIndex - 1;
    }

    private static int gerResourceSize(int density, Collection<String> list) {
        List<Integer> intList = getIntegerList(list);
        int index = Collections.binarySearch(intList, density);
        index = Math.abs(index);
        index = accountForOutOfBoundsIndex(list.size(), index);
        return index;
    }

    public int getDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }
}
