package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class Contact_us extends AppCompatActivity {
    private TextView contct,location,mail,phone;
    private String instaUrl,faceUrl,youtubeUrl,twitterUrl;
    private LinearLayout parent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        contct=findViewById(R.id.contactus);
        location=findViewById(R.id.location);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        Get_Data();

        parent = findViewById(R.id.parentCountactUs);



    }
    private void Get_Data() {


        AndroidNetworking.get(ConstantsUrl.contactUs)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parent.setVisibility(View.VISIBLE);
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ContactUsModelObject array = gson.fromJson(response.toString(), ContactUsModelObject.class);

                        contct.setText(  array.getText());
                        location.setText(  array.getAddress());
                        mail.setText(  array.getMail());
                        phone.setText(  array.getPhone());
                        instaUrl=array.getInstagram();
                       // faceUrl=array.g
                        youtubeUrl=array.getYoutube();
                        twitterUrl=array.getTwitter();

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(Contact_us.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void openTwitter(View view) {

        Intent intent = null;
        try {
            // get the Twitter app if possible
            Contact_us.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=20536157"));
            Toast.makeText(this, "afnjldbfkadsf", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            Toast.makeText(this, "823459872345893", Toast.LENGTH_SHORT).show();
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/google"));
        }
        Contact_us.this.startActivity(intent);
        Toast.makeText(Contact_us.this,twitterUrl+"", Toast.LENGTH_SHORT).show();

    }

    public void openfacebook(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/104958162837"));
            startActivity(intent);
        } catch(Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/google")));
        }
    }

    public void openinsta(View view) {
        PackageManager manager = this.getPackageManager();
        try {
            Intent intent = manager.getLaunchIntentForPackage("com.instagram.android");
            if (intent == null) {
                throw new PackageManager.NameNotFoundException();
            }
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            this.startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openyoutube(View view) {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=F2xYkytHNxY&list=RDF2xYkytHNxY&start_radio=1"));
        startActivity(intent);

    }
}
