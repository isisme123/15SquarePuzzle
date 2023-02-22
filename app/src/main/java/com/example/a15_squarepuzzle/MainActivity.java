package com.example.a15_squarepuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public LinearLayout buttonLayout;
    //private LinearLayout verticalLayout;
    private Button newGameButton;
    private int emptyButton;
    private Game game;
    private int gridSize = 4;
    private int numSquares = 15;
    //random object to shuffle squares
    private static final Random RANDOM = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);



    buttonLayout = findViewById(R.id.buttonLayout);
        //verticalLayout = findViewById(R.id.verticalLayout);

        //adding a vertical linear layout everytime
        //for(int i =0; i < 5; i++){
            //buttonLayout.add(new LinearLayout(VERTICAL));
            //buttonLayout.setOrientation(LinearLayout.VERTICAL);
            //verticalLayout.setOrientation(LinearLayout.HORIZONTAL);
        //    buttonLayout.setOrientation(i == 0 ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);

        //}
        newGameButton = findViewById(R.id.newGameButton);

        //generating random numbers
        //final TextView textNumber = (TextView)findViewById(R.id.textNumber);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                //generates new random number after game is reset
                //textNumber.setText(String.valueOf(RANDOM.nextInt(15)));
            }
        });

        game = new Game();
        createGrid();
    }
    //creating initial grid
    private void createGrid() {
        //buttonLayout.removeAllViews();
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
                //buttonLayout.addView(button);
            }
        }
    }
    //loops through rows and columns and update the value of each square
    private void updateGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Button button = (Button) buttonLayout.getChildAt(i * gridSize + j);
                button.setText(String.valueOf(game.getSquare(i,j).getValue()));
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



    // Declare an array of button ids
    public int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button10, R.id.button11, R.id.button12,
            R.id.button13, R.id.button14, R.id.button15, R.id.button16};


    // Combine the four LinearLayouts into an array for easier iteration
    // Get the LinearLayouts by their IDs
    LinearLayout linearLayout1 = findViewById(R.id.linear1);
    LinearLayout linearLayout2 = findViewById(R.id.linear2);
    LinearLayout linearLayout3 = findViewById(R.id.linear3);
    LinearLayout linearLayout4 = findViewById(R.id.linear4);

    // Create a 2D array of the LinearLayouts
    LinearLayout[] linearLayouts = {linearLayout1, linearLayout2 ,linearLayout3, linearLayout4 };

    public void setButtonId() {
        // Iterate through each linear layout
        for (LinearLayout linearLayout : linearLayouts) {
            // Iterate through each child view (button) in the linear layout
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                // Get the child view (button) at the current index
                View childView = linearLayout.getChildAt(i);
                // Set its id from the buttonIds array
                childView.setId(buttonIds[i]);
            }
        }
    }





    //calls the createGrid method to create a new grid.
//    public void newGame() {
//        game = new Game();
//        game.shuffle();
//        updateGrid();
//
//    }

    public void newGame() {
        // Generate a random order for the tile numbers
        int[] tileOrder = game.shuffle();
        setButtonId();
        // Iterate through each button in the grid and set its text to the corresponding tile number
        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            int tileNumber = tileOrder[i];
            button.setText(String.valueOf(tileNumber));

            // If the tile number is 16, set the button's visibility to invisible to represent the empty square
            if (tileNumber == 16) {
                button.setVisibility(View.INVISIBLE);
                emptyButton = buttonIds[i]; // Keep track of the empty button's id
            } else {
                button.setVisibility(View.VISIBLE);
            }
        }

        // Set the empty square position to the bottom-right corner using the emptyButtonId
        Button emptyButton = findViewById(R.id.button16);
        emptyButton.setVisibility(View.VISIBLE);

        updateGrid();
    }



}