package com.example.mental.Definition;

import java.util.List;

public class EmotionCardItem {
    private String emotionDate;
    private int emotionImageResId;
    private String emotionText;
    private List<ActionCardItem> actionList;

    public EmotionCardItem(String emotionDate, int emotionImageResId, String emotionText, List<ActionCardItem> actionList) {
        this.emotionDate = emotionDate;
        this.emotionImageResId = emotionImageResId;
        this.emotionText = emotionText;
        this.actionList = actionList;
    }

    public String getEmotionDate() {
        return emotionDate;
    }

    public int getEmotionImageResId() {
        return emotionImageResId;
    }

    public String getEmotionText() {
        return emotionText;
    }

    public List<ActionCardItem> getActionList() {
        return actionList;
    }
}

