package com.example.a15_squarepuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout buttonLayout;
    private Button newGameButton;
    private Game game;
    private int gridSize = 4;
    private int numSquares = 16;
    //random object to shuffle squares
    private static final Random RANDOM = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLayout = findViewById(R.id.buttonLayout);

        //adding a vertical linear layout everytime
        for(int i =0; i < 5; i++){
            //buttonLayout.add(new LinearLayout(VERTICAL));
            buttonLayout.setOrientation(LinearLayout.VERTICAL);

        }
        newGameButton = findViewById(R.id.newGameButton);

        //generating random numbers
        final TextView textNumber = (TextView)findViewById(R.id.textNumber);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                //generates new random number after game is reset
                textNumber.setText(String.valueOf(RANDOM.nextInt(15)));
            }
        });

        game = new Game();
        createGrid();
    }
    //creating initial grid
    private void createGrid() {
        buttonLayout.removeAllViews();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Button button = new Button(this);
                button.setTag(new int[] {i, j});
                button.setText(String.valueOf(game.getSquare(i,j).getValue()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int[] tag = (int[]) v.getTag();
                        if (game.move(tag[0], tag[1])) {
                            updateGrid();
                            if (game.isWon()) {
                                Toast.makeText(MainActivity.this, "You won!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                buttonLayout.addView(button);
            }
        }
    }
    //loops through rows and columns and update the value of each square
    private void updateGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Button button = (Button) buttonLayout.getChildAt(i * gridSize + j);
                button.setText(String.valueOf(game.getSquare(i, j).getValue()));
            }
        }
    }

    //public void shuffle(squares){
        //for (int i = 0; i < gridSize; i++) {
            //for (int j = 0; j < gridSize; j++) {
                //int index = i * gridSize + j;
                //if (value < squares.size()) {
                    //Rectangle square = squares.get(index);
                    //squares[i][j] = 0;
                   // if (square == Missing) {
                 //       continue;
               //     }
             //       int number = index + 1;
           //         grid[i][j] = number;
         //       }
       //     }
     //   }
   // }

//    public void shuffle(){
  //      int n = numSquares;
    //    while(n > 1){
      //      int num = RANDOM.nextInt(n--){
        //        int temp = game.getSquare(i,j).getValue(num);
          //      game.getSquare(i,j).getValue(num) = game.getSquare(i,j).getValue(n);
            //    game.getSquare(i,j).getValue(n) = temp;

            //}
        //}
    //}

    //calls the createGrid method to create a new grid.
    public void newGame() {
        game = new Game();
        createGrid();
    }
}
