/*Author Name: Isaela Timogene-Julien
* Date: February 28, 2022
* Enhancement: none */

package com.example.a15_squarepuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //creating the instance variables for this class
    private Game game;
    private TextView movesText;
    private Button[] buttons;

    @Override
    //OnCreate method to create instance of game class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance of game class
        game = new Game(4);
        movesText = findViewById(R.id.movesText);
        buttons = new Button[16]; //creating 16 buttons
        //setting button ids to variables
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);
        buttons[11] = findViewById(R.id.button12);
        buttons[12] = findViewById(R.id.button13);
        buttons[13] = findViewById(R.id.button14);
        buttons[14] = findViewById(R.id.button15);
        buttons[15] = findViewById(R.id.button16);

        createGrid();
        //onclick listener to swap buttons on click
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    int position = Integer.parseInt(getResources().getResourceEntryName(id).substring(6));
                    int x = position / game.getSize();
                    int y = position % game.getSize();
                    int emptyX = game.getEmptyX();
                    int emptyY = game.getEmptyY();
                    if ((Math.abs(x - emptyX) == 1 && y == emptyY) || (Math.abs(y - emptyY) == 1 && x == emptyX)) {
                        // swap the button text with the empty button text
                        Button emptyButton = buttons[emptyX * game.getSize() + emptyY];
                        String buttonText = button.getText().toString();
                        button.setText(emptyButton.getText().toString());
                        emptyButton.setText(buttonText);
                        // update the game state and layout
                        game.swap(x, y);
                        //update the position of the empty button
                        emptyX = x;
                        emptyY = y;
                        updateLayout();
                        //calls the isWon method
                        if (game.isWon()) {
                            Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        }

        //onClick listener to create reset button that calls shuffle and update the layout.
        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.shuffle(); //calls shuffle from game class
                updateLayout(); //calls updateLayout method
            }
        });
    }

//    void createGrid() {
//        for (int i = 0; i < 16; i++) {
//            buttons[i].setText(String.valueOf(game.getSquare(i / game.getSize(), i % game.getSize()).getText()));
//        }
//        // set the text of button16 to empty string and show it
//        buttons[15].setText("");
//        buttons[15].setVisibility(View.VISIBLE);
//        updateLayout();
//    }
void createGrid() {
    //initialize 15 buttons for the game
    for (int i = 0; i < 15; i++) {
        buttons[i].setText(String.valueOf(game.getSquare(i / game.getSize(), i % game.getSize()).getText()));
    }
    // set the text of the last button to empty string and show it
    int lastIndex = buttons.length - 1;
    buttons[lastIndex].setText("");
    buttons[lastIndex].setVisibility(View.VISIBLE);
    updateLayout(); //update the text of each button
}


//updates the grid
void updateLayout() {
        movesText.setText("Moves: " + game.getMoves());

        //iterates over the 15 buttons and set texts to the corresponding square
        for (int i = 0; i < 15; i++) {
            Button button = buttons[i];
            int x = i / game.getSize();
            int y = i % game.getSize();
            Square square = game.getSquare(x, y);
            button.setText(square.getText());
        }
    }

}




