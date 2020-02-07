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
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtil.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseJson(results,movies);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static void parseJson(String results, ArrayList<Movie> movies) {
        try {
            JSONObject mainObject = new JSONObject(results);

            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                Movie movie = new Movie(); //New Movie object
                movie.setId(jsonObject.getInt("id"));
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.optString("original_title"));
                movie.setTitle(jsonObject.optString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.optString("backdrop_path"));
                movie.setOverview(jsonObject.optString("overview"));
                movie.setReleaseDate(jsonObject.optString("release_date"));
                movie.setPosterPath(jsonObject.optString("poster_path"));
                //Adding a new movie object into ArrayList
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Erro occurred during JSON Parsing", e);
        }
    }

    public static Boolean networkStatus(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConnectivityManager.getActiveNetworkInfo() != null && mConnectivityManager.getActiveNetworkInfo().isConnected();
    }
}
