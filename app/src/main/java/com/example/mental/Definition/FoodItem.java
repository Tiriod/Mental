package com.example.mental.Definition;

public class FoodItem {
    private int imageResId;
    private String name;
    private String weight;
    private String calories;

    public FoodItem(int imageResId, String name, String weight, String calories) {
        this.imageResId = imageResId;
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getCalories() {
        return calories;
    }
}

