package com.example.mental.Definition;

public class ActionCardItem {
    private int actionImageResId;
    private String actionText;

    public ActionCardItem(int actionImageResId, String actionText) {
        this.actionImageResId = actionImageResId;
        this.actionText = actionText;
    }

    public int getActionImageResId() {
        return actionImageResId;
    }

    public String getActionText() {
        return actionText;
    }
}

