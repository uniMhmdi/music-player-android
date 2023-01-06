package com.example.musicplayer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.MyResponse;
import com.example.musicplayer.models.Song;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        getMusics();

    }

    private void getMusics() {
        retrofitInterface.getLatestMusics().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                Toast.makeText(HomeActivity.this, "ارتباط برقرار شد", Toast.LENGTH_SHORT).show();
                if (response.body() != null) {
                    int i = 0;
                    for (Song song : response.body().getSongs()) {
                        Log.i(TAG, (i+1)+"th song :");
                        Log.i(TAG, "play count : " + song.getPlayCount());
                        Log.i(TAG, "duration in second : " + song.getSongDuration());
                        Log.i(TAG, "song name : " + song.getSongName());
                        Log.i(TAG, "cover art url : " + song.getCoverArt().getBigCover().getUrl());
                        Log.i(TAG, "release date : " + song.getReleaseDate());
                        Log.i(TAG, "artist name : " + song.getArtistList().get(0).getFullName());
                        Log.i(TAG, "audio url : " + song.getAudio().getHigh().getUrl());
                        i++;
                        Log.i(TAG, "================================================");

//                        adapter.addMusic(music);
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

    }

}