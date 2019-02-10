package com.aquar.android.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aquar.android.myaquar_egypt.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer();
    }


    public void Timer() {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(Splash.this, Login.class));

                }
            }
        };
        timer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timer();
    }
}
