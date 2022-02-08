package com.example.myapplication.QuizGame;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.QuizQuestionModel;
import com.example.myapplication.R;
import com.example.myapplication.Singleton.MyDBHelper;
import com.example.myapplication.Singleton.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_add_question);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            String question = ((EditText) findViewById(R.id.etQuestion)).getText().toString();
            String optionA = ((EditText) findViewById(R.id.etOptionA)).getText().toString();
            String optionB = ((EditText) findViewById(R.id.etOptionB)).getText().toString();
            String optionC = ((EditText) findViewById(R.id.etOptionC)).getText().toString();
            String optionD = ((EditText) findViewById(R.id.etOptionD)).getText().toString();
            String answer = ((EditText) findViewById(R.id.etAnswer)).getText().toString();

            List<String> optionList = new ArrayList<String>() {
                {
                    add(optionA);
                    add(optionB);
                    add(optionC);
                    add(optionD);
                }
            };

            if(!optionList.contains(answer)) {
                Toast.makeText(this, "Your answer must be identical to the option", Toast.LENGTH_SHORT).show();
            }else if(optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty()
            || answer.isEmpty() || question.isEmpty()) { // prevent empty elements
                Toast.makeText(this, "Your question components cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                QuizQuestionModel quizQuestionModel = new QuizQuestionModel(question, optionList, answer);
                MyDBHelper myDBHelper = new MyDBHelper(AddQuestionActivity.this);
                boolean success = myDBHelper.addQuizQuestion(quizQuestionModel);
                if(success) {
                    Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to add question", Toast.LENGTH_SHORT).show();
                }

                MyHelper.getInstance().go2Activity(this, QuizGameMenuActivity.class);
            }
        });
    }
}
