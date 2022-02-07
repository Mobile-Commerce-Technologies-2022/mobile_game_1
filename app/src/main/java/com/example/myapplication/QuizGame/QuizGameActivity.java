package com.example.myapplication.QuizGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.QuizQuestion;
import com.example.myapplication.R;
import com.example.myapplication.Singleton.MyHelper;
import com.example.myapplication.Singleton.MyQuizHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QuizGameActivity extends AppCompatActivity {

    private List<QuizQuestion> questionList = null;
    private List<String> userAnswers = null;
    final int[] COLORS = new int[]{Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY, Color.MAGENTA, Color.DKGRAY};
    final int NUM_QUESTION = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_game);

        this.questionList = MyQuizHelper.getInstance().loadQuestions(this);
        this.userAnswers = new ArrayList<>();
        // randomize questions in the list, and pick 5
        Collections.shuffle(questionList);
        AtomicInteger pos = new AtomicInteger(0);

        // update the activity with given question data
        MyQuizHelper.getInstance().setQuestion(this,
                                                    pos.getAndIncrement(),
                                                    this.questionList,
                                                    this.COLORS);
        Button submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener((View view) -> {
            RadioGroup options = findViewById(R.id.rgOptions);
            int selectedId = options.getCheckedRadioButtonId();

            if(selectedId == -1) { // no answer selected
                Toast.makeText(this, "You must select an answer!!!", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rb = findViewById(selectedId);
                String selected = (String) rb.getText();

                userAnswers.add(selected);

                if(pos.get() == this.NUM_QUESTION) { // pass question go to quiz score page(reveal answers)
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(this, AnswerRevealActivity.class);
                    bundle.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) this.questionList);
                    bundle.putStringArrayList("userAnswers", (ArrayList<String>) this.userAnswers);
                    bundle.putInt("NUM_QUESTION", this.NUM_QUESTION);
                    intent.putExtras(bundle);
                    MyHelper.getInstance().go2Activity(this, intent);
                } else { // update data as long as it hasn't reach the last question
                    MyQuizHelper.getInstance().setQuestion(this, pos.getAndIncrement(),
                                                                this.questionList,
                                                                this.COLORS);
                }
            }
        });
    }
}
