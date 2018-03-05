package com.example.android.popularmoviesearch;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by karenulmer on 2/18/2018.
 *
 * Contemplating between extends ArrayAdapter and BaseAdapter (used ArrayAdapter but BaseAdapter
 * maybe what I need. But I have not figured out how to go about it).
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> implements View.OnClickListener {



    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private static final int VIEW_TYPE_MOVIE_LIST = 0;
    private static final int VIEW_TYPE_MOVIE_DETAILS = 1;

    /* The context we use to utility methods, app resources and layout inflaters */
    private Context mContext;
    private Cursor mCursor;

    final private MovieAdapterOnClickHandler mClickHandler;

    private List<Movie> mMoviesList;


    private  ImageView posterImageView;

    private TextView dateTextView;
    private TextView overviewTextView;
    private TextView ratingTextView;
    private TextView titleTextView;

    RecyclerView mRecyclerView;
    MovieAdapterViewHolder mViewHolder;



    /**
     * The interface that receives onClick messages.
     */
    public interface MovieAdapterOnClickHandler {
        void onClick();
    }

   public MovieAdapter(MovieAdapterOnClickHandler clickHandler ) {
       mClickHandler = clickHandler;

    //}
    //public MovieAdapter (List<Movie>movies, MovieAdapterOnClickHandler clickHandler ){
    // mMoviesList = movies;
     //mClickHandler = clickHandler;
    }


    //@Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            mContext = viewGroup.getContext();
            int layoutId;

            switch (viewType) {
                case VIEW_TYPE_MOVIE_LIST: {
                    layoutId = R.layout.movie_list_item;
                    break;

                }
                case VIEW_TYPE_MOVIE_DETAILS: {
                    layoutId = R.layout.movie_details;
                    break;
                }
                default:
                    throw new IllegalArgumentException("Invalid view type, value of " + viewType);
            }

            View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
            view.setFocusable(true);

             return new MovieAdapterViewHolder(view);
    }


    @Override
    public int getItemCount() {return 0;}

    class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        public Movie mMovie;

        //public final ImageView posterImageView;

        //public final TextView dateTextView;
        //final TextView overviewTextView;
        //final TextView ratingTextView;

        MovieAdapterViewHolder(View view) {
            super(view);

            ratingTextView = view.findViewById(R.id.tv_rating);
            dateTextView = view.findViewById(R.id.tv_date);
            posterImageView = view.findViewById(R.id.movie_poster);
            overviewTextView=view.findViewById(R.id.tv_summary);
            titleTextView=view.findViewById(R.id.title);

        }


            /**
             * This gets called by the child views during a click. We fetch the date that has been
             * selected, and then call the onClick handler registered with this adapter, passing that
             * date.
             *
             * @param v the View that was clicked
             */
        }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_MOVIE_LIST: {
                showMovieList(holder);
                break;
            }
            case VIEW_TYPE_MOVIE_DETAILS: {
                showMovieDetails(holder);
                break;
            }

        }
    }

    public void showMovieDetails(MovieAdapterViewHolder holder){
        dateTextView.setText(holder.mMovie.getReleaseDate());
        ratingTextView.setText(holder.mMovie.getVoteAverage());
        overviewTextView.setText(holder.mMovie.getOverview());
        titleTextView.setText(holder.mMovie.getOverview());

        Picasso.with(mContext).setLoggingEnabled(true);

        Picasso.with(mContext)
                .load(holder.mMovie.getPosterPath())
                .into(posterImageView);
    }

    public void showMovieList(MovieAdapterViewHolder holder){
        dateTextView.setText(holder.mMovie.getReleaseDate());
        ratingTextView.setText(holder.mMovie.getVoteAverage());


        Picasso.with(mContext).setLoggingEnabled(true);

        Picasso.with(mContext)
                .load(holder.mMovie.getPosterPath())
                .into(posterImageView);

    }

    int viewType;

    @Override
        public void onClick(View v) {
            //int adapterPosition = getAdapterPosition();
            //String  movieList = mMoviesList[adapterPosition];
            //mClickHandler.onClick(moviesList);

            switch (viewType) {
                case VIEW_TYPE_MOVIE_LIST: {
                    mClickHandler.onClick();
                    break;
                }
            case VIEW_TYPE_MOVIE_DETAILS:
                break;
        }
                return;


   // public void setMovieList(String[] mMoviesList) {
   //     mMoviesList = movieList;
    //    notifyDataSetChanged();
    }

}