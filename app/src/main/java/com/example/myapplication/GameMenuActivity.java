package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
            // display pop up window
//            Intent intent = getIntent();
//            if(intent != null && intent.getExtras() != null) {
//                Bundle bundle = intent.getExtras();
//                double countingScore = bundle.getDouble("COUNTING_SCORE");
//                Log.i("COUNTING_SCORE", String.valueOf(countingScore));
//                Toast.makeText(this,String.valueOf(countingScore), Toast.LENGTH_LONG).show();
//            }
            MyDBHelper myDBHelper = new MyDBHelper(GameMenuActivity.this);
            double countScore = myDBHelper.getRecentScore("COUNT");
            double quizScore = myDBHelper.getRecentScore("QUIZ");
            Toast.makeText(this, "Quiz Score: " + quizScore + ", Counting Score: " + countScore, Toast.LENGTH_SHORT).show();
        });
    }

}
