package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Audio implements Parcelable {
    private AudioQuality medium;
    private AudioQuality high;

    public Audio(AudioQuality medium, AudioQuality high) {
        this.medium = medium;
        this.high = high;
    }

    public AudioQuality getMedium() {
        return medium;
    }

    public void setMedium(AudioQuality medium) {
        this.medium = medium;
    }

    public AudioQuality getHigh() {
        return high;
    }

    public void setHigh(AudioQuality high) {
        this.high = high;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.high, flags);
    }

    public void readFromParcel(Parcel source) {
        this.medium = source.readParcelable(AudioQuality.class.getClassLoader());
        this.high = source.readParcelable(AudioQuality.class.getClassLoader());
    }

    protected Audio(Parcel in) {
        this.medium = in.readParcelable(AudioQuality.class.getClassLoader());
        this.high = in.readParcelable(AudioQuality.class.getClassLoader());
    }

    public static final Parcelable.Creator<Audio> CREATOR = new Parcelable.Creator<Audio>() {
        @Override
        public Audio createFromParcel(Parcel source) {
            return new Audio(source);
        }

        @Override
        public Audio[] newArray(int size) {
            return new Audio[size];
        }
    };
}
