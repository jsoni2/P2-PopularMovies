package com.janak.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;

    String popularMoviesURL;
    String topRatedMoviesURL;

    String YOUR_API_KEY = "67abda4d931f7a093afa16b89a87dcf9";

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    // AsyncTask
    public class FetchMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+YOUR_API_KEY;

            topRatedMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+YOUR_API_KEY;

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();
            
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
