package com.example.singersgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.singersgallery.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        ImageView splashIcon = findViewById(R.id.splashIcon);
        TextView splashTitle = findViewById(R.id.splashTitle);
        TextView splashSubtitle = findViewById(R.id.splashSubtitle);
        View splashLine = findViewById(R.id.splashLine);
        TextView splashTagline = findViewById(R.id.splashTagline);

        splashIcon.setAlpha(0f);
        splashIcon.setScaleX(0.3f);
        splashIcon.setScaleY(0.3f);
        splashIcon.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(800).setStartDelay(300).start();

        splashTitle.setAlpha(0f);
        splashTitle.setTranslationY(60f);
        splashTitle.animate().alpha(1f).translationY(0f).setDuration(600).setStartDelay(900).start();

        splashSubtitle.setAlpha(0f);
        splashSubtitle.setTranslationY(60f);
        splashSubtitle.animate().alpha(1f).translationY(0f).setDuration(600).setStartDelay(1100).start();

        splashLine.setScaleX(0f);
        splashLine.animate().scaleX(1f).setDuration(500).setStartDelay(1400).start();

        splashTagline.setAlpha(0f);
        splashTagline.animate().alpha(1f).setDuration(500).setStartDelay(1700).start();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, ListActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 4000);
    }
}