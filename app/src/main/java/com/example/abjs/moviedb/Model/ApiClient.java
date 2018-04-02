package com.example.abjs.moviedb.Model;

import com.example.abjs.moviedb.Activities.UpdateConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ajuna on 3/1/2018.
 */

public class ApiClient {
   // public static final String Base_Url = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    public static String Base_Url ;
    static {
       Base_Url = UpdateConfig.getConfigValue(configValues.context,"URL");
    }

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
