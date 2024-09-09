package com.example.pizzeria;

public class Pizza {
    private String name;
    private int imageResId;

    public Pizza(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}

