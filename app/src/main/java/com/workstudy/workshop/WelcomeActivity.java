package com.workstudy.workshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        blink();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playButton:
                startActivity(new Intent(WelcomeActivity.this, GameActivity.class));
                finish();
                break;
        }
    }

    int timeToBlink = 800;

    private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                   //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView txt = (TextView) findViewById(R.id.playText);
                        if(txt.getVisibility() == View.VISIBLE){
                            txt.setVisibility(View.INVISIBLE);
                            timeToBlink = 500;
                        }else{
                            txt.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
}
