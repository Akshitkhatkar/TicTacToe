package com.tictactoe777.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString())-1;

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.circle);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            int turn=0;
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // Somone has won!

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Player 1";

                    } else {

                        winner = "Player 2";

                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);

                }
                else {
                    int count = 0;
                    for (int j = 0; j < 9; j++) {
                        if (gameState[j] != 2) {
                            count++;
                        }
                    }
                    if (count == 9 && turn==0) {
                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                        winnerTextView.setText("Draw!");

                        playAgainButton.setVisibility(View.VISIBLE);

                        winnerTextView.setVisibility(View.VISIBLE);
                        turn=1;
                    }
                }

            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);


        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }



        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
