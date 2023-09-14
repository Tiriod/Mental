package com.example.mental.Definition;

public class ShareLoopItem {
    private int userAvatarResId;
    private String username;
    private int userEmotionResId;
    private String text;
    private int imageResId;
    private String releaseTime;

    public ShareLoopItem(int userAvatarResId, String username, int userEmotionResId, String text, int imageResId, String releaseTime) {
        this.userAvatarResId = userAvatarResId;
        this.username = username;
        this.userEmotionResId = userEmotionResId;
        this.text = text;
        this.imageResId = imageResId;
        this.releaseTime = releaseTime;
    }

    // Getter methods for all the fields
    public int getUserAvatarResId() {
        return userAvatarResId;
    }

    public String getUsername() {
        return username;
    }

    public int getUserEmotionResId() {
        return userEmotionResId;
    }

    public String getText() {
        return text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getReleaseTime() {
        return releaseTime;
    }
}

