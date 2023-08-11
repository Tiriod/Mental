package com.example.mental.Definition;

public class UserCardItem {
    private int avatarResId;
    private String userName;
    private String userId;

    public UserCardItem(int avatarResId, String userName, String userId) {
        this.avatarResId = avatarResId;
        this.userName = userName;
        this.userId = userId;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }
}
