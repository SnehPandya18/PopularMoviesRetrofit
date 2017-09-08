package com.snehpandya.popularmovies.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.snehpandya.popularmovies.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sneh.pandya on 08/09/17.
 */

public class MoviesAdapter extends ArrayAdapter<Result> {

    String url = "http://image.tmdb.org/t/p/w342";

    public MoviesAdapter(@NonNull Context context, @NonNull List<Result> movies) {
        super(context, 0, movies);
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(getContext());
        } else {
            imageView = (ImageView) view;
        }

        Result currentMovie = getItem(i);
        Picasso.with(getContext()).load(url + currentMovie.getPosterPath()).into(imageView);
        imageView.setAdjustViewBounds(true);
        return imageView;
    }
}
