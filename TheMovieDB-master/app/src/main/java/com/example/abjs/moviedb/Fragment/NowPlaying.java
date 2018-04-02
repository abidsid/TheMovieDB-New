package com.example.abjs.moviedb.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.abjs.moviedb.Activities.MainActivity;
import com.example.abjs.moviedb.Interface.ApiInterface;
import com.example.abjs.moviedb.Model.ApiClient;
import com.example.abjs.moviedb.Model.Movie;
import com.example.abjs.moviedb.Adapter.MovieAdapter;
import com.example.abjs.moviedb.Model.MovieResponse;
import com.example.abjs.moviedb.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowPlaying.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowPlaying#newInstance} factory method to
 * create an instance of this fragment.
 */

// Display the Now Playing movies in the fragment on the Now Playing tab
public class NowPlaying extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    MovieAdapter madapter;
    //DisplayAdapter dAdapter;
    RecyclerView recyclerView;

    MenuItem menuItem;
    Movie movie;
    String newQuery;
    Context context;
    // String UrlString = "https://api.themoviedb.org/3/search/movie?api_key=cb8b839b126ae6fbe72cadb9c7805bb3&language=en-US";

    //String UrlString = "";
    List<Movie> movieList = new ArrayList<>();
    String TAG = MainActivity.class.getSimpleName();
    String api_key = "75d6a21a24cc88a4b60fcd0246f02ad1";
    Toolbar toolbar;
    TabLayout tabLayout;
    private ViewPager viewPager;

    public NowPlaying() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlaying.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlaying newInstance(String param1, String param2) {
        NowPlaying fragment = new NowPlaying();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_now_playing, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        //madapter = new MovieAdapter(movieList);
        //dAdapter = new DisplayAdapter(movieList);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        ApiInterface apiInterface  = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getnowplaying(api_key);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies  = response.body().getResults();
                MovieAdapter mvadapter = new MovieAdapter(movies, R.layout.rec_row_layout, getContext());
                recyclerView.setAdapter(mvadapter);

                //Log.d(TAG, "Number of movies received: " + movies.size());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
