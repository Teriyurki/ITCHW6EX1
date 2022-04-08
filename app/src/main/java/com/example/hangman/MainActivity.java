package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Hangman game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (game == null)
            game = new Hangman(Hangman.DEFAULT_GUESSES);
        setContentView(R.layout.activity_main);
        TextView status = findViewById(R.id.status);
        TextView activity = findViewById(R.id.activitytv);
        status.setText("" + game.getGuessesLeft());


    }

    public void play(View view){

    }
}