package com.example.a15_squarepuzzle;

import android.util.Log;
import android.view.View;

public class Square implements View.OnClickListener{
    public boolean isButtonClicked = false;
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

    public int setValue(int value){
        return value = value;
    };

    //returns true if the square is empty.
    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public void onClick(View view) {
        Log.d("button", "button");
        isButtonClicked = true;
    }
}
