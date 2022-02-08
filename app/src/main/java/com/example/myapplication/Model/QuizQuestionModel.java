package com.example.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestionModel implements Parcelable {
    private final String question;
    private final ArrayList<String> optionList;
    private final String answer;

    public QuizQuestionModel(String question, List<String> optionList, String answer) {
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

    public QuizQuestionModel(Parcel parcel) {
        this.question = parcel.readString();
        this.optionList = (ArrayList<String>) parcel.readArrayList(QuizQuestionModel.class.getClassLoader());
        this.answer = parcel.readString();
    }

    public static final Parcelable.Creator<QuizQuestionModel> CREATOR = new Parcelable.Creator<QuizQuestionModel>() {
        public QuizQuestionModel createFromParcel(Parcel in) {
            return new QuizQuestionModel(in);
        }

        public QuizQuestionModel[] newArray(int size) {
            return new QuizQuestionModel[size];
        }
    };


}
