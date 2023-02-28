/*Author Name: Isaela Timogene-Julien
 * Date: February 28, 2022
 * Enhancement: none */

package com.example.a15_squarepuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    //declare instance variables for the Game class
    private Square[][] grid;
    public int size;
    private int emptyX;
    private int emptyY;
    private int moves;

    //create a game constructor
    public Game(int size) {
        this.size = size;
        this.grid = new Square[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int value = x * size + y + 1;
                if (value == size * size) {
                    grid[x][y] = new Square("");
                    //update position
                    emptyX = x;
                    emptyY = y;
                } else {
                    grid[x][y] = new Square(String.valueOf(value));
                }
            }
        }
        moves = 0;
    }

//    public void shuffle() {
//        //create an array list
//        ArrayList<Integer> values = new ArrayList<>();
//        for (int i = 1; i < size * size; i++) {
//            values.add(i);
//        }
//        Collections.shuffle(values);
//        int index = 0;
//        for (int x = 0; x < size; x++) {
//            for (int y = 0; y < size; y++) {
//                //find the emptyIndex and update the position
//                if (grid[x][y].getText().equals("")) {
//                    int[] emptyIndex = findEmptyIndex();
//                    emptyX = emptyIndex[0];
//                    emptyY = emptyIndex[1];
//                } else {
//                    grid[x][y].setText(Integer.toString(values.get(index)));
//                    index++;
//                }
//            }
//        }
//        moves = 0;
//    }

    //randomly shuffles the numbers on the squares
    public void shuffle() {
    // create an array list
    ArrayList<Integer> values = new ArrayList<>();
    for (int i = 1; i < size * size; i++) {
        values.add(i);
    }
    Collections.shuffle(values);
    int index = 0;
    int emptyX = -1, emptyY = -1;
    // find the position of the button that currently contains an empty string
    for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
            if (grid[x][y].getText().equals("")) {
                emptyX = x;
                emptyY = y;
                break;
            }
        }
        if (emptyX != -1) {
            break;
        }
    }
    // set the shuffled values to the buttons and set the position of the button with empty string to ""
    for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
            if (x == emptyX && y == emptyY) {
                grid[x][y].setText("");
            } else {
                grid[x][y].setText(Integer.toString(values.get(index)));
                index++;
            }
        }
    }
    moves = 0;
}


    public void swap(int x, int y) {
        int[] emptyIndex = findEmptyIndex();
        if (((x == emptyIndex[0] && Math.abs(y - emptyIndex[1]) == 1) || (y == emptyIndex[1] && Math.abs(x - emptyIndex[0]) == 1))
                && (Math.abs(x - emptyIndex[0]) == 1 || Math.abs(y - emptyIndex[1]) == 1)) {
            // swap the texts of the squares
            String tempText = grid[x][y].getText();
            grid[x][y].setText(grid[emptyIndex[0]][emptyIndex[1]].getText());
            grid[emptyIndex[0]][emptyIndex[1]].setText(tempText);

            // update the empty square coordinates
            emptyX = x;
            emptyY = y;
            //update move
            moves++;
        }
    }

    //create getter for size
    public int getSize() {
        return size;
    }

    //getter for emptyX
    public int getEmptyX() {
        return emptyX;
    }

    //getter for emptyY
    public int getEmptyY() {
        return emptyY;
    }

    //getter for moves
    public int getMoves() {
        return moves;
    }

    //determine whether all the numbers are in order
    public boolean isWon() {
        int value = 1;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (!grid[x][y].getText().equals("")) {
                    if (Integer.parseInt(grid[x][y].getText()) != value) {
                        return false;
                    }
                    value++;
                }
            }
        }
        return true;
    }

    //getter for square
    public Square getSquare(int x, int y) {
        return grid[x][y];
    }

    //find the position of the empty square after swap
    public int[] findEmptyIndex() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid[x][y].getText().equals("")) {
                    return new int[] {x, y};
                }
            }
        }
        return null;
    }
}


