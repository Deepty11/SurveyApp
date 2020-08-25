package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class InitialViewActivity extends AppCompatActivity {
    /*splash screen duration before next activity*/
    public static final int SCREEN_SPLASH_DURATION = 5000;

    //variables for animations
    Animation topAnim, bottomAnim;
    ImageView splash_screen_image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_initial_view);
        animateInitialScreenWithSplashEffect();

        gotoNextActivity();

    }
    private void animateInitialScreenWithSplashEffect() {
        //load animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        splash_screen_image = findViewById(R.id.splash_screen_image);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        //setAnimation
        splash_screen_image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
    }

    private void gotoNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitialViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SCREEN_SPLASH_DURATION);
    }

}