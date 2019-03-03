package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;

import com.aquar.myaquar_egypt.R;
import com.facebook.FacebookSdk;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mySharedPreference.init(this);

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
//                    startActivity(new Intent(Splash.this, Login.class));
                    checkLoggedInUser();
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

    private void checkLoggedInUser() {


        if (mySharedPreference.getUserOBJ().equals("")) {
            //user obj not available
            startActivity(new Intent(Splash.this, Login.class));
        } else {
            //user obj available
            startActivity(new Intent(Splash.this, MainActivity.class));
        }


    }
}
