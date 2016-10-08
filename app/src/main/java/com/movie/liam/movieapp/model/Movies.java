package com.movie.liam.movieapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lduf0001 on 06/10/2016.
 */

public class Movies {
    private List<Results> results;

    private String page;
    @SerializedName("total_pages")
    private String totalPages;
    @SerializedName("total_results")
    private String totalResults;

    public List<Results> getResults() {
        return results;
    }

    public String getPage() {
        return page;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public String getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "results=" + results +
                ", page='" + page + '\'' +
                ", totalPages='" + totalPages + '\'' +
                ", totalResults='" + totalResults + '\'' +
                '}';
    }
}