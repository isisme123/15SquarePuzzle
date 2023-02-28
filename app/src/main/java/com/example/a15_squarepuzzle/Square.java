/*Author Name: Isaela Timogene-Julien
 * Date: February 28, 2022
 * Enhancement: none */

package com.example.a15_squarepuzzle;

import android.util.Log;
import android.view.View;

public class Square {
    //declare instance variable
    private String text;

    //create square constructor
    public Square(String text) {
        this.text = text;
    }

    //create getter method
    public String getText() {
        return text;
    }

    //create setter method
    public void setText(String text) {
        this.text = text;
    }
}
