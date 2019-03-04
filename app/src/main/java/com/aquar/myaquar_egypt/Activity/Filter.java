package com.aquar.myaquar_egypt.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
import com.aquar.myaquar_egypt.Model.Search.SearchModelObject;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.widget.ArrayAdapter;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Filter extends AppCompatActivity {
    public RadioGroup rg1, rg2, rg3;
    public RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    Spinner maxarea,minarea,minbed,maxbed,location,minbath,maxbath,minprice,maxprice;



    ArrayAdapter<Integer> adapter;

    ArrayAdapter<String> adapterString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        rg1 = findViewById(R.id.radiogroup1);
        rg3 = findViewById(R.id.radiogroup3);
        rg2 = findViewById(R.id.radiogroup2);
        rb1 = findViewById(R.id.one);
        rb2 = findViewById(R.id.two);
        rb3 = findViewById(R.id.three);
        rb4 = findViewById(R.id.four);
        rb5 = findViewById(R.id.five);
        rb6 = findViewById(R.id.six);
        maxarea =findViewById(R.id.maxAreaSpinner);
        minarea=findViewById(R.id.minAreaSpinner);
        minbed=findViewById(R.id.minBedroomsSpinner);
        maxbed=findViewById(R.id.maxBedroomSpinner);
        location=findViewById(R.id.locationSpinner);
        minbath=findViewById(R.id.minbathroomsSpinner);
        maxbath=findViewById(R.id.maxBathroomsSpinner);
        minprice=findViewById(R.id.minPriceSpinner);
        maxprice=findViewById(R.id.maxPriceSpinner);


        radioButton();
        Get_Data();







    }



    private void Get_Data() {


        AndroidNetworking.get(ConstantsUrl.search)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        SearchModelObject array = gson.fromJson(response.toString(), SearchModelObject.class);



                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMax_area());
                        minarea.setAdapter(adapter);

                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMin_area());
                        maxarea.setAdapter(adapter);

                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMin_price());
                        minprice.setAdapter(adapter);

                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMax_price());
                        maxprice.setAdapter(adapter);


                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMin_badrooms());
                        minbed.setAdapter(adapter);

                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMax_badrooms());
                        maxbed.setAdapter(adapter);

                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMin_bathrooms());
                        minbath.setAdapter(adapter);

                        adapter = new ArrayAdapter<Integer>(getApplicationContext(),R.layout.custom_simple_text, array.getMax_bathrooms());
                        maxbath.setAdapter(adapter);

                        adapterString = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, array.getLocations());
                        location.setAdapter(adapterString);


//                        location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                locationSpinner
//                                ((TextView) view).setTextColor(getResources().getColor(R.color.Red));
//                            }
//                        });







                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(Filter.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    private void radioButton(){

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb3.isChecked() || rb5.isChecked()) {
                    rb3.setChecked(false);
                    rb5.setChecked(false);
                }
                rb1.setChecked(true);


            }
        });

        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();


                if (rb3.isChecked() || rb1.isChecked()) {
                    rb3.setChecked(false);
                    rb1.setChecked(false);
                }

                rb5.setChecked(true);


            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb1.isChecked() || rb5.isChecked()) {
                    rb1.setChecked(false);
                    rb5.setChecked(false);
                }
                rb3.setChecked(true);


            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();

                if (rb4.isChecked() || rb5.isChecked()) {
                    rb4.setChecked(false);
                    rb5.setChecked(false);
                }
                rb2.setChecked(true);


            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb2.isChecked() || rb5.isChecked()) {
                    rb2.setChecked(false);
                    rb5.setChecked(false);
                }
                rb4.setChecked(true);

            }
        });
    }
}