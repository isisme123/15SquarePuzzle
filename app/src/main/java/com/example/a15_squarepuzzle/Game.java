package com.example.a15_squarepuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
        private Square[][] grid;
        public int size;
        public int emptyX = size-1;
        public int emptyY = size -1;
        private int moves;

        public Game(int size) {
            this.size = size;
            this.grid = new Square[size][size];
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    int value = x * size + y + 1;
                    if (value == size * size) {
                        grid[x][y] = new Square("");
                    } else {
                        grid[x][y] = new Square(String.valueOf(value));
                    }
                }
            }
            emptyX = size - 1;
            emptyY = size - 1;
            moves = 0;
        }

public void shuffle() {
    ArrayList<Integer> values = new ArrayList<>();
    for (int i = 1; i < size * size; i++) {
        values.add(i);
    }
    Collections.shuffle(values);
    int index = 0;
    for (int x = 0; x < size; x++) {
        for (int y = 0; y < size; y++) {
            if (x != emptyX || y != emptyY) {
                grid[x][y].setText(Integer.toString(values.get(index)));
                index++;
            }
        }
    }
    moves = 0;
}

//public void swap(int x, int y) {
//            // check if the square to be swapped is adjacent to the empty square
//    if ((x == emptyX && Math.abs(y - emptyY) == 1) || (y == emptyY && Math.abs(x - emptyX) == 1)) {
//                // swap the text of the squares
//                String temp = grid[x][y].getText();
//                grid[x][y].setText(grid[emptyX][emptyY].getText());
//                grid[emptyX][emptyY].setText(temp);
//
//                // update the position of the empty square
//                emptyX = x;
//                emptyY = y;
//
//                // increment the number of moves
//                moves++;
//            }
//        }
public void swap(int x, int y) {
    // check if the selected square is adjacent to the empty square
    if ((x == emptyX && Math.abs(y - emptyY) == 1) || (y == emptyY && Math.abs(x - emptyX) == 1)) {
        // swap the squares in the grid
        Square temp = grid[x][y];
        grid[x][y] = grid[emptyX][emptyY];
        grid[emptyX][emptyY] = temp;

        // swap the texts of the squares
        String tempText = grid[x][y].getText();
        grid[x][y].setText(grid[emptyX][emptyY].getText());
        grid[emptyX][emptyY].setText(tempText);

        // update the empty square coordinates
        emptyX = x;
        emptyY = y;

        moves++;
    }
}

        public int getSize() {
        return size;
        }

        public int getEmptyX() {
            return emptyX;
        }

    public int getEmptyY() {
        return emptyY;
    }

    public int getMoves() {
        return moves;
    }

//    public boolean isWon() {
//        int value = 1;
//        for (int x = 0; x < size; x++) {
//            for (int y = 0; y < size; y++) {
//                if (!grid[x][y].getText().equals(String.valueOf(value))) {
//                    return false;
//                }
//                value++;
//            }
//        }
//        return true;
//    }
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


    public Square getSquare(int x, int y) {
        return grid[x][y];
    }

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


