package com.movie.liam.movieapp.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lduf0001 on 06/10/2016.
 */
public class Results {
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private String adult;
    private String id;
    private String title;
    private String overview;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("genre_ids")
    private String[] genreIds;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("poster_path")
    private String posterPath;
    private String video;
    private String popularity;

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getAdult() {
        return adult;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String[] getGenreIds() {
        return genreIds;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Results{" +
                "voteAverage='" + voteAverage + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult='" + adult + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", releaseDate='" + releaseDate + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", voteCount='" + voteCount + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", video='" + video + '\'' +
                ", popularity='" + popularity + '\'' +
                '}';
    }

}
