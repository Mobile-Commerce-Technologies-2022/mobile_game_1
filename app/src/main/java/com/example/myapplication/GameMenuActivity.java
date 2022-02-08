package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.CountSheep.CountSheepActivity;
import com.example.myapplication.QuizGame.QuizGameMenuActivity;
import com.example.myapplication.Singleton.MyDBHelper;
import com.example.myapplication.Singleton.MyHelper;

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
            MyDBHelper myDBHelper = new MyDBHelper(GameMenuActivity.this);
            double countScore = myDBHelper.getRecentScore("COUNT");
            double quizScore = myDBHelper.getRecentScore("QUIZ");
            Toast.makeText(this, "Quiz Score: " + quizScore + ", Counting Score: " + countScore, Toast.LENGTH_SHORT).show();
        });

        Button btnBack2Welcome = findViewById(R.id.btnBack2Welcome);
        btnBack2Welcome.setOnClickListener(view -> MyHelper.getInstance().go2Activity(this, MainActivity.class));
    }

}
