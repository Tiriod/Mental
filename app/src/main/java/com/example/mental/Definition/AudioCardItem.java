package com.example.mental.Definition;

public class AudioCardItem {

    private String audioName;
    private int playCount;
    private int audioImage;


    private int audioUrl; // 新增的音频地址变量

    public AudioCardItem(String audioName, int playCount, int audioImage, int audioUrl) {
        this.audioName = audioName;
        this.playCount = playCount;
        this.audioImage = audioImage;
        this.audioUrl = audioUrl; // 初始化音频地址
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

    public int getAudioImage() {
        return audioImage;
    }

    public void setAudioImage(int audioImage) {
        this.audioImage = audioImage;
    }

    public int getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(int audioUrl) {
        this.audioUrl = audioUrl;
    }
}
