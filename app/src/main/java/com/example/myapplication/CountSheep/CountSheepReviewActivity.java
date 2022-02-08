package com.example.myapplication.CountSheep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.GameMenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.Singleton.MyHelper;

public class CountSheepReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_sheep_review);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int numSheeps = bundle.getInt("NUM_SHEEPS");

        EditText editText = findViewById(R.id.etUserSheep);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener((view -> {
            int userInput = Integer.parseInt(editText.getText().toString());
            Intent intent2 = new Intent(this, GameMenuActivity.class);
            Bundle bundle2 = new Bundle();
            double score = 1 - Math.abs((double)(numSheeps - userInput) / numSheeps );
            bundle2.putDouble("COUNTING_SCORE", score);
            intent2.putExtras(bundle2);
            MyHelper.getInstance().go2Activity(this, intent2);
        }));

    }
}
