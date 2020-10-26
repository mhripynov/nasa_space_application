package com.example.spaceapplication.ui;

import android.graphics.Bitmap;

import com.davemorrissey.labs.subscaleview.ImageSource;

public class Item {

    private String title;
    private String message;
    private ImageSource image;
    private String date;

    public Item(String title, String message, ImageSource image, String date) {
        this.title = title;
        this.message = message;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public ImageSource getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }
}
