package com.movie.liam.movieapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lduf0001 on 08/10/2016.
 */

public class Image {

    public enum Type{
        POSTER,BACKDROP,STILL,LOGO
    }
    @SerializedName("poster_sizes")
    private List<String> posterSizes;

    @SerializedName("backdrop_sizes")
    private List<String> backdropSizes;

    @SerializedName("still_sizes")
    private List<String> stillSizes;

    @SerializedName("logo_sizes")
    private List<String> logoSizes;

    @SerializedName("secure_base_url")
    private String secureBaseUrl;

    @SerializedName("base_url")
    private String baseUrl;

    @SerializedName("profile_sizes")
    private List<String> profileSizes;

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public List<String> getStillSizes() {
        return stillSizes;
    }

    public List<String> getLogoSizes() {
        return logoSizes;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public List<String> getProfileSizes() {
        return profileSizes;
    }

    @Override
    public String toString() {
        return "Image{" +
                "posterSizes=" + posterSizes +
                ", backdropSizes=" + backdropSizes +
                ", stillSizes=" + stillSizes +
                ", logoSizes=" + logoSizes +
                ", secureBaseUrl='" + secureBaseUrl + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", profileSizes=" + profileSizes +
                '}';
    }
}