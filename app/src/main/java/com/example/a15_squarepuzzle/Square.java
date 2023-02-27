package com.example.a15_squarepuzzle;

import android.util.Log;
import android.view.View;

public class Square {
    private String text;

    public Square(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
