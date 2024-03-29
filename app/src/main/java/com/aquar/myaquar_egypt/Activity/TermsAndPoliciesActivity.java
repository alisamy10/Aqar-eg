package com.aquar.myaquar_egypt.Activity;

import android.app.Dialog;
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
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class TermsAndPoliciesActivity extends AppCompatActivity {
    private TextView textview;
    //    private AlertDialog dialog1;
    private Dialog dialog1;

    private ScrollView parentOfTermesAndPolicies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_policies);

        myUtils.setLocale(this);
        definitions();

        showDialog();
        Get_Data();
    }


    private void definitions() {

        textview = findViewById(R.id.text_of_terms);
        parentOfTermesAndPolicies = findViewById(R.id.parentOfTermesAndPolicies);


    }

    private void showDialog() {

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

    }


    private void Get_Data() {

        AndroidNetworking.get(ConstantsUrl.termsAndPolicies)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                            dialog1.dismiss();
                        parentOfTermesAndPolicies.setVisibility(View.VISIBLE);
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);
                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            textview.setText(array.getText());

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        Toast.makeText(TermsAndPoliciesActivity.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });


    }


}
