package com.example.myapplication.QuizGame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.QuizQuestionModel;
import com.example.myapplication.Singleton.MyDBHelper;
import com.example.myapplication.Singleton.MyHelper;
import com.example.myapplication.R;

import java.util.List;

public class QuizGameMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_game_menu);

        Button btnAddQuestion = findViewById(R.id.btnAddQuestion);
        btnAddQuestion.setOnClickListener((View view) -> {
            MyHelper.getInstance().go2Activity(this, AddQuestionActivity.class);
        });

        Button btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setOnClickListener((View view) -> {

            MyDBHelper myDBHelper = new MyDBHelper(QuizGameMenuActivity.this);
            List<QuizQuestionModel> list = myDBHelper.getQuizQuestionModels();
            if(list.size() == 0) {
                myDBHelper.loadData();
            }
            MyHelper.getInstance().go2Activity(this, QuizGameActivity.class);
        });
    }

}
