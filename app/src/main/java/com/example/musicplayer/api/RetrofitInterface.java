package com.example.musicplayer.api;

import com.example.musicplayer.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    // get latest songs
    @GET("v1/song/new/0/11")
    Call<Object> getLatestMusics();

}
