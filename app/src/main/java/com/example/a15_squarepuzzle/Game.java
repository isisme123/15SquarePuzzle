package com.example.a15_squarepuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    public Square[][] squares;
    private int emptyX, emptyY;
    private int size;
    public int moves;

    public Game(int size) {
        this.size = size;
        this.squares = new Square[size][size];
        this.moves = 0;

        // Initialize the board with squares in order
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squares[i][j] = new Square(i * size + j + 1, i, j);
            }
        }

        // Set the bottom-right square as the empty square
        emptyX = size - 1;
        emptyY = size - 1;
        squares[emptyX][emptyY] = new Square(0, emptyX, emptyY);
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


//    public boolean isSolved() {
//        // Check if all squares are in order except for the empty square
//        int count = 1;
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (squares[i][j].getValue() != count) {
//                    return false;
//                }
//                count++;
//            }
//        }
//        return true;
//    }

    public boolean isSolved() {
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getSquare(i, j).getValue() != count++ && !(i == size-1 && j == size-1)) {
                    return false;
                }
            }
        }
        return true;
    }


//    public boolean move(int x, int y) {
//        // Check if the clicked square is adjacent to the empty square
//        if ((x == emptyX && Math.abs(y - emptyY) == 1) ||
//                (y == emptyY && Math.abs(x - emptyX) == 1)) {
//            // Swap the clicked square with the empty square
//            Square temp = squares[x][y];
//            squares[x][y] = squares[emptyX][emptyY];
//            squares[emptyX][emptyY] = temp;
//
//            // Update the empty square's position
//            emptyX = x;
//            emptyY = y;
//
//            moves++;
//            return true;
//        }
//        return false;
//    }

    public void shuffle() {
        // Perform a random shuffle of the board
        Random random = new Random();
        for (int i = 1; i < 16; i++) {
            int randX = random.nextInt(size);
            int randY = random.nextInt(size);
            move(randX, randY);
        }
    }


//    public int[] shuffle() {
//        int[] numbers = new int[16];
//        ArrayList<Integer> list = new ArrayList<>();
//
//        for (int i = 1; i <= 16; i++) {
//            list.add(i);
//        }
//
//        // Shuffle the ArrayList using Collections.shuffle
//        Collections.shuffle(list);
//
//        for (int i = 0; i < 16; i++) {
//            numbers[i] = list.get(i);
//        }
//
//         //Check if the resulting puzzle is solvable
//        if (!isSolved()) {
//            // If not, swap the first two numbers
//            int temp = numbers[0];
//            numbers[0] = numbers[1];
//            numbers[1] = temp;
//        }
////
//        return numbers;
//    }
//        private boolean isSolved(int[] numbers) {
//            int inversions = 0;
//
//            // Count the number of inversions in the array
//            for (int i = 0; i < numbers.length; i++) {
//                for (int j = i + 1; j < numbers.length; j++) {
//                    if (numbers[i] > numbers[j] && numbers[i] != 16 && numbers[j] != 16) {
//                        inversions++;
//                    }
//                }
//            }
//
//            // Check if the puzzle is solvable
//            return (inversions % 2 == 0);
//        }



// Method that randomizes the numbers in an array
//public int[] shuffle() {
//    Random rand = new Random();
//    int[] randomInts = new int[16];
//
//    // initializes array of ints 1-15
//    for (int i = 0; i < randomInts.length - 1; i++) {
//        randomInts[i] = i + 1;
//    }
//
//    // randomly shuffles array of ints, leaves last element always 0
//    for (int i = 0; i < randomInts.length - 1; i++) {
//        int randomIndexToSwap = rand.nextInt(randomInts.length - 1);
//        int temp = randomInts[randomIndexToSwap];
//        randomInts[randomIndexToSwap] = randomInts[i];
//        randomInts[i] = temp;
//    }
//    return randomInts;
//}

    public boolean isWon() {
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getSquare(i, j).getValue() != count++) {
                    return false;
                }
            }
        }
        return true;
    };
//public boolean isWon() {
//    return isSolved() && getSquare(size-1, size-1).getValue() == 0;
//}


//    public void swap(int x1, int y1, int x2, int y2) {
//        Square temp = squares[x1][y1];
//        squares[x1][y1] = squares[x2][y2];
//        squares[x2][y2] = temp;
//        if (squares[x1][y1].getValue() == 0) {
//            emptyX = x1;
//            emptyY = y1;
//        } else if (squares[x2][y2].getValue() == 0) {
//            emptyX = x2;
//            emptyY = y2;
//        }
//        moves++;
//    }

    public boolean move(int row, int col) {
        if (row > 0 && squares[row - 1][col].isEmpty()) {
            swap(row, col, row - 1, col);
            return true;
        } else if (row < 3 && squares[row + 1][col].isEmpty()) {
            swap(row, col, row + 1, col);
            return true;
        } else if (col > 0 && squares[row][col - 1].isEmpty()) {
            swap(row, col, row, col - 1);
            return true;
        } else if (col < 3 && squares[row][col + 1].isEmpty()) {
            swap(row, col, row, col + 1);
            return true;
        }
        return false;
    }

    private void swap(int row1, int col1, int row2, int col2) {
        Square temp = squares[row1][col1];
        squares[row1][col1] = squares[row2][col2];
        squares[row2][col2] = temp;
    }
}


