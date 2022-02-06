package com.example.myapplication.QuizGame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuizQuestion {
    private String question;
    private ArrayList<String> optionList;
    private String answer;

    public QuizQuestion(String question, List<String> optionList, String answer) {
        this.question = question;
        this.optionList = (ArrayList<String>) optionList;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getOptionList() {
        return optionList;
    }

    public String getAnswer() {
        return answer;
    }




}
