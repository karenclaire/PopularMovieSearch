package com.example.android.popularmoviesearch;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//import com.example.android.popularmoviesearch.MovieAdapter.MovieAdapterOnClickHandler;

/**
 * Created by karenulmer on 2/20/2018.
 *
 * References used for coding guide and corrections:
 * 1) https://github.com/first087/Android-ViewHolder-Example/blob/master/app/src/main/java/com/artitk/android_viewholder_example/GridViewActivity.java
 * 2)https://github.com/ajinkya007/Popular-Movies-Stage-1
 * 3) https://github.com/bapspatil/FlickOff
 * 4)https://github.com/henriquenfaria/popular-movies-stage-1
 * 5) my previous udacity projects: NewsApp and BookApp
 * 6) https://googledevndscholars.slack.com/threads/
 * 7)https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
 */



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>, MovieAdapter.MovieAdapterOnClickListener{


    /**
     * Tag for log messages
     */
    public static final String LOG_TAG = MainActivity.class.getName();
    /**
     * URL for Movie API for tmdb
     */
    private static final String MOVIE_REQUEST_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";
    /**
     * Constant value for the movie loader ID
     */
    private static final int MOVIE_LOADER_ID = 1;

    /**
     * Movies List
     */
    public ArrayList<Movie> moviesList;

    /**
     * Adapter for the list of movies
     */
    public MovieAdapter mMovieAdapter;

    public Movie mMovie;

    public Context mContext;

    public MovieAdapter.MovieAdapterOnClickListener listener;
    /**
     * SearchView that takes the query
     */
    //private SearchView searchView;

    /**
     * List of movies
     */
    private GridView movieGridView;

      /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    /**
     * ProgressBar that is displayed when the data is loaded
     */
    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;
    //private MovieAdapterOnClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link SearchView} in the layout
        //searchView = (SearchView) findViewById(R.id.search_view);

        // Create a new adapter that takes an empty list of movies as input
        //mMovieAdapter = new MovieAdapter();


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected movie.
        // Find a reference to the {@link movieGridView} in the layout
        mRecyclerView = findViewById(R.id.recyclerview_grid);

        GridLayoutManager layoutManager = new  GridLayoutManager(this,2);

        mRecyclerView.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true);
        //GridView gridView = findViewById(R.id.movie_list);

        //movieGridView= gridView;

        // Create a new adapter that takes a list of movies as input
        mMovieAdapter = new MovieAdapter(this, new ArrayList<Movie>(),listener);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mMovieAdapter);

        /**movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


             @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Movie currentMovie = mMovie;
                mMovie = moviesList.get(position);
                Uri movieUri = Uri.parse(currentMovie.getPosterPath());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, movieUri);
                webIntent.putExtra("movie_id", moviesList.get(position).getId());
                webIntent.putExtra("movie_position", position);
                startActivity(webIntent);
            }
        });**/

        // Find the reference to the progress bar in a layout
        mProgressBar = findViewById(R.id.pb_loading_indicator);
        // Find the reference to the empty text view in a layout and set empty view
        mEmptyStateTextView = findViewById(R.id.empty_view);
        //movieGridView.setEmptyView(mEmptyStateTextView);


        if (isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);

        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }


    // Helper method to check network connection
    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        String requestUrl = MOVIE_REQUEST_URL;
        return new MovieLoader(this, requestUrl);

    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {

        if (movies != null && !movies.isEmpty()) {
            mEmptyStateTextView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
            mMovieAdapter.setMovieData(moviesList);

        } else
            //if (movies == null && movies.isEmpty()){
            mEmptyStateTextView.setText(R.string.no_movies);
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.INVISIBLE);
            //}else


        }



    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
       return;
        }

    @Override
    public void onMovieClick(int clickMovieIndex) {
        Movie currentMovie = mMovie;
        mMovie = moviesList.get(clickMovieIndex);
        Uri movieUri = Uri.parse(currentMovie.getPosterPath());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, movieUri);
        webIntent.putExtra("movie_id", moviesList.get(clickMovieIndex).getId());
        webIntent.putExtra("movie_position", clickMovieIndex);
        startActivity(webIntent);



    }

}