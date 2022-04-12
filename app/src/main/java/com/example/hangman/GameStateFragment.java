package com.example.hangman;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameStateFragment extends Fragment {


    public GameStateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_state, container, false);
    }


    @Override
    public void onStart(){
        super.onStart();

        View fragmentView  = getView();
        TextView gameStateTV = fragmentView.findViewById(R.id.state_of_game);
        MainActivity fragmentActivity = (MainActivity) getActivity();
        gameStateTV.setText(fragmentActivity.getGame().currentIncompleteWord());
    }
}