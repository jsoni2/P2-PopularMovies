package com.janak.popularmovies.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private int mId;
    private  int mVoteAverage;
    private int mVoteCount;
    private String mOriginalTitle;
    private String mTitle;
    private double mPopularity;
    private String mBackdropPath;
    private String mOverview;
    private String mReleaseDate;
    private String mPosterPath;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(int mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public int getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public double getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getReleaseYear() {
        return this.mReleaseDate.split("-")[0];
    }

    @Override
    public String toString() {
        return "{\"Movie\":{"
                + "\"mId\":\"" + mId + "\""
                + ", \"mVoteAverage\":\"" + mVoteAverage + "\""
                + ", \"mVoteCount\":\"" + mVoteCount + "\""
                + ", \"mOriginalTitle\":\"" + mOriginalTitle + "\""
                + ", \"mTitle\":\"" + mTitle + "\""
                + ", \"mPopularity\":\"" + mPopularity + "\""
                + ", \"mBackdropPath\":\"" + mBackdropPath + "\""
                + ", \"mOverview\":\"" + mOverview + "\""
                + ", \"mReleaseDate\":\"" + mReleaseDate + "\""
                + ", \"mPosterPath\":\"" + mPosterPath + "\""
                + "}}";
    }
}
