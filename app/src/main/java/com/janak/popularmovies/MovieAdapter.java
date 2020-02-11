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
    ArrayList<Movie> movieArrayList;

    public static final String MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";

    public MovieAdapter(Context mContext, ArrayList<Movie> movieArrayList) {
        this.mContext = mContext;
        this.movieArrayList = movieArrayList;
    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Movie getItem(int i) {
        return movieArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView mImageView;
        Movie mMovie = getItem(position);

        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageView.setAdjustViewBounds(true);
            relativeLayout.addView(mImageView);
        } else {
            mImageView = (ImageView) convertView;
        }

        Picasso.get()
                .load(MOVIE_POSTER_BASE_URL + mMovie.getmPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(mImageView);

        return mImageView;
    }
}
