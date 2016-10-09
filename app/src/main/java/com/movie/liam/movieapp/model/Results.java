package com.movie.liam.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lduf0001 on 06/10/2016.
 */
public class Results implements Parcelable {
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

    public String getPopularity() {
        return popularity;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((null == o) || (getClass() != o.getClass())) {
            return false;
        }
        Results results = (Results) o;
        return (null != id) ? id.equals(results.id) : ((null == results.id) && ((null != title) ? title.equals(results.title) : (null == results.title)));
    }

    @Override
    public int hashCode() {
        int result = (null != id) ? id.hashCode() : 0;
        result = (31 * result) + ((null != title) ? title.hashCode() : 0);
        return result;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(voteAverage);
        dest.writeString(backdropPath);
        dest.writeString(adult);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(originalLanguage);
        dest.writeStringArray(genreIds);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(voteCount);
        dest.writeString(posterPath);
        dest.writeString(video);
        dest.writeString(popularity);
    }

    public Results() {
    }

    protected Results(Parcel in) {
        voteAverage = in.readString();
        backdropPath = in.readString();
        adult = in.readString();
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        originalLanguage = in.readString();
        genreIds = in.createStringArray();
        releaseDate = in.readString();
        originalTitle = in.readString();
        voteCount = in.readString();
        posterPath = in.readString();
        video = in.readString();
        popularity = in.readString();
    }

    public static final Parcelable.Creator<Results> CREATOR = new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
