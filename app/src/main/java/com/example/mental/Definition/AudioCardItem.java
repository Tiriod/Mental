package com.example.mental.Definition;

public class AudioCardItem {

    private String audioName;
    private int playCount;

    public AudioCardItem(String audioName, int playCount) {
        this.audioName = audioName;
        this.playCount = playCount;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}

