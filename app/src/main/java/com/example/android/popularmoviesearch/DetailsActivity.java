package com.example.android.popularmoviesearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by karenulmer on 2/20/2018.
 *
 * References used for coding guide and corrections:
 * 1) https://github.com/first087/Android-ViewHolder-Example
 * 2)https://github.com/ajinkya007/
 * 3) https://github.com/bapspatil
 * 4) my previous udacity projects: NewsApp and BookApp
 *
 * Work in progress! NEED TO FIGURE OUT WHAT IS WRONG WITH THIS...
 */

public class DetailsActivity extends AppCompatActivity {


    public static Movie movie;
    public static Intent intent;
    public static TextView title, releaseDate, rating, summary;
    public static ImageView movie_poster;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * NOT SURE IF THIS FITS MY CURRENT CODE... I just based this on someone else's work for
     * my reference and evaluate if this is what I need...
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.movie_details, container, false);
            WindowManager wm = (WindowManager) rootView.getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            initComponents(rootView);
            setValues(rootView);
            return rootView;
        }


        public void initComponents(View rootView) {
            DetailsActivity.intent = getActivity().getIntent();
            int id = intent.getIntExtra("movie_id", 0);
            int movie_position = intent.getIntExtra("movie_position", 0);
            movie = MainActivity.moviesList.get(movie_position);
            title = (TextView) rootView.findViewById(R.id.title);
            releaseDate = (TextView) rootView.findViewById(R.id.tv_date);
            rating = (TextView) rootView.findViewById(R.id.tv_rating);
            movie_poster = (ImageView) rootView.findViewById(R.id.movie_poster);
            summary = (TextView) rootView.findViewById(R.id.tv_summary);
        }

        public static void setValues(View rootView) {
            title.setText(movie.getTitle());
            releaseDate.setText(movie.getReleaseDate().substring(0, 4));
            title.setVisibility(View.VISIBLE);
            rating.setText(movie.getVoteAverage() + "/10");
            summary.setText(movie.getOverview());
            String imageUrl;
            if (movie.getPosterPath() == API.IMAGE_NOT_FOUND) {
                imageUrl = API.IMAGE_NOT_FOUND;
            } else {
                imageUrl = API.IMAGE_URL + API.IMAGE_SIZE_185 + "/" + movie.getPosterPath();
            }

            Picasso.with(rootView.getContext()).load(imageUrl).into(movie_poster);
            movie_poster.setVisibility(View.VISIBLE);

        }
    }
}


