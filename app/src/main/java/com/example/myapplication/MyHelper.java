package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
}
