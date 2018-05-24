package com.animation.android.valueanimator;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;

        final TextView textView = (TextView) findViewById(R.id.textView);

        // animate from 0 - 100
        final ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // get the value 0 - 100
                // int currentValue = (Integer) animation.getAnimatedValue();
                // textView.setText("Value is " + currentValue);

                // fractional values 0 - 1
                float fraction = animation.getAnimatedFraction();
//                textView.setText("fraction is " + fraction);
                // height * 0.745 - looks correct on emulator and device ???
                textView.setTranslationY(height * 0.745f * fraction);
//                textView.setTranslationX(width * 0.745f * fraction);
            }
        });
        animator.setDuration(5000);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.setRepeatCount(animator.INFINITE);
        animator.setRepeatMode(animator.REVERSE);
        animator.start();
    }
}
