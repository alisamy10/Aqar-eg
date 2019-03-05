package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aquar.myaquar_egypt.R;
import com.google.vr.sdk.proto.nano.CardboardDevice;


public class EventsAndNewsDetails extends AppCompatActivity {

    private Button Attend_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news_details);

        Attend_btn=findViewById(R.id.Attend);

        Attend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

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
