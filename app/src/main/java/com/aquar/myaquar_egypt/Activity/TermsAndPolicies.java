package com.aquar.myaquar_egypt.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class TermsAndPolicies extends AppCompatActivity {
     private  TextView textview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_policies);
        textview = findViewById(R.id.text_of_terms);

        Get_Data();
    }



        private void Get_Data() {

            AndroidNetworking.get(ConstantsUrl.tremsAndPolicies)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Gson gson = new GsonBuilder().setPrettyPrinting().create();

                            AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);


                            textview.setText(array.getText());





                        }

                        @Override
                        public void onError(ANError anError) {

                            Toast.makeText(TermsAndPolicies.this, "connection field", Toast.LENGTH_SHORT).show();

                        }
                    });


    }
}
