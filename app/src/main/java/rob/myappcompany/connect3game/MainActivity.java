
package rob.myappcompany.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        // 0: yellow, 1: red, 2: empty
        int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

        int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        int count =0;

        int activePlayer = 0;
        boolean gameActive = true;

        public void dropIn(View view) {

                ImageView counter = (ImageView) view;

                int tappedCounter = Integer.parseInt(counter.getTag().toString());

                if (gameState[tappedCounter] == 2 && gameActive) {
                        count++;

                        gameState[tappedCounter] = activePlayer;

                        counter.setTranslationY(-1500);

                        if (activePlayer == 0) {

                                counter.setImageResource(R.drawable.yellow2);
                                activePlayer = 1;
                        } else {
                                counter.setImageResource(R.drawable.red1);
                                activePlayer = 0;

                        }
                        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                        for (int[] winningPosition : winningPositions) {

                                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                                        //someone has won!

                                        gameActive = false;
                                        String winner;
                                        if (activePlayer == 1) {
                                                winner = "Yellow";

                                        } else {
                                                winner = "Red";

                                        }


                                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                                         TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                                        winnerTextView.setText(winner + "has won!");
                                        winnerTextView.setVisibility(View.VISIBLE);
                                        playAgainButton.setVisibility(View.VISIBLE);
                                }
                        }
                                if (gameActive && count == 9) {
                                        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                                        winnerTextView.setText("DRAW!");
                                        winnerTextView.setVisibility(View.VISIBLE);
                                        playAgainButton.setVisibility(View.VISIBLE);
                                        gameActive = false;


                                }

                }
        }

        public void playAgain(View view) {
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                playAgainButton.setVisibility(View.INVISIBLE);

                winnerTextView.setVisibility(View.INVISIBLE);
                GridLayout GridLayout = (GridLayout) findViewById(R.id.GridLayout);
                int i;
                for (i = 0; i < GridLayout.getChildCount(); i++) {
                        ImageView counter = (ImageView) GridLayout.getChildAt(i);
                        counter.setImageDrawable(null);

                }
                for (i = 0; i < gameState.length; i++) {
                        gameState[i] = 2;
                }


                activePlayer = 1;
                gameActive = true;
                count= 0;


        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        }
}

