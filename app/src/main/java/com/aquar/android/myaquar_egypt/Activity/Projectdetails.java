package com.aquar.android.myaquar_egypt.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.android.myaquar_egypt.R;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class Projectdetails extends AppCompatActivity {
    private SliderLayout Product_Slider;
    private Button see_more_btn , like_btn,struct_btn , location_btn , call_btn , share_btn , go360 ,send_email_btn;
    private TextView description;
    private ScrollView sc ;
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        see_more_btn =(Button)findViewById(R.id.see_more_btn);
        description=(TextView)findViewById(R.id.description);
        like_btn =(Button)findViewById(R.id.like);
        share_btn = findViewById(R.id.share);
        location_btn = findViewById(R.id.go_location);
        struct_btn=(Button)findViewById(R.id.structure);
        call_btn = findViewById(R.id.call);
       send_email_btn=findViewById(R.id.send_email);

       send_email_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendEmail();

           }
       });


        go360 = (Button)findViewById(R.id.go_youtube);

        go360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=F2xYkytHNxY&list=RDF2xYkytHNxY&start_radio=1"));
                startActivity(intent);

            }
        });
        struct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Projectdetails.this,struct_activity.class);
                startActivity(i);
            }
        });


        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liked_projects();
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=com.youssef.maggy.aqartest");
                share.setType("textDes/plain");
                startActivity(share);
            }
        });

        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + 30.145305 + "," + 31.630784 + " ("+"Shourok"+")";
                Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(map);
            }
        });
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contact = new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:01095488883"));
                startActivity(contact);

            }
        });

        see_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_all_description();
              //  see_more_btn.setVisibility(View.INVISIBLE);
            }
        });

        Product_Slider = (SliderLayout)findViewById(R.id.Product_Slider);
        DataOfSlider();


    }



    private void DataOfSlider() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

        file_maps.put("Phase 1", R.drawable.pashe1);
        file_maps.put("Phase 2", R.drawable.pashe2);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            Product_Slider.addSlider(textSliderView);
        }
        Product_Slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Product_Slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Product_Slider.setCustomAnimation(new DescriptionAnimation());
        Product_Slider.setDuration(4000);
    }
    private void show_all_description()
    {
        String x =see_more_btn.getText().toString();
        if (x.equals("See more")) {
            String s = (String) description.getText();
            s += getResources().getString(R.string.more_description);
            description.setText(s);
            see_more_btn.setText("See Less");
        }
        else
        {
            String s = getResources().getString(R.string.some_description);
            description.setText(s);
            see_more_btn.setText("See more");

        }

    }
    private void liked_projects ()
    {
        n++;
       if (n%2==0)
        like_btn.setBackgroundResource(R.drawable.like);
       else
           like_btn.setBackgroundResource(R.drawable.liked);
    }


    private void sendEmail() {

        String[] TO = {"someone@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        String recepientEmail = ""; // either set to destination email or leave empty
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        startActivity(emailIntent);


    }
}



