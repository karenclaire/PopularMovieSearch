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

/**
 * Created by karenulmer on 2/18/2018.
 *
 * Contemplating between extends ArrayAdapter and BaseAdapter (used ArrayAdapter but BaseAdapter
 * maybe what I need. But I have not figured out how to go about it).
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>implements View.OnClickListener {



    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private static final int VIEW_TYPE_MOVIE_LIST = 0;
    private static final int VIEW_TYPE_MOVIE_DETAILS = 1;

    /* The context we use to utility methods, app resources and layout inflaters */
    private Context mContext;
    private Cursor mCursor;

    final private MovieAdapterOnClickHandler mClickHandler;
    private boolean mUseDetailLayout;

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

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
       mClickHandler = clickHandler;
        //mUseDetailLayout = mContext.getResources().getBoolean(R.bool.use_detail_layout);
    }


    //@Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

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

             return new MovieAdapterViewHolder(viewGroup);
    }


    @Override
    public int getItemCount() {return 0;
    }

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
                //dateTextView.setText(holder.mMovie.getReleaseDate());
                //ratingTextView.setText(holder.mMovie.getVoteAverage());

                //Picasso.with(mContext).setLoggingEnabled(true);

                //Picasso.with(mContext)
                //        .load(holder.mMovie.getImageUrl())
                //        .into(posterImageView);
                showMovieDetails(holder);
                break;
            }
            case VIEW_TYPE_MOVIE_DETAILS: {

                //titleTextView.setText(holder.mMovie.getTitle());
                //dateTextView.setText(holder.mMovie.getReleaseDate());
                //ratingTextView.setText(holder.mMovie.getVoteAverage());
                //overviewTextView.setText(holder.mMovie.getOverview());

                //Picasso.with(mContext).setLoggingEnabled(true);

                //Picasso.with(mContext)
                //        .load(holder.mMovie.getImageUrl())
                //        .into(posterImageView);
                showMovieList(holder);
                break;
            }

        }
    }

    private void showMovieDetails(MovieAdapterViewHolder holder){
        dateTextView.setText(holder.mMovie.getReleaseDate());
        ratingTextView.setText(holder.mMovie.getVoteAverage());

        Picasso.with(mContext).setLoggingEnabled(true);

        Picasso.with(mContext)
                .load(holder.mMovie.getImageUrl())
                .into(posterImageView);
    }

    private void showMovieList(MovieAdapterViewHolder holder){
        dateTextView.setText(holder.mMovie.getReleaseDate());
        ratingTextView.setText(holder.mMovie.getVoteAverage());

        Picasso.with(mContext).setLoggingEnabled(true);

        Picasso.with(mContext)
                .load(holder.mMovie.getImageUrl())
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



//public class MovieAdapter extends ArrayAdapter<Movie> {

//    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();
//    private static final int VIEW_TYPE_MOVIE_LIST = 0;
//    private static final int VIEW_TYPE_MOVIE_DETAILS = 1;


    /**
     * ViewHolder for fields of the movie
     */
 /**   static class ViewHolder {
        private ImageView posterImageView;
       private TextView titleTextView;
        private TextView dateTextView;
        private TextView overviewTextView;
        private TextView ratingTextView;
    }
  **/


    /**
     * @param context The current context. Used to inflate the layout file.
     * @param movies  A List of movies objects to display in a list.
     */
 /**   private Context mContext;

    public MovieAdapter(Activity context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }
/**/

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

            /**@NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup viewGroup ) {

             public View chooseView int position, @Nullable View convertView, @NonNull ViewGroup viewGroup )
                View movieGridView = convertView;

                int layoutId;

                final Movie currentMovie = getItem(position);
                RecyclerView.ViewHolder holder;


                if (movieGridView == null) {layoutInflater.from(getContext()).inflate(layoutId, viewGroup, false);
                    layoutId = R.layout.movie_list_item;
                    movieGridView = LayoutInflater.from(getContext()).inflate(layoutId, viewGroup, false);

                    holder = new ViewHolder();
                    holder.ratingTextView = (TextView) movieGridView.findViewById(R.id.tv_rating);
                    holder.dateTextView = (TextView) movieGridView.findViewById(R.id.tv_date);
                    holder.posterImageView = (ImageView) movieGridView.findViewById(R.id.movie_poster);
                    movieGridView.setTag(holder);
                }
                holder = (ViewHolder) movieGridView.getTag();


                holder.titleTextView.setText(currentMovie.getTitle());
                holder.dateTextView.setText(currentMovie.getReleaseDate());
                holder.ratingTextView.setText(currentMovie.getVoteAverage());
                holder.overviewTextView.setText(currentMovie.getOverview());

                Picasso.with(getContext()).setLoggingEnabled(true);

                Picasso.with(getContext())
                        .load(currentMovie.getImageUrl())
                        .into(holder.posterImageView);

                return movieGridView;

            }
            if (movieDetailsView ==null){


                   layoutId = R.layout.movie_details;
                    movieDetailsView = LayoutInflater.from(getContext()).inflate(layoutId, viewGroup, false);

                    holder = new ViewHolder();
                    holder.ratingTextView = (TextView) listItemView.findViewById(R.id.tv_rating);
                    holder.titleTextView = (TextView) listItemView.findViewById(R.id.title);
                    holder.dateTextView = (TextView) listItemView.findViewById(R.id.tv_date);
                    holder.posterImageView = (ImageView) listItemView.findViewById(R.id.movie_poster);
                    holder.ratingTextView = (TextView) listItemView.findViewById(R.id.tv_rating);
                    holder.overviewTextView = (TextView) listItemView.findViewById(R.id.tv_summary);

                    movieDetailsView.setTag(holder);


                        }
                    }
             /**/

        /**/
 /**/

}