package com.example.musicplayer.api;

import com.example.musicplayer.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    // get new song
    @GET("v1/song/new/0/11")
    Call<List<Song>> getMusics();

//    @POST("search.php")
//    Call<List<Music>> search(@Query(DATABASE_KEY_SEARCH) String key);

//    @POST("add_music.php")
//    Call<Result> addMusic(@Query(SONG_NAME) String songName, @Query(SINGER_NAME) String singerName, @Query(COVER_LINK) String coverLink, @Query(PLAY_LINK) String playLink, @Query(DURATION) String duration);
}
