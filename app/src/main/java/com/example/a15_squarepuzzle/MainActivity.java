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
    private Game game;
    private TextView movesText;
    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(4);
        movesText = findViewById(R.id.movesText);
        buttons = new Button[16];

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
                    if ((x == emptyX && Math.abs(y - emptyY) == 1) || (y == emptyY && Math.abs(x - emptyX) == 1)) {
                        game.swap(x, y); //emptyX, emptyY);
                        updateLayout();
                        if (game.isWon()) {
                            Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            });
        }


        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.shuffle();
                updateLayout();
                //createGrid();
            }
        });
    }

//    void createGrid() {
//        for (int i = 0; i < 15; i++) {
//            buttons[i].setText(String.valueOf(i + 1));
//        }
//        //buttons[15].setText(""); //set the text of the empty square button to empty sting
//        //buttons[15].setVisibility(View.GONE); // Hide the empty square
//        buttons[game.size * game.size - 1] = findViewById(R.id.button16);
//        buttons[game.size * game.size - 1].setText("");
//
//        //game.shuffle(); // Shuffle the game
//
//        updateLayout();
//    }

    void createGrid() {
        for (int i = 0; i < 15; i++) {
            buttons[i].setText(String.valueOf(i + 1));
        }
        // set the text of button16 to empty string and show it
        buttons[15].setText("");
        buttons[15].setVisibility(View.GONE);

        // shuffle the game
        game.shuffle();
        updateLayout();
    }


    void updateLayout() {
        movesText.setText("Moves: " + game.getMoves());

        for (int i = 0; i < 15; i++) {
            Button button = buttons[i];
            int x = i / game.getSize();
            int y = i % game.getSize();
            Square square = game.getSquare(x, y);
            button.setText(square.getText());
        }
    }


}




