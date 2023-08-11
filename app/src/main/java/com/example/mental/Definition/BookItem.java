package com.example.mental.Definition;

public class BookItem {
    private int bookBackgroundResId;
    private String bookTitle;

    public BookItem(int bookBackgroundResId, String bookTitle) {
        this.bookBackgroundResId = bookBackgroundResId;
        this.bookTitle = bookTitle;
    }

    public int getBookBackgroundResId() {
        return bookBackgroundResId;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}



