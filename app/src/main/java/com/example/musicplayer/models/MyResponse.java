package com.example.musicplayer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResponse {
    @SerializedName("results")
    private List<Song> songs;

    public MyResponse(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
