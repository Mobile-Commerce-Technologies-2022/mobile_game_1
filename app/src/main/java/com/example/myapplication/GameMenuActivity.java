package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.CountSheep.CountSheepActivity;
import com.example.myapplication.QuizGame.QuizGameMenuActivity;

public class GameMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);

        Button btnQuizGame = findViewById(R.id.btnQuizGame);
        btnQuizGame.setOnClickListener((View view) ->
                MyHelper.getInstance().go2Activity(this, QuizGameMenuActivity.class));

        Button btnCountSheep = findViewById(R.id.btnCountSheep);
        btnCountSheep.setOnClickListener((View view) ->
                MyHelper.getInstance().go2Activity(this, CountSheepActivity.class));

        Button btnShowScore = findViewById(R.id.btnShowScore);
        btnShowScore.setOnClickListener((View view) -> {
            // display pop up window
        });
    }

}
