package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.musicplayer.api.RetrofitClient;
import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.Song;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {



    private RetrofitInterface retrofitInterface;
    private List<Song> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        getMusics();

    }

    private void getMusics() {
        retrofitInterface.getLatestMusics().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, @NonNull Response<Object> response) {
                Toast.makeText(HomeActivity.this, "ارتباط برقرار شد", Toast.LENGTH_SHORT).show();
                if (response.body() != null) {
//                    for (Song song : response.body()) {
////                        adapter.addMusic(music);
//                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "ارتباط شما با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        list = new ArrayList<>();

    }

}