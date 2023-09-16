package com.example.mental.Definition;

import java.util.List;

public class ShareLoopItem {
    private int userAvatarResId;
    private String username;
    private int userEmotionResId;
    private String text;
    private List<Integer> imageResIds; // 图片资源ID列表
    private String releaseTime;

    public ShareLoopItem(int userAvatarResId, String username, int userEmotionResId, String text, List<Integer> imageResIds, String releaseTime) {
        this.userAvatarResId = userAvatarResId;
        this.username = username;
        this.userEmotionResId = userEmotionResId;
        this.text = text;
        this.imageResIds = imageResIds;
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

    public List<Integer> getImageResIds() {
        return imageResIds;
    }

    public String getReleaseTime() {
        return releaseTime;
    }
}
