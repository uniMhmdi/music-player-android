package com.example.musicplayer.models;

import com.google.gson.annotations.SerializedName;

public class TrendArtist {

    private String fullName;
    private String sumSongsDownloadsCount;
    @SerializedName("image")
    private Image cover;
    private String followersCount;

    public TrendArtist(String fullName, String sumSongsDownloadsCount, Image cover, String followersCount) {
        this.fullName = fullName;
        this.sumSongsDownloadsCount = sumSongsDownloadsCount;
        this.cover = cover;
        this.followersCount = followersCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSumSongsDownloadsCount() {
        return sumSongsDownloadsCount;
    }

    public void setSumSongsDownloadsCount(String sumSongsDownloadsCount) {
        this.sumSongsDownloadsCount = sumSongsDownloadsCount;
    }

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }
}
