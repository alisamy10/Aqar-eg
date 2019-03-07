package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;

public class AboutUs extends AppCompatActivity {
    TextView aboutUs;
    ScrollView parentOfAboutUs;
    private long backPressedTime;

    private AlertDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        myUtils.setLocale(this);
        aboutUs = findViewById(R.id.about_us_text);

        parentOfAboutUs = findViewById(R.id.parentOfAboutUs);


        dialog1 = new SpotsDialog.Builder().setContext(AboutUs.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();


        Get_Data();
    }

    private void Get_Data() {

        AndroidNetworking.get(ConstantsUrl.aboutUs)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parentOfAboutUs.setVisibility(View.VISIBLE);
                        dialog1.dismiss();

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);
                        aboutUs.setText(array.getText());

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        Toast.makeText(AboutUs.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(AboutUs.this, MainActivity.class));
        finish();

    }


}
