package com.example.a15_squarepuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private Square[][] squares;
    private int emptyX, emptyY;
    private int size;
    private int moves;

    public Game(int size) {
        this.size = size;
        this.squares = new Square[size][size];
        this.moves = 0;

        // Initialize the board with squares in order
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squares[i][j] = new Square(i, j, i * size + j + 1);
            }
        }

        // Set the bottom-right square as the empty square
        emptyX = size - 1;
        emptyY = size - 1;
        squares[emptyX][emptyY] = new Square(emptyX, emptyY, 0);
    }

    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    public int getSize() {
        return size;
    }

    public int getMoves() {
        return moves;
    }

    public int getEmptyX() {
        return emptyX;
    }

    public int getEmptyY() {
        return emptyY;
    }


    public boolean isSolved() {
        // Check if all squares are in order except for the empty square
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (squares[i][j].getValue() != count) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public boolean move(int x, int y) {
        // Check if the clicked square is adjacent to the empty square
        if ((x == emptyX && Math.abs(y - emptyY) == 1) ||
                (y == emptyY && Math.abs(x - emptyX) == 1)) {
            // Swap the clicked square with the empty square
            Square temp = squares[x][y];
            squares[x][y] = squares[emptyX][emptyY];
            squares[emptyX][emptyY] = temp;

            // Update the empty square's position
            emptyX = x;
            emptyY = y;

            moves++;
            return true;
        }
        return false;
    }

    public void shuffle() {
        // Perform a random shuffle of the board
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randX = random.nextInt(size);
            int randY = random.nextInt(size);
            move(randX, randY);
        }
    }
    public boolean isWon() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (getSquare(i, j).getValue() != count++) {
                    return false;
                }
            }
        }
        return true;
    }

    public void swap(int x1, int y1, int x2, int y2) {
        Square temp = squares[x1][y1];
        squares[x1][y1] = squares[x2][y2];
        squares[x2][y2] = temp;
        if (squares[x1][y1].getValue() == 0) {
            emptyX = x1;
            emptyY = y1;
        } else if (squares[x2][y2].getValue() == 0) {
            emptyX = x2;
            emptyY = y2;
        }
        moves++;
    }

}


