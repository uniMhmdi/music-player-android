package com.example.musicplayer.api;

import com.example.musicplayer.models.ArtistsResponse;
import com.example.musicplayer.models.SongsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    // get latest songs
    @GET("v1/song/new/0/11")
    Call<SongsResponse> getLatestSongs();

    // get trending artists
    @GET("v1/artist/trending/0/4")
    Call<ArtistsResponse> getTrendingArtist();

    // get top day songs
    @GET("v1/song/top/day/0/100")
    Call<SongsResponse> getTopDaySongs();

    // get top day songs
    @GET("v1/song/top/week/0/100")
    Call<SongsResponse> getTopWeekSongs();

}
