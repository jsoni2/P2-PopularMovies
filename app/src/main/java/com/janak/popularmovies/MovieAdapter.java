package com.janak.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.janak.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<Movie> movies;

    public static final String MOVIE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    public MovieAdapter(Context mContext, ArrayList<Movie> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView mImageView;
        Movie movie = getItem(i);

        RelativeLayout mRelativeLayout = new RelativeLayout(mContext);
        mRelativeLayout.setLayoutParams(new ViewGroup.LayoutParams(200, 300));

        if (view == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageView.setAdjustViewBounds(true);
            mRelativeLayout.addView(mImageView);
        } else {
            mImageView = (ImageView) view;
        }

        Picasso.get().load(MOVIE_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(mImageView);

        return mImageView;
    }
}
