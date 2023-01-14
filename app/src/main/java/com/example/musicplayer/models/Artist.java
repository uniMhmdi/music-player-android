package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements Parcelable {

    private String fullName ;

    public Artist(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullName);
    }

    public void readFromParcel(Parcel source) {
        this.fullName = source.readString();
    }

    protected Artist(Parcel in) {
        this.fullName = in.readString();
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
