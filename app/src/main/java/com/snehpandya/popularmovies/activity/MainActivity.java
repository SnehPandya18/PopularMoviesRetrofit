package com.snehpandya.popularmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.apiservice.RetrofitBuilder;
import com.snehpandya.popularmovies.data.MoviesAdapter;
import com.snehpandya.popularmovies.model.Response;
import com.snehpandya.popularmovies.model.Result;
import com.snehpandya.popularmovies.util.InfiniteScollListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    int page = 1;

    Result mResult;
    GridView mGridView;
    MoviesAdapter mMoviesAdapter;
    ArrayList<Result> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = findViewById(R.id.gridview);
        mMoviesAdapter = new MoviesAdapter(this, new ArrayList<Result>());
        mGridView.setAdapter(mMoviesAdapter);
        getMovies(page);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mResult = mMoviesAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("movie", mResult);
                startActivity(intent);
            }
        });

        mGridView.setOnScrollListener(new InfiniteScollListener(2) {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void loadMore(int page, int totalItemsCount){
                getMovies(page);
            }
        });
    }

    private void getMovies(int page) {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        Call<Response> responseCall = retrofitBuilder.mTMDBApi.popularResponse(getString(R.string.API_KEY), page);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mMoviesAdapter.addAll(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);
            }
        });

    }
}
