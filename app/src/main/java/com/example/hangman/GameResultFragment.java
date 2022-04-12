package com.example.hangman;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class GameResultFragment extends Fragment {

    TextView gameResultTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpFragmentGUI(container);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    private void setUpFragmentGUI(ViewGroup container) {
        if(gameResultTV == null)   {
            gameResultTV = new TextView(getActivity());
            gameResultTV.setGravity(Gravity.CENTER_HORIZONTAL);
            gameResultTV.setTextSize(30);
            container.addView(gameResultTV);

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        gameResultTV.setText("Good Luck");
    }

    public void setResult(String result) {
        gameResultTV.setText(result);
    }
}