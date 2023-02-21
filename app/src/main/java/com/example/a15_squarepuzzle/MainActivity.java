package com.example.a15_squarepuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private Button newGameButton;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        game = new Game();
        createGrid();
    }

    private void createGrid() {
        gridLayout.removeAllViews();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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
                                Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                gridLayout.addView(button);
            }
        }
    }

    private void updateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = (Button) gridLayout.getChildAt(i * 4 + j);
                button.setText(String.valueOf(game.getSquare(i, j).getValue()));
            }
        }
    }

    public void newGame() {
        game = new Game();
        createGrid();
    }
}
