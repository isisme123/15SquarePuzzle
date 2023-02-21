package com.example.a15_squarepuzzle;

public class Square {
    //value field that represent the number on the square.
    private int value;

    //constructor, sets value of the square to value.
    public Square(int value) {
        this.value = value;
    }

    //gets the number on the square.
    public int getValue() {
        return value;
    }

    //returns true if the square is empty.
    public boolean isEmpty() {
        return value == 0;
    }
}
