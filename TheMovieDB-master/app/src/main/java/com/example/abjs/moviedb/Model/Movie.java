package com.example.abjs.moviedb.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajuna on 3/3/2018.
 */

public class Movie {
    String title,vote_average,release_date,poster_path,overview,popularity;
    List<MovieGenre> movie_genre= new ArrayList<MovieGenre>();
    int id;

    public Movie(){}
    public Movie(String Title, String vote_average, String release_date,String poster_path,String overview, String popularity,int id,List<MovieGenre> movie_genre)
    {
        this.title = Title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.overview= overview;
        this.popularity = popularity;
        this.id = id;
        this.movie_genre=movie_genre;



    }

    public List<MovieGenre> getMovie_genre() {
        return movie_genre;
    }

    public void setMovie_genre(List<MovieGenre> movie_genre) {
        this.movie_genre = movie_genre;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {return poster_path;}

    public void setPoster_path(String poster_path) {this.poster_path = poster_path;}
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
