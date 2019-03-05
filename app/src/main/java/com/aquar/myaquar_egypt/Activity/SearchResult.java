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
        GetCategoryData(Filter.radioBtn , Filter.itemMaxPrice ,Filter.itemMinPrice,Filter.itemMaxArea,
                Filter.itemMinArea,Filter.itemMaxBedroom,Filter.itemMinBedroom,Filter.itemMaxBathroom,
                Filter.itemMinBathroom,Filter.locationOfSpinner
                );


    }






    private void GetCategoryData(int categoryId ,String max_price ,String min_price , String max_area ,String min_area
                 ,String max_badrooms ,String min_badrooms , String max_bathrooms ,String min_bathrooms,
                                    String locations  )
    {
        JSONObject object = new JSONObject();
        try {
            object.put("category_id", categoryId);
            object.put("max_price", max_price);
            object.put("min_price", min_price);
            object.put("max_area", max_area);
            object.put("min_area", min_area);
            object.put("max_badrooms", max_badrooms);
            object.put("min_badrooms", min_badrooms);
            object.put("max_bathrooms", max_bathrooms);
            object.put("min_bathrooms", min_bathrooms);
            object.put("locations", locations);

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
                        Toast.makeText(SearchResult.this, list.get(0).getProduct_id()+"", Toast.LENGTH_SHORT).show();

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
