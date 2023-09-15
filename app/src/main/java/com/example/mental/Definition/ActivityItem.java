package com.example.mental.Definition;

public class ActivityItem {
    private int imageResId;
    private String activityName;
    private String activityIntroduce;

    public ActivityItem(int imageResId, String activityName, String activityIntroduce) {
        this.imageResId = imageResId;
        this.activityName = activityName;
        this.activityIntroduce = activityIntroduce;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityIntroduce() {
        return activityIntroduce;
    }
}





