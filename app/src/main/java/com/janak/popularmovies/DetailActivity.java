package com.janak.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.janak.popularmovies.model.Movie;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedActivity = getIntent();

        if (intentThatStartedActivity != null) {
            if (intentThatStartedActivity.hasExtra("Movie")) {
                Movie detailMovie = (Movie) intentThatStartedActivity.getSerializableExtra("Movie");
                Toast.makeText(this, detailMovie.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
