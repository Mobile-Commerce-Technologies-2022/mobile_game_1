package com.example.myapplication.QuizGame;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class QuizGameActivity extends AppCompatActivity {

    private List<QuizQuestion> questionList = null;
    private List<String> userAnswers = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_game);

        this.questionList = loadQuestions();
        this.userAnswers = new ArrayList<>();

        Collections.shuffle(questionList);
        AtomicInteger pos = new AtomicInteger(0);
        setQuestion(pos.getAndIncrement());
        Button submit = findViewById(R.id.btnSubmit);
        submit.setOnClickListener((View view) -> {
            if(pos.get() == 5) { // go to score page
                Toast.makeText(this, "GO TO SCORE PAGE", Toast.LENGTH_SHORT).show();
            } else {
                String selected = "";
                RadioGroup options = findViewById(R.id.rgOptions);
                int selectedId = options.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedId);
                selected = (String) rb.getText();
                userAnswers.add(selected);

                Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();

                setQuestion(pos.getAndIncrement());
            }
        });
    }


    /**
     * TODO: use AWS database to fetch questions
     * @return
     */
    private List<QuizQuestion> loadQuestions() {
        List<QuizQuestion> questionList = new ArrayList<>();
        String[] q1 = getResources().getStringArray(R.array.q1);
        questionList.add(new QuizQuestion(q1[0],
                new ArrayList<String>(){
                    {
                        add(q1[1]);
                        add(q1[2]);
                        add(q1[3]);
                        add(q1[4]);
                    }
                }, q1[5]));

        String[] q2 = getResources().getStringArray(R.array.q2);
        questionList.add(new QuizQuestion(q2[0],
                new ArrayList<String>(){
                    {
                        add(q2[1]);
                        add(q2[2]);
                        add(q2[3]);
                        add(q2[4]);
                    }
                }, q2[5]));

        String[] q3 = getResources().getStringArray(R.array.q3);
        questionList.add(new QuizQuestion(q3[0],
                new ArrayList<String>(){
                    {
                        add(q3[1]);
                        add(q3[2]);
                        add(q3[3]);
                        add(q3[4]);
                    }
                }, q3[5]));

        String[] q4 = getResources().getStringArray(R.array.q4);
        questionList.add(new QuizQuestion(q4[0],
                new ArrayList<String>(){
                    {
                        add(q4[1]);
                        add(q4[2]);
                        add(q4[3]);
                        add(q4[4]);
                    }
                }, q4[5]));

        String[] q5 = getResources().getStringArray(R.array.q5);
        questionList.add(new QuizQuestion(q5[0],
                new ArrayList<String>(){
                    {
                        add(q5[1]);
                        add(q5[2]);
                        add(q5[3]);
                        add(q5[4]);
                    }
                }, q5[5]));

        return questionList;
    }

    private void setQuestion(int pos) {
        List<String> optionList = this.questionList.get(pos).getOptionList();
        String question = this.questionList.get(pos).getQuestion();
        // receive quiz game components by id
        TextView tv = findViewById(R.id.questionDescription);
        RadioButton option1 = findViewById(R.id.radioOption1);
        RadioButton option2 = findViewById(R.id.radioOption2);
        RadioButton option3 = findViewById(R.id.radioOption3);
        RadioButton option4 = findViewById(R.id.radioOption4);
        // remove the checked button
        RadioGroup options =  findViewById(R.id.rgOptions);
        options.clearCheck();
        // update quiz game components with corresponding question info
        tv.setText(question);
        option1.setText(optionList.get(0));
        option2.setText(optionList.get(1));
        option3.setText(optionList.get(2));
        option4.setText(optionList.get(3));

    }


}
