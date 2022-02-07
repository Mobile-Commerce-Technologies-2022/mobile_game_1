package com.example.myapplication.Singleton;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.Model.QuizQuestion;
import com.example.myapplication.R;

import java.util.List;

/**
 * Singleton class for global helper methods
 */
public class MyHelper {
    private MyHelper(){}

    private enum Singleton {
        INSTANCE;

        private final MyHelper instance;


        Singleton() {
            instance = new MyHelper();
        }

        private MyHelper getInstance() {
            return instance;
        }
    }

    public static MyHelper getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /**
     * Go to a given activity page
     * @param context Activity Context
     * @param cls Activity class
     */
    public void go2Activity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(context, intent, Bundle.EMPTY);
    }

    public void go2Activity(Context context, Intent intent) {
        startActivity(context, intent, Bundle.EMPTY);
    }
}
