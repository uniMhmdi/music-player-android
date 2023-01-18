package com.example.musicplayer.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse implements Parcelable {
    @SerializedName("results")
    private List<SearchResult> searchResultList;

    public SearchResponse(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.searchResultList);
    }

    public void readFromParcel(Parcel source) {
        this.searchResultList = source.createTypedArrayList(SearchResult.CREATOR);
    }

    protected SearchResponse(Parcel in) {
        this.searchResultList = in.createTypedArrayList(SearchResult.CREATOR);
    }

    public static final Parcelable.Creator<SearchResponse> CREATOR = new Parcelable.Creator<SearchResponse>() {
        @Override
        public SearchResponse createFromParcel(Parcel source) {
            return new SearchResponse(source);
        }

        @Override
        public SearchResponse[] newArray(int size) {
            return new SearchResponse[size];
        }
    };
}
