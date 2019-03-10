package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;

public class Contact_us extends AppCompatActivity {
    private TextView contct,location,mail,phone;
    private String instaUrl,faceUrl,youtubeUrl,twitterUrl;
    private ScrollView parent ;
    private AlertDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        myUtils.setLocale(this);

        contct=findViewById(R.id.contactus);
        location=findViewById(R.id.location);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        Get_Data();

        parent = findViewById(R.id.parentCountactUs);
        dialog1 = new SpotsDialog.Builder().setContext(Contact_us.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();


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

                        contct.setText(  array.getText());
                        location.setText(  array.getAddress());
                        mail.setText(  array.getMail());
                        phone.setText(  array.getPhone());


                        instaUrl=array.getInstagram();
                        faceUrl=array.getFacebook();
                        youtubeUrl=array.getYoutube();
                        twitterUrl=array.getTwitter();

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();

                        Toast.makeText(Contact_us.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void openTwitter(View view) {

        try {


        Intent intent = null;
        try {
            // get the Twitter app if possible
            Contact_us.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        }
        Contact_us.this.startActivity(intent);
    }catch (Exception o ){}
        Toast.makeText(this, twitterUrl+"", Toast.LENGTH_SHORT).show();
    }

    public void openfacebook(View view) {


        try {



        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(faceUrl));
            startActivity(intent);
        } catch(Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(faceUrl)));
        }


        }catch (Exception n ){
            Toast.makeText(this, faceUrl+"", Toast.LENGTH_SHORT).show();
        }
    }

    public void openinsta(View view) {
      try {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(instaUrl));
        startActivity(intent);
      }catch (Exception o ){

          Toast.makeText(this, instaUrl+"", Toast.LENGTH_SHORT).show();
      }
    }

    public void openyoutube(View view) {

        try {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(youtubeUrl));
        startActivity(intent);
    }catch (Exception o ){
            Toast.makeText(this,youtubeUrl+ "", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Contact_us.this, MainActivity.class));
        finish();

    }

}
