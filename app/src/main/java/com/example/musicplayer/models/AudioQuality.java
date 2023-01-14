package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AudioQuality implements Parcelable {

    private String url ;

    public AudioQuality(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
    }

    public void readFromParcel(Parcel source) {
        this.url = source.readString();
    }

    protected AudioQuality(Parcel in) {
        this.url = in.readString();
    }

    public static final Parcelable.Creator<AudioQuality> CREATOR = new Parcelable.Creator<AudioQuality>() {
        @Override
        public AudioQuality createFromParcel(Parcel source) {
            return new AudioQuality(source);
        }

        @Override
        public AudioQuality[] newArray(int size) {
            return new AudioQuality[size];
        }
    };
}
