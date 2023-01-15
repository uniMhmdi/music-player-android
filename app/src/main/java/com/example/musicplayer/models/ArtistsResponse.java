package com.example.musicplayer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistsResponse {
    @SerializedName("results")
    private List<TrendArtist> trendArtistList;

    public ArtistsResponse(List<TrendArtist> trendArtistList) {
        this.trendArtistList = trendArtistList;
    }

    public List<TrendArtist> getTrendArtistList() {
        return trendArtistList;
    }

    public void setTrendArtistList(List<TrendArtist> trendArtistList) {
        this.trendArtistList = trendArtistList;
    }
}
