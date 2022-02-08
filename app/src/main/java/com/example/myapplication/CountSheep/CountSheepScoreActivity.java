package com.example.myapplication.CountSheep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.GameMenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.Singleton.MyHelper;


public class CountSheepScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_sheep_score);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double score = bundle.getDouble("COUNTING_SCORE");

        TextView textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText("Score: " + score);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            MyHelper.getInstance().go2Activity(this, GameMenuActivity.class);
        });
    }
}
