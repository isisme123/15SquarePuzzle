package com.example.a15_squarepuzzle;

import android.util.Log;
import android.view.View;

public class Square {

    private int value;
    private int row;
    private int column;

    public Square(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isEmpty() {
        return value == 0;
    }
}
