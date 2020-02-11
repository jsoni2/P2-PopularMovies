package com.janak.popularmovies.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.janak.popularmovies.model.Movie;

import org.apache.commons.io.IOUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static ArrayList<Movie> fetchData(String url) throws IOException {
        ArrayList<Movie> mMovieList = new ArrayList<Movie>();
        try {

            URL new_url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            String mResultString = IOUtil.toString(inputStream);
            parseJson(mResultString, mMovieList);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mMovieList;
    }

    private static void parseJson(String mResultString, ArrayList<Movie> mMovieList) {
        try {
            JSONObject mainObject = new JSONObject(mResultString);

            JSONArray resultArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject jsonObject = resultArray.getJSONObject(i);
                Movie mMovie = new Movie();

                mMovie.setmId(jsonObject.getInt("id"));
                mMovie.setmVoteAverage(jsonObject.getInt("vote_average"));
                mMovie.setmVoteCount(jsonObject.getInt("vote_count"));
                mMovie.setmOriginalTitle(jsonObject.optString("original_title"));
                mMovie.setmTitle(jsonObject.optString("title"));
                mMovie.setmPopularity(jsonObject.getDouble("popularity"));
                mMovie.setmBackdropPath(jsonObject.optString("backdrop_path"));
                mMovie.setmOverview(jsonObject.optString("overview"));
                mMovie.setmReleaseDate(jsonObject.optString("release_date"));
                mMovie.setmPosterPath(jsonObject.optString("poster_path"));

                mMovieList.add(mMovie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error occurred during JSON Parsing", e);
        }
    }

    public static Boolean networkStatus(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConnectivityManager.isDefaultNetworkActive();
    }
}
