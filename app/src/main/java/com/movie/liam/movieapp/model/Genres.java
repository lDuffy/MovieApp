package com.movie.liam.movieapp.model;

import java.util.List;

/**
 * Created by lduf0001 on 09/10/2016.
 */

public class Genres {

    public List<Genre> getGenres() {
        return genres;
    }

    private List<Genre> genres;

    public class Genre {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public String getGenreById(String id){
        for(Genre genre : genres){
            if(genre.id.equals(id)){
                return genre.getName();
            }
        }
        return "";
    }
}
