package com.crestikinoliki.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class GameDisplay extends AppCompatActivity {

    TicTacToeBoard ticTacToeBoard;
    Button playAgainBTN, homeBTN;
    TextView playerdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playAgainBTN = findViewById(R.id.playAgainBTN);
        homeBTN = findViewById(R.id.homeBTN);
        playerdisplay = findViewById(R.id.player_display);

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        String[] playersNames = getIntent().getStringArrayExtra("PLAYER_NAMES");

        if (playersNames[0].equals("")){
            playersNames[0] = "Игрок 1";
        }
        if (playersNames[1].equals("")){
            playersNames[1] = "Игрок 2";
        }

            playerdisplay.setText(("Ходит " + playersNames[0]));
        playerdisplay.setTextColor(Color.parseColor("#FF0099CC"));


        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        ticTacToeBoard.setUpGame(playAgainBTN, homeBTN, playerdisplay,playersNames);
    }

    public void playAgainButtonClick(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); finish();
    }

    // Системная кнопка назад
    @Override
    public void onBackPressed(){
        Intent backintent = new Intent(this, MainActivity.class);
        startActivity(backintent);finish();
    }
}