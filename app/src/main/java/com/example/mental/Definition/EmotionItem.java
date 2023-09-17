package com.example.mental.Definition;

public class EmotionItem {
    private int emotionImageResId;
    private String emotionDescription;
    private String emotionColor;
    private boolean isSelected;

    public EmotionItem(int emotionImageResId, String emotionDescription, String emotionColor, boolean isSelected) {
        this.emotionImageResId = emotionImageResId;
        this.emotionDescription = emotionDescription;
        this.emotionColor = emotionColor;
        this.isSelected = isSelected;
    }

    public int getEmotionImageResId() {
        return emotionImageResId;
    }

    public String getEmotionDescription() {
        return emotionDescription;
    }

    public String getEmotionColor() {
        return emotionColor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

