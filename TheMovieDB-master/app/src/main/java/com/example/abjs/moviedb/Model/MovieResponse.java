package com.example.abjs.moviedb.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ajuna on 3/2/2018.
 */

public class MovieResponse {
    @SerializedName("page")
    int page;
    @SerializedName("results")
    List<Movie> results;
    @SerializedName("total_results")
    int totalresults;
    @SerializedName("total_pages")
    int totalpages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(int totalresults) {
        this.totalresults = totalresults;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    @SerializedName("title")
    String title;
    @SerializedName("vote_average")
    String vote_average;
    @SerializedName("release_date")
    String release_date;
    @SerializedName("poster_path")
    String poster_path;
    @SerializedName("overview")
    String overview;
    @SerializedName("popularity")
    String popularity;
    @SerializedName("id")
    int id;
    @SerializedName("genre")
    List<Integer> genre;

    public List<Integer> getGenre() {
        return genre;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
