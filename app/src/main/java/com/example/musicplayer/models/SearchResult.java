package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchResult implements Parcelable {
    private String type;
    private Song song ;

    public SearchResult(String type, Song song) {
        this.type = type;
        this.song = song;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.song, flags);
    }

    public void readFromParcel(Parcel source) {
        this.type = source.readString();
        this.song = source.readParcelable(Song.class.getClassLoader());
    }

    protected SearchResult(Parcel in) {
        this.type = in.readString();
        this.song = in.readParcelable(Song.class.getClassLoader());
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };
}
