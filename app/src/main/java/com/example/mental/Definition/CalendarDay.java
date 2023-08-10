package com.example.mental.Definition;

public class CalendarDay {
    private String dayOfWeek;
    private int date;

    public CalendarDay(String dayOfWeek, int date) {
        this.dayOfWeek = dayOfWeek;
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDate() {
        return date;
    }
}

