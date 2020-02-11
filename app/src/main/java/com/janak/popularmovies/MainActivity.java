package com.janak.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    String mPopularMoviesURL;
    String mTopRatedMoviesURL;

    String YOUR_API_KEY = BuildConfig.YOUR_API_KEY;

    ArrayList<Movie> mPopularMovieList;
    ArrayList<Movie> mTopRatedMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE);

        new FetchMovies().execute();

        mGridView.setOnItemClickListener((adapterView, view, i, l) -> {
            Movie clickedMovie = (Movie) adapterView.getItemAtPosition(i);
            Class destinationClass = DetailActivity.class;
            Intent intentThatWillStartActivity = new Intent(MainActivity.this, destinationClass);
            intentThatWillStartActivity.putExtra(getString(R.string.current_movie), clickedMovie);
            startActivity(intentThatWillStartActivity);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pop_movies) {
            getSupportActionBar().setTitle(R.string.pop_movie_text);
            refreshList(mPopularMovieList);
        }
        if (id == R.id.top_movies) {
            getSupportActionBar().setTitle(R.string.top_movie_text);
            refreshList(mTopRatedMovieList);
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList(ArrayList<Movie> movieArrayList) {
        MovieAdapter mMovieAdapter = new MovieAdapter(MainActivity.this, movieArrayList);
        mGridView.invalidateViews();
        mGridView.setAdapter(mMovieAdapter);
    }


    // AsyncTask
    public class FetchMovies extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mPopularMoviesURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + YOUR_API_KEY;

            mTopRatedMoviesURL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + YOUR_API_KEY;

            mPopularMovieList = new ArrayList<>();
            mTopRatedMovieList = new ArrayList<>();
            try {
                if (NetworkUtils.networkStatus(MainActivity.this)) {
                    mPopularMovieList = NetworkUtils.fetchData(mPopularMoviesURL);
                    mTopRatedMovieList = NetworkUtils.fetchData(mTopRatedMoviesURL);

                } else {
                    Toast.makeText(MainActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
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
            refreshList(mPopularMovieList);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
