package com.example.myapplication.CountSheep;


import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

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

        Button test = findViewById(R.id.btnStartCounting);
        test.setOnClickListener(view -> {
            this.removeView(view); // remove button

            List<Integer> imageList = new ArrayList<>();
            final int NUM_IMAGE_VIEWS = 5;
                    //(int) Math.random() * 60;
            for(int i = 0; i < NUM_IMAGE_VIEWS; i++) {
                imageList.add(R.drawable.sheep);
            }

            final Handler handler = new Handler();
            // add imageview to the layout in random place with a delay between 1s and 3s
            Runnable runnable = new Runnable() {
                int i=0;
                public void run() {
                    if(i < imageList.size()) {
                        addSheep(width, height, imageList.get(i), constraintLayout);
                        int delay = (int)(Math.random()*2000) + 1000;
                        i++;
                        handler.postDelayed(this, delay);  //for interval...
                    }
                }

                private void addSheep(int width, int height, int imageId, ConstraintLayout constraintLayout) {
                    int x = (int)(Math.random() * width);
                    int y = (int)(Math.random() * height);
                    float horizontalBias = (float) (Math.random());
                    float verticalBias = (float) (Math.random());
                    // equivalent to android:layout_width and android:layout_height
                    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(x, y);
                    //create a new ImageView object
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(imageId);
                    // Add layout parameters to ImageView
                    imageView.setLayoutParams(params);
                    imageView.setId(View.generateViewId());
                    // set constrain parameters to the view image
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    // equivalent to app:layout_constraintBottom_toBottomOf="parent"
                    constraintSet.connect(imageView.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 0);
                    constraintSet.connect(imageView.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 0);
                    constraintSet.connect(imageView.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
                    constraintSet.connect(imageView.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 0);
                    // equivalent to app:layout_constraintVertical_bias
                    constraintSet.setVerticalBias(imageView.getId(), horizontalBias);
                    constraintSet.setHorizontalBias(imageView.getId(), verticalBias);
                    // apply the constraints to the layout
                    constraintSet.applyTo(constraintLayout);
                    // Finally, add the ImageView to layout
                    constraintLayout.addView(imageView);
                }

            };
            handler.postDelayed(runnable, 200); //for initial delay..
        });
    }


    private void removeView(View view) {
        ViewGroup vg = (ViewGroup) view.getParent();
        vg.removeView(view);
    }
}
