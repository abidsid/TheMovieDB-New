package com.example.abjs.moviedb.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abjs.moviedb.Interface.ApiInterface;
import com.example.abjs.moviedb.Model.ApiClient;
import com.example.abjs.moviedb.Model.Movie;
import com.example.abjs.moviedb.Adapter.MovieAdapter;
import com.example.abjs.moviedb.Model.MovieResponse;
import com.example.abjs.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// Display the details of the movie
public class Dsp extends AppCompatActivity {
    TextView tv;
    TextView tv2;
    TextView tv3;
    ImageView imgv;
    RecyclerView recv;
    MovieAdapter madapter;
    String api_key = "75d6a21a24cc88a4b60fcd0246f02ad1";
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsp);
        tv = (TextView) findViewById(R.id.moviename);
        tv2= (TextView) findViewById(R.id.overview);
        tv3 = (TextView)findViewById(R.id.rating);
        imgv= (ImageView)findViewById(R.id.imgv);

        List<Movie> movieList = new ArrayList<>();
        Intent intentthattostartthisactivity = getIntent();
        String title = getIntent().getExtras().getString("title");
        tv.setText(title);
        String poster = getIntent().getExtras().getString("poster");
        String imageurl = String.format("http://image.tmdb.org/t/p/w185"+poster);
        Picasso.with(this).load(imageurl).resize(185,200).into(imgv);
        int id = getIntent().getExtras().getInt("id");
        String idstring = Integer.toString(id);
        String overview = getIntent().getExtras().getString("overview");
        tv2.setText(overview);
        String rating = getIntent().getExtras().getString("rating");
        tv3.setText(rating);
        recv=(RecyclerView)findViewById(R.id.recview);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(this);
        recv.setLayoutManager(layoutmanager);
        ApiInterface apiInterface  = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getSimilarMovies(idstring,api_key);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies  = response.body().getResults();
                MovieAdapter mvadapter = new MovieAdapter(movies, R.layout.row_display_similar, getApplicationContext());
                recv.setAdapter(mvadapter);
                //Log.d(TAG, "Number of movies received: " + movies.size());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }
}
