package com.janak.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        mGridView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
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
            refreshList(mPopularList);
        }
        if (id == R.id.top_movies) {
            refreshList(mTopRatedList);
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList(ArrayList<Movie> list) {
        MovieAdapter adapter = new MovieAdapter(MainActivity.this, list);
        mGridView.invalidateViews();
        mGridView.setAdapter(adapter);
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
            refreshList(mPopularList);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
