package com.example.mental.Definition;

import java.io.Serializable;

public class BookItem implements Serializable {
    private int bookBackgroundResId;
    private String bookTitle;
    private String bookContent; // 新增书本内容字段

    public BookItem(int bookBackgroundResId, String bookTitle, String bookContent) {
        this.bookBackgroundResId = bookBackgroundResId;
        this.bookTitle = bookTitle;
        this.bookContent = bookContent;
    }

    public int getBookBackgroundResId() {
        return bookBackgroundResId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookContent() {
        return bookContent;
    }
}
