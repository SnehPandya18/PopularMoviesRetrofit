package com.snehpandya.popularmovies.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.model.Result;
import com.squareup.picasso.Picasso;

/**
 * Created by sneh.pandya on 08/09/17.
 */

public class DetailActivity extends AppCompatActivity {

    TextView movieName;
    TextView movieDescription;
    TextView movieVote;
    TextView movieDate;
    ImageView imageView;

    Result mResult;

    String url = "http://image.tmdb.org/t/p/w342";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mResult = getIntent().getExtras().getParcelable("movie");

        movieName = findViewById(R.id.movie_title);
        movieDescription = findViewById(R.id.movie_description);
        movieVote = findViewById(R.id.movie_vote);
        movieDate = findViewById(R.id.movie_date);
        imageView = findViewById(R.id.movie_image);

        updateView();
    }

    private void updateView() {
        movieName.setText(mResult.getTitle());
        movieDescription.setText(mResult.getOverview());
        movieVote.setText(mResult.getVoteAverage());
        movieDate.setText(mResult.getReleaseDate());
        Picasso.with(this).load(url + mResult.getPosterPath()).into(imageView);
    }
}
