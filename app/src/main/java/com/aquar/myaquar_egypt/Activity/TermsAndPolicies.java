package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
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

import dmax.dialog.SpotsDialog;

public class TermsAndPolicies extends AppCompatActivity {
     private  TextView textview ;
    private AlertDialog dialog1;

    private ScrollView parentOfTermesAndPolicies ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_policies);
        textview = findViewById(R.id.text_of_terms);


        parentOfTermesAndPolicies = findViewById(R.id.parentOfTermesAndPolicies);


        dialog1 = new SpotsDialog.Builder().setContext(TermsAndPolicies.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();

        Get_Data();
    }



        private void Get_Data() {

            AndroidNetworking.get(ConstantsUrl.tremsAndPolicies)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            dialog1.dismiss();
                            parentOfTermesAndPolicies.setVisibility(View.VISIBLE);
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();

                            AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);


                            textview.setText(array.getText());



                        }

                        @Override
                        public void onError(ANError anError) {
                                dialog1.dismiss();
                            Toast.makeText(TermsAndPolicies.this, "connection field", Toast.LENGTH_SHORT).show();

                        }
                    });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TermsAndPolicies.this, MainActivity.class));
        finish();

    }

}
