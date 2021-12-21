package com.crestikinoliki.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import com.crestikinoliki.tictactoe.R;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerSetup extends AppCompatActivity {
    EditText player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        player1 = findViewById(R.id.player1_name);
        player2 = findViewById(R.id.player2_name);
    }

    public void submitButtonClick(View view){
        String player1_name = player1.getText().toString();
        String player2_name = player2.getText().toString();

        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("PLAYER_NAMES", new String[] {player1_name, player2_name});
        startActivity(intent); finish();
    }

    // Системная кнопка назад
    @Override
    public void onBackPressed(){
        Intent backintent = new Intent(this, MainActivity.class);
        startActivity(backintent);finish();
    }
}