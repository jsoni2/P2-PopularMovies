package com.janak.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.janak.popularmovies.model.Movie;
import com.janak.popularmovies.utilities.NetworkUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    @BindView(R.id.gv_movies_grid)
    GridView mGridView;

    String popularMoviesURL;
    String topRatedMoviesURL;

    String YOUR_API_KEY = BuildConfig.YOUR_API_KEY;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopRatedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE);
        new FetchMovies().execute();
    }

    // AsyncTask
    public class FetchMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+YOUR_API_KEY;

            topRatedMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+YOUR_API_KEY;

            mPopularList = new ArrayList<>();
            mTopRatedList = new ArrayList<>();
            try {
                if (NetworkUtils.networkStatus(MainActivity.this)){
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL);
                    mTopRatedList = NetworkUtils.fetchData(topRatedMoviesURL);
                    for (Movie movie: mPopularList) {
                        Log.d( "doInBackground:Popular", movie.toString());
                    }
                    Log.d( "doInBackground:Popular", String.valueOf(mPopularList.size()));
                    for (Movie movie: mTopRatedList) {
                        Log.d( "doInBackground:TopRated", movie.toString());
                    }
                    Log.d( "doInBackground:TopRated", String.valueOf(mTopRatedList.size()));

                } else {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
