package com.aquar.android.myaquar_egypt.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aquar.android.myaquar_egypt.R;

import com.ortiz.touchview.TouchImageView;


public class struct_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_struct);

        TouchImageView img =  findViewById(R.id.img);
        img.setMaxZoom(3);
        img.setMinZoom(1);

    }





}