package com.example.abjs.moviedb.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.abjs.moviedb.Fragment.NowPlaying;
import com.example.abjs.moviedb.Fragment.UpComing;
import com.example.abjs.moviedb.Interface.ApiInterface;
import com.example.abjs.moviedb.Model.ApiClient;
import com.example.abjs.moviedb.Model.Movie;
import com.example.abjs.moviedb.Adapter.MovieAdapter;
import com.example.abjs.moviedb.Model.MovieResponse;
import com.example.abjs.moviedb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
// Displaying 2 tabs with Now Playing and Upcoming Movies
public class MainPage extends AppCompatActivity {

    MovieAdapter madapter;
    //DisplayAdapter dAdapter;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    SearchView searchView;
    MenuItem menuItem;
    Movie movie;
    String newQuery;
    Context context;
    List<Movie> movieList = new ArrayList<>();
    String TAG = MainActivity.class.getSimpleName();
    String api_key = "75d6a21a24cc88a4b60fcd0246f02ad1";
    Toolbar toolbar;
    TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainPage.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        linearLayout = (LinearLayout)findViewById(R.id.ll1);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //recyclerView.setAdapter(madapter);
        if(api_key.isEmpty()){
            Toast.makeText(getApplicationContext(),"obatin a key first",Toast.LENGTH_LONG).show();
            return;
        }
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NowPlaying(), "Now Playing");
        adapter.addFragment(new UpComing(), "UpComing");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menuItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search all movies");
        menuItem.expandActionView();

        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query.toString());

                linearLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);



                if(TextUtils.isEmpty(query))
                {
                    newQuery="";
                }
                else
                {
                    movieList.clear();
                    newQuery = query;
                    //new DownloadJson().execute();
                    //recyclerView.setAdapter(mvadapter);

                    ApiInterface apiInterface  = ApiClient.getClient().create(ApiInterface.class);
                    Map<String,String> data = new HashMap<>();
                    data.put("query",query);

                    Call<MovieResponse> call2 = apiInterface.searchMovies(api_key,data);
                    call2.enqueue(new Callback<MovieResponse>() {
                        @Override
                        public void onResponse(Call<MovieResponse> call2, Response<MovieResponse> response) {
                            List<Movie> movies  = response.body().getResults();
//                            Log.d("movies", movies.get(0).getTitle());
                            MovieAdapter mvadapter = new MovieAdapter(movies, R.layout.rec_row_layout, getApplicationContext());
                            recyclerView.setAdapter(mvadapter);
                            //Log.d(TAG, "Number of movies received: " + movies.size());

                        }

                        @Override
                        public void onFailure(Call<MovieResponse> call2, Throwable t) {
                            Log.e(TAG, t.toString());
                        }
                    });


                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // return super.onCreateOptionsMenu(menu);
        return true;
    }
}
