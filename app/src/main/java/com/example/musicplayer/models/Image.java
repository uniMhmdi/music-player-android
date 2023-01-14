package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.smallCover, flags);
        dest.writeParcelable(this.bigCover, flags);
    }

    public void readFromParcel(Parcel source) {
        this.smallCover = source.readParcelable(Cover.class.getClassLoader());
        this.bigCover = source.readParcelable(Cover.class.getClassLoader());
    }

    protected Image(Parcel in) {
        this.smallCover = in.readParcelable(Cover.class.getClassLoader());
        this.bigCover = in.readParcelable(Cover.class.getClassLoader());
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
