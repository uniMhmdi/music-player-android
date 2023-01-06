package com.example.musicplayer.models;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("cover_small")
    private Cover smallCover ;

    @SerializedName("cover")
    private Cover bigCover ;

    public Image(Cover smallCover, Cover bigCover) {
        this.smallCover = smallCover;
        this.bigCover = bigCover;
    }

    public Cover getSmallCover() {
        return smallCover;
    }

    public void setSmallCover(Cover smallCover) {
        this.smallCover = smallCover;
    }

    public Cover getBigCover() {
        return bigCover;
    }

    public void setBigCover(Cover bigCover) {
        this.bigCover = bigCover;
    }
}
