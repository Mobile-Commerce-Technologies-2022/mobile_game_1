package com.example.myapplication.Singleton;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.Model.QuizQuestion;
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
    public void setQuestion(AppCompatActivity view, int pos, List<QuizQuestion> questionList, int[] COLORS) {
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
    public List<QuizQuestion> loadQuestions(AppCompatActivity view) {
        List<QuizQuestion> questionList = new ArrayList<>();
        String[] q1 = view.getResources().getStringArray(R.array.q1);
        questionList.add(new QuizQuestion(q1[0],
                new ArrayList<String>(){
                    {
                        add(q1[1]);
                        add(q1[2]);
                        add(q1[3]);
                        add(q1[4]);
                    }
                }, q1[5]));

        String[] q2 = view.getResources().getStringArray(R.array.q2);
        questionList.add(new QuizQuestion(q2[0],
                new ArrayList<String>(){
                    {
                        add(q2[1]);
                        add(q2[2]);
                        add(q2[3]);
                        add(q2[4]);
                    }
                }, q2[5]));

        String[] q3 = view.getResources().getStringArray(R.array.q3);
        questionList.add(new QuizQuestion(q3[0],
                new ArrayList<String>(){
                    {
                        add(q3[1]);
                        add(q3[2]);
                        add(q3[3]);
                        add(q3[4]);
                    }
                }, q3[5]));

        String[] q4 = view.getResources().getStringArray(R.array.q4);
        questionList.add(new QuizQuestion(q4[0],
                new ArrayList<String>(){
                    {
                        add(q4[1]);
                        add(q4[2]);
                        add(q4[3]);
                        add(q4[4]);
                    }
                }, q4[5]));

        String[] q5 = view.getResources().getStringArray(R.array.q5);
        questionList.add(new QuizQuestion(q5[0],
                new ArrayList<String>(){
                    {
                        add(q5[1]);
                        add(q5[2]);
                        add(q5[3]);
                        add(q5[4]);
                    }
                }, q5[5]));

        String[] q6 = view.getResources().getStringArray(R.array.q6);
        questionList.add(new QuizQuestion(q6[0],
                new ArrayList<String>(){
                    {
                        add(q6[1]);
                        add(q6[2]);
                        add(q6[3]);
                        add(q6[4]);
                    }
                }, q6[5]));

        String[] q7 = view.getResources().getStringArray(R.array.q7);
        questionList.add(new QuizQuestion(q7[0],
                new ArrayList<String>(){
                    {
                        add(q7[1]);
                        add(q7[2]);
                        add(q7[3]);
                        add(q7[4]);
                    }
                }, q7[5]));

        return questionList;
    }
}
