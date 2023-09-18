package com.example.mental.Definition;

import java.io.Serializable;

public class MessageItem implements Serializable {
    private int imageResource; // 资讯图片的资源ID
    private String title; // 资讯标题
    private String description; // 资讯文案
    private String time; // 资讯时间
    private String source; // 文本来源

    // 构造函数
    public MessageItem(int imageResource, String title, String description, String time, String source) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
        this.time = time;
        this.source = source;
    }

    // 获取资讯图片资源ID
    public int getImageResource() {
        return imageResource;
    }

    // 获取资讯标题
    public String getTitle() {
        return title;
    }

    // 获取资讯文案
    public String getDescription() {
        return description;
    }

    // 获取资讯时间
    public String getTime() {
        return time;
    }

    // 获取文本来源
    public String getSource() {
        return source;
    }
}

