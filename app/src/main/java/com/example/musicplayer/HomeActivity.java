package com.example.musicplayer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.adapter.SongAdapter;
import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.MyResponse;
import com.example.musicplayer.models.Song;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private RetrofitInterface retrofitInterface;
    private RecyclerView latestRv;
    private RecyclerView trendingRv;
    private RecyclerView topDayRv;
    private SongAdapter latestAdapter;
    private SongAdapter trendingAdapter;
    private SongAdapter topDayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        getMusics();

    }

    private void getMusics() {
        retrofitInterface.getLatestSongs().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.body() != null) {
                    int i = 0;
                    for (Song song : response.body().getSongs()) {
                        Log.i(TAG, (i + 1) + "th song :");
                        Log.i(TAG, "play count : " + song.getPlayCount());
                        Log.i(TAG, "duration in second : " + song.getSongDuration());
                        Log.i(TAG, "song name : " + song.getSongName());
                        Log.i(TAG, "cover art url : " + song.getCoverArt().getBigCover().getUrl());
                        Log.i(TAG, "release date : " + song.getReleaseDate());
                        Log.i(TAG, "artist name : " + song.getArtistList().get(0).getFullName());
                        Log.i(TAG, "audio url : " + song.getAudio().getHigh().getUrl());
                        i++;
                        Log.i(TAG, "================================================");

                        latestAdapter.addSong(song);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
                Toast.makeText(HomeActivity.this, "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();
            }
        });

//        retrofitInterface.getTrendingArtist().enqueue(new Callback<MyResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
//                if (response.body() != null) {
//                    for (Song song : response.body().getSongs()) {
//                        trendingAdapter.addSong(song);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
//                Toast.makeText(HomeActivity.this, "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();
//            }
//        });

        retrofitInterface.getTopDaySongs().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.body() != null) {
                    for (Song song : response.body().getSongs()) {
                        topDayAdapter.addSong(song);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyResponse> call, @NonNull Throwable t) {
                Toast.makeText(HomeActivity.this, "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init() {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        latestRv = findViewById(R.id.rv_latest);
        trendingRv = findViewById(R.id.rv_trending_artist);
        topDayRv = findViewById(R.id.rv_top10_day);
        latestRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trendingRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topDayRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        latestAdapter = new SongAdapter(new ArrayList<>());
        trendingAdapter = new SongAdapter(new ArrayList<>());
        topDayAdapter = new SongAdapter(new ArrayList<>());
        latestRv.setAdapter(latestAdapter);
        trendingRv.setAdapter(trendingAdapter);
        topDayRv.setAdapter(topDayAdapter);


    }

}