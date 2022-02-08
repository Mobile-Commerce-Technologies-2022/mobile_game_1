package com.example.myapplication.Singleton;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.Model.QuizQuestionModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MyQuizHelper {
    private MyQuizHelper(){}

    private enum Singleton {
        INSTANCE;

        private final MyQuizHelper instance;


        Singleton() {
            instance = new MyQuizHelper();
        }

        private MyQuizHelper getInstance() {
            return instance;
        }
    }

    public static MyQuizHelper getInstance() {
        return MyQuizHelper.Singleton.INSTANCE.getInstance();
    }

    /**
     * Update Quiz Question elements with corresponding element in questionList
     * @param view Activity
     * @param pos Index of a question in questionList
     * @param questionList Contrainer for QuizQuestion Object
     * @param COLORS
     */
    public void setQuestion(AppCompatActivity view, int pos, List<QuizQuestionModel> questionList, int[] COLORS) {
        List<String> optionList = questionList.get(pos).getOptionList();
        String question = questionList.get(pos).getQuestion();
        // receive quiz game components by id
        TextView tv = view.findViewById(R.id.questionDescription);
        RadioButton option1 = view.findViewById(R.id.radioOption1);
        RadioButton option2 = view.findViewById(R.id.radioOption2);
        RadioButton option3 = view.findViewById(R.id.radioOption3);
        RadioButton option4 = view.findViewById(R.id.radioOption4);
        // remove the checked button
        RadioGroup options =  view.findViewById(R.id.rgOptions);
        options.clearCheck();
        // update quiz game components with corresponding question info
        tv.setText((pos+1) + "." + question);
        option1.setText(optionList.get(0));
        option2.setText(optionList.get(1));
        option3.setText(optionList.get(2));
        option4.setText(optionList.get(3));
        // change background color
        ConstraintLayout bgElement = view.findViewById(R.id.container);
        bgElement.setBackgroundColor(COLORS[pos]);
    }

    public List<RadioButton> getRadioButtonsByGroup(RadioGroup rg) {
        int count = rg.getChildCount();

        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<>();
        for (int i=0;i<count;i++) {
            View o = rg.getChildAt(i);
            if (o instanceof RadioButton) {
                listOfRadioButtons.add((RadioButton)o);
            }
        }
        return listOfRadioButtons;
    }


    /**
     * TODO: use AWS database to fetch questions
     * @return
     */
    public List<QuizQuestionModel> loadQuestions(AppCompatActivity view) {
        MyDBHelper myDBHelper = new MyDBHelper(view);
        return myDBHelper.getQuizQuestionModels();
    }
}
