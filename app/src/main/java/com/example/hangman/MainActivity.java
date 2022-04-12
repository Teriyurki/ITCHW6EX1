package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
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
        status.setText("" + game.getGuessesLeft());

        FragmentManager fm = getSupportFragmentManager();

        if (fm.findFragmentById(R.id.game_state)== null){
            FragmentTransaction ft = fm.beginTransaction();
            GameStateFragment fragment = new GameStateFragment();
            ft.add(R.id.game_state, fragment);
            ft.commit();
        }

        if( fm.findFragmentById(R.id.game_result) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            GameResultFragment fragment = new GameResultFragment();
            ft.add( R.id.game_result, fragment);
            ft.commit();
        }

       if (fm.findFragmentByTag("background") == null) {
            FragmentTransaction ft = fm.beginTransaction();
            GameResultFragment fragment = new GameResultFragment();
            ft.add(fragment, "background");
            ft.commit();
        }
    }

    public Hangman getGame() {
        return game;
    }


    public void play(View view){

        EditText input = findViewById(R.id.letter);
        Editable userText  = input.getText();

        if (userText != null && userText.length() > 0) {
            //update the number of guesses left
            char letter = userText.charAt(0);
            game.guess(letter);

            TextView status = findViewById(R.id.status);
            status.setText("" + game.getGuessesLeft());

            //update incomplete word (fragment)
            FragmentManager fm = getSupportFragmentManager();
            GameStateFragment gameStateFragment = (GameStateFragment) fm.findFragmentById(R.id.game_state);
            View gsFragmentView = gameStateFragment.getView();

            TextView gameStateTV = gsFragmentView.findViewById(R.id.state_of_game);
            gameStateTV.setText(game.currentIncompleteWord());

            //clear EditTExt
            input.setText("");

            //check if there is only one guess left

            if (game.getGuessesLeft()==1){
                BackgroundFragment background = (BackgroundFragment)
                        getSupportFragmentManager().findFragmentByTag("background");
                GameResultFragment grFragment = (GameResultFragment)
                        getSupportFragmentManager().findFragmentById(R.id.game_result);


                //receive warning and display it
                grFragment.setResult(background.warning());
            }

            int result = game.gameOver();
            if (result != 0) {

                GameResultFragment gameResultFragment = (GameResultFragment)
                        fm.findFragmentById(R.id.game_result);

                //Update TextView in result fragment

                if (result ==1)
                    gameResultFragment.setResult(" You Won");
                else if (result == -1)
                    gameResultFragment.setResult("You Lost");

                //delete hint
                input.setHint("");


            }
        }
    }
}

