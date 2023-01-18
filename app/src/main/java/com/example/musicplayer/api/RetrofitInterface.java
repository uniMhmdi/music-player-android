package com.example.musicplayer.api;

import com.example.musicplayer.models.ArtistsResponse;
import com.example.musicplayer.models.SearchResponse;
import com.example.musicplayer.models.Song;
import com.example.musicplayer.models.SongsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    // get top week songs
    @GET("v1/song/top/week/0/100")
    Call<SongsResponse> getTopWeekSongs();

    // get song bt id
    @GET("v1/song/{id}")
    Call<Song> getLyricSong(@Path("id") String songId);

    // search by user input
    @GET("v1/search/query/{keyword}/0/50")
    Call<SearchResponse> search(@Path("keyword") String keyword);

}
