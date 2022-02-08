package com.example.myapplication.CountSheep;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.myapplication.R;
import com.example.myapplication.Singleton.MyHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CountSheepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_sheep);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        final ConstraintLayout constraintLayout = findViewById(R.id.container);

        List<Integer> imageList = new ArrayList<>();
        final int NUM_IMAGE_VIEWS = 70;
        for(int i = 0; i < NUM_IMAGE_VIEWS; i++) {
            imageList.add(R.drawable.sheep);
        }

        Button test = findViewById(R.id.btnStartCounting);
        test.setOnClickListener(view -> {
            this.removeView(view); // remove button

            handleSheep(imageList, width, height, constraintLayout, this);

        });
    }

    private void removeView(View view) {
        ViewGroup vg = (ViewGroup) view.getParent();
        vg.removeView(view);
    }

    private void handleSheep(List<Integer> imageList, int width, int height, ConstraintLayout constraintLayout, Context context) {
        final Handler handler = new Handler();
        AtomicInteger timeCountDown = new AtomicInteger(60 * 1000); // 60 second
        // add imageview to the layout in random place with a delay between 1s and 3s
        Runnable runnable = new Runnable() {
            int i = 0; // counter of current imageView position
            public void run() {
                int delay = (int)(Math.random() * 2000) + 1000;
                if(i < imageList.size() && timeCountDown.get() > delay) {
                    //create a new ImageView object
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(imageList.get(i));
                    imageView.setId(View.generateViewId());

                    addSheep(width, height, imageView, constraintLayout);

                    handler.postDelayed(this, delay);

                    handler.postDelayed(() -> {
                        removeView(imageView);
                        timeCountDown.set(timeCountDown.get() - delay);
                        String msg = "Total:[" + imageList.size() + "], Pos:["+i+"], Time left:["+timeCountDown.get()+"]";
                        Log.i("Time Left", msg);
                        }, delay);
                    i++;

                } else {
                    if(timeCountDown.get() < 0) {
                        i--;
                        Log.i("Time", String.valueOf(i));
                    }
                    Intent intent = new Intent(context, CountSheepReviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("NUM_SHEEP", i);
                    intent.putExtras(bundle);
                    MyHelper.getInstance().go2Activity(context, intent);
                }
            }

            private void addSheep(int width, int height, ImageView imageView, ConstraintLayout constraintLayout) {
                // equivalent to android:layout_width and android:layout_height
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams((int)(Math.random() * width),
                        (int)(Math.random() * height));

                // Add layout parameters to ImageView
                imageView.setLayoutParams(params);
                // set constrain parameters to the view image
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                // equivalent to app:layout_constraintBottom_toBottomOf="parent"
                constraintSet.connect(imageView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 0);
                constraintSet.connect(imageView.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 0);
                constraintSet.connect(imageView.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
                constraintSet.connect(imageView.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 0);
                // equivalent to app:layout_constraintVertical_bias
                constraintSet.setVerticalBias(imageView.getId(), (float) (Math.random()));
                constraintSet.setHorizontalBias(imageView.getId(), (float) (Math.random()));
                // apply the constraints to the layout
                constraintSet.applyTo(constraintLayout);
                // Finally, add the ImageView to layout
                constraintLayout.addView(imageView);
            }

            private void removeView(View view) {
                ViewGroup vg = (ViewGroup) view.getParent();
                vg.removeView(view);
            }
        };

        handler.postDelayed(runnable, 0); //for initial delay..
    }

}
