package com.example.myapplication.QuizGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.GameMenuActivity;
import com.example.myapplication.Model.QuizQuestion;
import com.example.myapplication.R;
import com.example.myapplication.Singleton.MyHelper;
import com.example.myapplication.Singleton.MyQuizHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AnswerRevealActivity extends AppCompatActivity {
    private List<QuizQuestion> questionList = null;
    private List<String> userAnswers = null;
    final int[] COLORS = new int[]{Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY, Color.MAGENTA, Color.DKGRAY};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_reveal);
        // received from QuizGameActivity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userAnswers = bundle.getStringArrayList("userAnswers");
        questionList = bundle.getParcelableArrayList("questionList");
        final int NUM_QUESTION = bundle.getInt("NUM_QUESTION");

        AtomicInteger score = new AtomicInteger(0);
        AtomicInteger pos = new AtomicInteger(0);

        RadioGroup options = findViewById(R.id.rgOptions);

        MyQuizHelper.getInstance().setQuestion(this,
                                                    pos.getAndIncrement(),
                                                    this.questionList,
                                                    this.COLORS);
        ArrayList<RadioButton> rbList = (ArrayList<RadioButton>) MyQuizHelper.getInstance().getRadioButtonsByGroup(options);
        showCorrectAnswer(pos, score, rbList);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener((view -> {
            if(pos.get() < NUM_QUESTION) {
                MyQuizHelper.getInstance().setQuestion(this,
                                                            pos.getAndIncrement(),
                                                            this.questionList,
                                                            this.COLORS);
                ArrayList<RadioButton> rbList_ = (ArrayList<RadioButton>) MyQuizHelper.getInstance().getRadioButtonsByGroup(options);
                showCorrectAnswer(pos, score, rbList_);
            } else if(pos.get() == NUM_QUESTION) { // TODO: store the score in database
                Toast.makeText(this, "Final Score: " + score + "/5", Toast.LENGTH_SHORT).show();
                MyHelper.getInstance().go2Activity(this, GameMenuActivity.class);
            }
        }));
    }
    private void showCorrectAnswer(AtomicInteger pos, AtomicInteger score, List<RadioButton> rbList) {
        int index = pos.get()-1; //counter increment
        String userAnswer = this.userAnswers.get(index);
        String answer = this.questionList.get(index).getAnswer();

        // reset background color
        for(RadioButton rb : rbList) {
            rb.setBackgroundColor(Color.WHITE);
        }

        if(!answer.equals(userAnswer)) {
            // highlight answer as green
            score.getAndIncrement();
            for(RadioButton rb : rbList) {
                String rbText = (String) rb.getText();
                if(rbText.equals(userAnswer)) {
                    rb.setBackgroundColor(Color.RED);
                } else if(rbText.equals(answer)) {
                    rb.setBackgroundColor(Color.GREEN);
                }
            }
        } else {
            //highlight user answer as red, correct answer as green
            for(RadioButton rb : rbList) {
                String rbText = (String) rb.getText();
                if(rbText.equals(userAnswer)) {
                    rb.setBackgroundColor(Color.GREEN);
                }
            }
        }
    }
}
