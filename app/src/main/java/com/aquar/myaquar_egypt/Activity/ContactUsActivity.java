package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Objects;

import dmax.dialog.SpotsDialog;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView contct, location, mail, phone;
    private String instaUrl, faceUrl, youtubeUrl, twitterUrl;
    private ScrollView parent;
    //    private AlertDialog dialog1;
    private Dialog dialog1;
    private LinearLayout location_LL, call_LL, email_LL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        myUtils.setLocale(this);

        contct = findViewById(R.id.contactus);
        location = findViewById(R.id.location);
        mail = findViewById(R.id.mail);
        phone = findViewById(R.id.phone);
        findViewById(R.id.location_LL).setOnClickListener(this);
        findViewById(R.id.email_LL).setOnClickListener(this);
        findViewById(R.id.call_LL).setOnClickListener(this);
        Get_Data();

        parent = findViewById(R.id.parentCountactUs);

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();
//        location_LL.setOnClickListener(this);
//        email_LL.setOnClickListener(this);
//        call_LL.setOnClickListener(this);


    }

    private void Get_Data() {


        AndroidNetworking.get(ConstantsUrl.contactUs)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog1.dismiss();
                        parent.setVisibility(View.VISIBLE);
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ContactUsModelObject array = gson.fromJson(response.toString(), ContactUsModelObject.class);

                        contct.setText(array.getText());
                        location.setText(array.getAddress());
                        mail.setText(array.getMail());
                        phone.setText(array.getPhone());


                        instaUrl = array.getInstagram();
                        faceUrl = array.getFacebook();
                        youtubeUrl = array.getYoutube();
                        twitterUrl = array.getTwitter();

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        myUtils.handleError(ContactUsActivity.this, anError.getErrorBody(), anError.getErrorCode());
//                        Toast.makeText(ContactUsActivity.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void openTwitter(View view) {


        Intent intent = null;
        try {
            // get the Twitter app if possible
            ContactUsActivity.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        }
        ContactUsActivity.this.startActivity(intent);
    }

    public void openfacebook(View view) {


        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(faceUrl));
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(faceUrl)));
        }


    }

    public void openinsta(View view) {
        try {


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(instaUrl));
            startActivity(intent);
        } catch (Exception o) {

//          Toast.makeText(this, instaUrl+"", Toast.LENGTH_SHORT).show();
        }
    }

    public void openyoutube(View view) {

        try {


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(youtubeUrl));
            startActivity(intent);
        } catch (Exception o) {
//            Toast.makeText(this,youtubeUrl+ "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ContactUsActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.email_LL:
                sendEmail();
                break;
            case R.id.call_LL:
                Intent contact = new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:01033110330"));
                startActivity(contact);
                break;
            case R.id.location_LL:
                locationOfComp();
                break;
            default:
                break;
        }
    }

    private void locationOfComp() {
        String geoUri = "http://maps.google.com/maps?q=loc:" + 30.003304 + "," + 31.4247852 + " (" + "Shourok" + ")";
        Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        startActivity(map);
    }

    private void sendEmail() {
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        String[] TO = {"info@myaquar-eg.com"};
        String recepientEmail = ""; // either set to destination email or leave empty
        String Subject;

        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {
                String Lines = "--------------------------------";
                Subject = "";
                String Content = "Name              : " + userPOJO.getUsername()
                        + "\n" + "Mobile Phone : " + userPOJO.getPhone()
                        + "\n" + "Job Title         : " + userPOJO.getJobTitle();
                String Body = "\n" + "\n" + "\n" + "\n" + "\n" + Lines + "\n" + "\n" + Content + "\n" + "\n" + Lines;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

//                Log.d("developer: ", list.get(0).getDeveloper() + "");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, Body);

                startActivity(emailIntent);
            }

        } catch (Exception e) {
            Subject = "Enquiry regarding: ";
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");

            startActivity(emailIntent);
        }


    }
}
