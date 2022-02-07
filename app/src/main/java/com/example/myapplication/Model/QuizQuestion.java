package com.example.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.question);
        parcel.writeList(this.optionList);
        parcel.writeString(this.answer);
    }

    public QuizQuestion(Parcel parcel) {
        this.question = parcel.readString();
        this.optionList = (ArrayList<String>) parcel.readArrayList(QuizQuestion.class.getClassLoader());
        this.answer = parcel.readString();
    }

    public static final Parcelable.Creator<QuizQuestion> CREATOR = new Parcelable.Creator<QuizQuestion>() {
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };


}
