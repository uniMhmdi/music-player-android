package com.example.musicplayer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Song {

    @SerializedName("downloadCount")
    private String playCount;

    @SerializedName("duration")
    private int songDuration;

    @SerializedName("title")
    private String songName;

    @SerializedName("image")
    private Image coverArt;

    private String releaseDate;

    @SerializedName("artists")
    private List<Artist> artistList;

    private Audio audio ;

    public Song(String playCount, int songDuration, String songName, Image coverArt, String releaseDate, List<Artist> artistList, Audio audio) {
        this.playCount = playCount;
        this.songDuration = songDuration;
        this.songName = songName;
        this.coverArt = coverArt;
        this.releaseDate = releaseDate;
        this.artistList = artistList;
        this.audio = audio;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Image getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(Image coverArt) {
        this.coverArt = coverArt;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }
}
