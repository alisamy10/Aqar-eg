package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects> list  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);




        mRecyclerView = findViewById(R.id.recyclerView_search_result);





       try {
           if (Filter.locationOfSpinner.equals("all")) {
               Filter.locationOfSpinner = null;

           }
       }catch (Exception e){}

//        GetCategoryData(Filter.radioBtn , Filter.itemMaxPrice ,Filter.itemMinPrice,Filter.itemMaxArea,
//                Filter.itemMinArea,Filter.itemMaxBedroom,Filter.itemMinBedroom,Filter.itemMaxBathroom,
//                Filter.itemMinBathroom,Filter.locationOfSpinner
//                );


GetCategoryData();
        Toast.makeText(this,Filter.locationOfSpinner+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Filter.radioBtn+"", Toast.LENGTH_SHORT).show();



    }






    private void GetCategoryData()
    {
        JSONObject object = new JSONObject();
        try {
            object.put("category_id",1);
            object.put("max_price",6100000);
            object.put("min_price",1);
            object.put("max_area",240);
            object.put("min_area",40);
            object.put("max_badrooms",4);
            object.put("min_badrooms",1);
            object.put("max_bathrooms",3);
            object.put("min_bathrooms",1);
            object.put("locations",null);

        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.SearchResult)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {



                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ModelArray array = gson.fromJson(response.toString(), ModelArray.class);
                        list = array.getProjects();
                        setRecyclerData(list);


                        Toast.makeText(SearchResult.this, list.get(0).getProduct_id()+"rfefwer", Toast.LENGTH_SHORT).show();

                        mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

                            @Override
                            public void intent_to_detales(int pos, ImageView imageView ) {
                                go_detales(pos, imageView);



                            }
                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(SearchResult.this, "fnfEJKASF BSF", Toast.LENGTH_SHORT).show();


                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {

        mAdapter = new example_adapter_for_home_fragment(SearchResult.this, list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }
     public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(SearchResult.this, Projectdetails.class);
        startActivity(intent);
    }
}
