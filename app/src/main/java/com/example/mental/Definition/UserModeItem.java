package com.example.mental.Definition;

public class UserModeItem {
    private int iconResId;
    private String modeName;

    public UserModeItem(int iconResId, String modeName) {
        this.iconResId = iconResId;
        this.modeName = modeName;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getModeName() {
        return modeName;
    }
}

