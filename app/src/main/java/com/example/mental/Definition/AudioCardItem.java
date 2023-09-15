package com.example.mental.Definition;

public class AudioCardItem {

    private String audioName;
    private int playCount;
    private int audioImage; // 新增的图片资源ID变量

    public AudioCardItem(String audioName, int playCount, int audioImage) {
        this.audioName = audioName;
        this.playCount = playCount;
        this.audioImage = audioImage; // 初始化图片资源ID
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
}
