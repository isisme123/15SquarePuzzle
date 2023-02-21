package com.example.a15_squarepuzzle;

public class Square {
    private int value;

    public Square(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == 0;
    }
}
