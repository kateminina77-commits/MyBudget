package com.example.mybudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation animation =
                AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        findViewById(R.id.splashIcon).startAnimation(animation);

        Thread splashThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                runOnUiThread(() -> {
                    Intent intent =
                            new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                });
            }
        });

        splashThread.start();
    }
}