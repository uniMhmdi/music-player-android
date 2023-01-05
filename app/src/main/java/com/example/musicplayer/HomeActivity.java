package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.musicplayer.api.RetrofitInterface;
import com.example.musicplayer.models.Song;

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
//        getMusics();

    }

    private void getMusics() {
        retrofitInterface.getMusics().enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                if (response.body() != null) {
                    for (Song music : response.body()) {
//                        adapter.addMusic(music);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "ارتباط شما با سرور برقرار نشد", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {

    }

}