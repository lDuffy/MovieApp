package com.movie.liam.movieapp.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class Image {
    @SerializedName("poster_sizes")
    private String[] posterSizes;

    @SerializedName("backdrop_sizes")
    private String[] backdropSizes;

    @SerializedName("still_sizes")
    private String[] stillSizes;

    @SerializedName("logo_sizes")
    private String[] logoSizes;

    @SerializedName("secure_base_url")
    private String secureBaseUrl;

    @SerializedName("base_url")
    private String baseUrl;

    @SerializedName("profile_sizes")
    private String[] profileSizes;

    public String[] getPosterSizes() {
        return posterSizes;
    }

    public String[] getBackdropSizes() {
        return backdropSizes;
    }

    public String[] getStillSizes() {
        return stillSizes;
    }

    public String[] getLogoSizes() {
        return logoSizes;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String[] getProfileSizes() {
        return profileSizes;
    }

    @Override
    public String toString() {
        return "Image{" +
                "posterSizes=" + Arrays.toString(posterSizes) +
                ", backdropSizes=" + Arrays.toString(backdropSizes) +
                ", stillSizes=" + Arrays.toString(stillSizes) +
                ", logoSizes=" + Arrays.toString(logoSizes) +
                ", secureBaseUrl='" + secureBaseUrl + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", profileSizes=" + Arrays.toString(profileSizes) +
                '}';
    }
}