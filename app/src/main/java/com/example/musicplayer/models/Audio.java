package com.example.musicplayer.models;

public class Audio {
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
}
