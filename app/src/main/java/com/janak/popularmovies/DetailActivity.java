package com.janak.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.janak.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

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

    public static final String MOVIE_BASE_URL = "https://image.tmdb.org/t/p/w342";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intentThatStartedActivity = getIntent();

        if (intentThatStartedActivity != null) {
            if (intentThatStartedActivity.hasExtra("Movie")) {
                Movie detailMovie = (Movie) intentThatStartedActivity.getSerializableExtra("Movie");
                Toast.makeText(this, detailMovie.toString(), Toast.LENGTH_SHORT).show();
                displayData(detailMovie);

            }
        }

    }

    private void displayData(Movie detailMovie) {
        getSupportActionBar().setTitle("Movie Detail");
        mMovieTitle.setText(detailMovie.getTitle());
        mMovieRating.setText(detailMovie.getVoteAverage() + "/10");
        mMovieReleaseData.setText("(" + detailMovie.getReleaseYear() + ")");
        mMovieSynopsis.setText(detailMovie.getOverview());
        Picasso.get().load(MOVIE_BASE_URL + detailMovie.getPosterPath()).into(mMoviePoster);
    }
}
