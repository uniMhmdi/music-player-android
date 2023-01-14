package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cover implements Parcelable {

    private String url ;

    public Cover(String url) {
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

    protected Cover(Parcel in) {
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Cover> CREATOR = new Parcelable.Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel source) {
            return new Cover(source);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };
}
