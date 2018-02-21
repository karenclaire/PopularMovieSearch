package com.example.android.popularmoviesearch;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by karenulmer on 2/18/2018.
 *
 * Contemplating between extends ArrayAdapter and BaseAdapter (used ArrayAdapter but BaseAdapter
 * maybe what I need. But I have not figured out how to go about it).
 */

public class MovieAdapter extends ArrayAdapter<Movie> {


    private static final int VIEW_TYPE_MOVIE_LIST = 0;
    private static final int VIEW_TYPE_MOVIE_DETAILS = 1;

    /**
     * ViewHolder for fields of the movie
     */
    static class ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView overviewTextView;
        private TextView ratingTextView;
    }


    /**
     * @param context The current context. Used to inflate the layout file.
     * @param movies  A List of movies objects to display in a list.
     */

    public MovieAdapter(Activity context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }


    /**
     * @return The View for the position in the AdapterView.
     */

    /**
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param viewGroup   The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */


        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup viewGroup, int viewType) {

            View listItemView = convertView;
            int layoutId;

            final Movie currentMovie = getItem(position);
            ViewHolder holder;


            if (listItemView == null) {
                switch (viewType) {
                    case VIEW_TYPE_MOVIE_LIST: {
                        layoutId = R.layout.movie_list_item;
                        listItemView = LayoutInflater.from(getContext()).inflate(layoutId, viewGroup, false);

                        holder = new ViewHolder();
                        holder.ratingTextView = (TextView) listItemView.findViewById(R.id.tv_rating);
                        holder.dateTextView = (TextView) listItemView.findViewById(R.id.tv_date);
                        holder.posterImageView = (ImageView) listItemView.findViewById(R.id.movie_poster);
                        listItemView.setTag(holder);
                        break;

                    }
                    case VIEW_TYPE_MOVIE_DETAILS: {
                        layoutId = R.layout.movie_details;
                        listItemView = LayoutInflater.from(getContext()).inflate(layoutId, viewGroup, false);

                        holder = new ViewHolder();
                        holder.ratingTextView = (TextView) listItemView.findViewById(R.id.tv_rating);
                        holder.titleTextView = (TextView) listItemView.findViewById(R.id.title);
                        holder.dateTextView = (TextView) listItemView.findViewById(R.id.tv_date);
                        holder.posterImageView = (ImageView) listItemView.findViewById(R.id.movie_poster);
                        holder.ratingTextView = (TextView) listItemView.findViewById(R.id.tv_rating);
                        holder.overviewTextView = (TextView) listItemView.findViewById(R.id.tv_summary);

                        listItemView.setTag(holder);
                        break;

                    }
                }
            }
            holder = (ViewHolder) listItemView.getTag();


            holder.titleTextView.setText(currentMovie.getTitle());
            holder.dateTextView.setText(currentMovie.getReleaseDate());
            holder.ratingTextView.setText(currentMovie.getVoteAverage());
            holder.overviewTextView.setText(currentMovie.getOverview());

            Picasso.with(getContext()).setLoggingEnabled(true);

            Picasso.with(getContext())
                    .load(currentMovie.getImageUrl())
                    .into(holder.posterImageView);

            return listItemView;
        }

}
    /**
    public class MoviesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Movie currentMovie = getItem(position);


            ImageView posterImageView;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                posterImageView = (ImageView) inflater.inflate(R.layout.movie_list_item, parent, false);
            } else {
                posterImageView = (ImageView) convertView;
            }

            Picasso.with(getContext()).setLoggingEnabled(true);

            Picasso.with(getContext())
                    .load(currentMovie.getImageUrl())
                    .into(posterImageView);
            return null;
        }
    /**/
