package com.janak.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.janak.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
    @BindView(R.id.movie_title)
    TextView mMovieTitle;
    @BindView(R.id.movie_rating)
    TextView mMovieRating;
    @BindView(R.id.movie_synopsis)
    TextView mMovieSynopsis;
    @BindView(R.id.movie_released)
    TextView mMovieReleaseData;
    @BindView(R.id.movie_poster)
    ImageView mMoviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intentThatStartedActivity = getIntent();

        if (intentThatStartedActivity != null) {
            if (intentThatStartedActivity.hasExtra(getString(R.string.current_movie))) {
                Movie detailMovie = (Movie) intentThatStartedActivity.getParcelableExtra(getString(R.string.current_movie));
                displayData(detailMovie);
            }
        }

    }

    private void displayData(Movie detailMovie) {
        getSupportActionBar().setTitle(getString(R.string.movie_detail));

        mMovieTitle.setText(detailMovie.getmTitle());
        mMovieRating.setText(detailMovie.getmVoteAverage() + "/10");
        mMovieReleaseData.setText("(" + detailMovie.getReleaseYear() + ")");
        mMovieSynopsis.setText(detailMovie.getmOverview());
        Picasso.get()
                .load(MOVIE_POSTER_BASE_URL + detailMovie.getmPosterPath())
                .into(mMoviePoster);
    }
}
