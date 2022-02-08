package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Model.QuizQuestionModel;
import com.example.myapplication.Singleton.MyDBHelper;
import com.example.myapplication.Singleton.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener((view) ->{
            MyHelper.getInstance().go2Activity(this, GameMenuActivity.class);
        });
    }
}