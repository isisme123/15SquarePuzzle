package com.example.a15_squarepuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    //2-D array of square objects.
    private Square[][] squares;
    //private Numbers[] numbers;

    public Game() {
        squares = new Square[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    squares[i][j] = new Square(i * 4 + j + 1);
                }
            }
            squares[3][3] = new Square(0);
        }
        //create a getter method for square
        public Square getSquare(int i, int j){
        return squares[i][j];
        }

        //moves the square at the chosen position if possible.
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

        //Swap the square chosen with another square at that position.
        private void swap(int row1, int col1, int row2, int col2) {
            Square temp = squares[row1][col1];
            squares[row1][col1] = squares[row2][col2];
            squares[row2][col2] = temp;
        }

        // check if the game is won
   // public boolean isWon(){
     //   if (squares[squares.length -1] != 0){
       //     return false;
         //   for (int i = numSquares - 1; i >= 0; i--){
           //     if (squares[i] != i + 1){
             //       return false;
               // }
           //}
        //}
        //return false;
    //}

    //check if square values are in ascending order.
    public boolean isWon(){
        int count = 1;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(getSquare(i,j).getValue() != count++){
                    return false;
                }
            }
        }
        return true;
    }




    public int[] shuffle() {
        int[] numbers = new int[16];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 16; i++) {
            list.add(i);
        }

        // Shuffle the ArrayList using Collections.shuffle
        Collections.shuffle(list);

        for (int i = 0; i < 16; i++) {
            numbers[i] = list.get(i);
        }

        // Check if the resulting puzzle is solvable
        if (!isSolvable(numbers)) {
            // If not, swap the first two numbers
            int temp = numbers[0];
            numbers[0] = numbers[1];
            numbers[1] = temp;
        }

        return numbers;
    }

    private boolean isSolvable(int[] numbers) {
        int inversions = 0;

        // Count the number of inversions in the array
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j] && numbers[i] != 16 && numbers[j] != 16) {
                    inversions++;
                }
            }
        }

        // Check if the puzzle is solvable
        return (inversions % 2 == 0);
    }





}


