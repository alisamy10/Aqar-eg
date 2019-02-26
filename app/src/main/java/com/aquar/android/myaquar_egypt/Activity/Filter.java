package com.aquar.android.myaquar_egypt.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aquar.android.myaquar_egypt.R;

public class Filter extends AppCompatActivity {
    public RadioGroup rg1,rg2  , rg3;
    public RadioButton rb1,rb2,rb3,rb4 , rb5,rb6 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        rg1=findViewById(R.id.radiogroup1);
        rg3=findViewById(R.id.radiogroup3);
        rg2=findViewById(R.id.radiogroup2);
        rb1=findViewById(R.id.one);
        rb2=findViewById(R.id.two);
        rb3=findViewById(R.id.three);
        rb4=findViewById(R.id.four);
        rb5 = findViewById(R.id.five);
        rb6=findViewById(R.id.six);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rg1.clearCheck();
                rg2.clearCheck();
rg3.clearCheck();
                if (rb3.isChecked()||rb5.isChecked()) {
                    rb3.setChecked(false);
rb5.setChecked(false);
                }
                    rb1.setChecked(true);

//lkjlkj




            }
        });

        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();


                if (rb3.isChecked()||rb1.isChecked()) {
                    rb3.setChecked(false);
                    rb1.setChecked(false);
                }

                rb5.setChecked(true);


            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
                rg2.clearCheck();
rg3.clearCheck();
                if (rb1.isChecked()||rb5.isChecked()) {
                    rb1.setChecked(false);
rb5.setChecked(false);
                }
                    rb3.setChecked(true);


            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();

                if (rb4.isChecked()||rb5.isChecked()) {
                    rb4.setChecked(false);
                    rb5.setChecked(false);
                }
                    rb2.setChecked(true);


            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb2.isChecked()||rb5.isChecked()) {
                    rb2.setChecked(false);
                    rb5.setChecked(false);
                }
                rb4.setChecked(true);

            }
        });

    }
}