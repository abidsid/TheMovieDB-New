package com.example.abjs.moviedb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abjs.moviedb.Activities.Dsp;
import com.example.abjs.moviedb.Model.Movie;
import com.example.abjs.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ajuna on 3/3/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
    List<Movie> movieList ;
    int rowlayout;
    Context context;
    public MovieAdapter(List<Movie> movielist,int rowlayout, Context context)
    {
        this.movieList = movielist;
        this.rowlayout = rowlayout;
        this.context = context;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView title,rating,popularity,genre;
        ImageView poster;
        public MyViewHolder(View v)
        {
            super(v);
            //v.setOnClickListener(this);
            //context = v.getContext();
            title = (TextView)v.findViewById(R.id.title);
            rating = (TextView)v.findViewById(R.id.rating);
            popularity = (TextView)v.findViewById(R.id.popularity);
            poster = (ImageView)v.findViewById(R.id.poster);
            v.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    int pos= getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        // Movie moviep = movieList.get(pos);


                        Intent intent = new Intent(context,Dsp.class);
//                        intent.putExtra("genre",movieList.get(pos).movie_genre.get(0).getId());
//                    intent.putExtra("genre_id",movieList.get(pos).movie_genre.getClass().getName());
                        intent.putExtra("title",movieList.get(pos).getTitle());
                        intent.putExtra("poster",movieList.get(pos).getPoster_path());
                        intent.putExtra("id",movieList.get(pos).getId());
                        intent.putExtra("overview",movieList.get(pos).getOverview());
                        intent.putExtra("rating",movieList.get(pos).getVote_average());
                        intent.putExtra("popularity",movieList.get(pos).getPopularity());
//                    intent.putExtra("genre",movieList.get(pos).getGenre());
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),"You Clicked" + clickedDataItem.getTitle(),Toast.LENGTH_LONG);

                    }
                }
            });

        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View itemv = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_row_layout,parent,false);


        return new MyViewHolder(itemv);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie moviep = movieList.get(position);
        holder.title.setText(moviep.getTitle());
        //String vote = moviep.getVote_average();
        //holder.rating.setText(vote);
        holder.rating.setText(moviep.getVote_average());
        holder.popularity.setText(moviep.getPopularity());
        String path = moviep.getPoster_path();
        String imageurl = String.format("http://image.tmdb.org/t/p/w185"+path);
        Picasso.with(context).load(imageurl).resize(185,200).into(holder.poster);
        // holder.poster.set(moviep.getPoster_path());




    }



    @Override
    public int getItemCount() {
        return movieList.size();

    }
}
