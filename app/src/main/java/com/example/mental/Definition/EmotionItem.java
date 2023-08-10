package com.example.mental.Definition;

public class EmotionItem {
    private int emotionImageResId;
    private String emotionDescription;
    private String emotionColor;

    public EmotionItem(int emotionImageResId, String emotionDescription, String emotionColor) {
        this.emotionImageResId = emotionImageResId;
        this.emotionDescription = emotionDescription;
        this.emotionColor = emotionColor;
    }

    public int getEmotionImageResId() {
        return emotionImageResId;
    }

    public String getEmotionDescription() {
        return emotionDescription;
    }

    public String getEmotionColor(){
        return emotionColor;
    }
}

