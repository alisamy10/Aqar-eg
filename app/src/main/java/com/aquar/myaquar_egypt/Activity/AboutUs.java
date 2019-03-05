package com.aquar.myaquar_egypt.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class AboutUs extends AppCompatActivity {
     TextView aboutUs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
         aboutUs = findViewById(R.id.about_us_text);

        Get_Data();
    }

    private void Get_Data() {

        AndroidNetworking.get(ConstantsUrl.aboutUs)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);

                        aboutUs.setText(  array.getText());

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(AboutUs.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }




}
