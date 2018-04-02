package com.example.abjs.moviedb.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ajuna on 3/4/2018.
 */

public class MovieGenre {
    @SerializedName("id")
    private int id;


    public MovieGenre(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
