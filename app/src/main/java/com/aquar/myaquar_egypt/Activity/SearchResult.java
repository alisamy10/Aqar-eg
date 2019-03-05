package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.Fragments.fragment_home;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class SearchResult extends AppCompatActivity {

  private   fragment_home getid = new fragment_home();
    private RecyclerView mRecyclerView;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects> list  = new ArrayList<>();
    private String categoryId ;
    private AlertDialog dialog1;
    private LinearLayout parentOfSearchResult ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        parentOfSearchResult = findViewById(R.id.parentOfSearchResult);

        dialog1 = new SpotsDialog.Builder().setContext(SearchResult.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();

        mRecyclerView = findViewById(R.id.recyclerView_search_result);

        //for cast category id from int to string and check if it = 0 give it ""
          categoryId = String.valueOf(Filter.radioBtn);


        cheakIfDataIsAll();

        Toast.makeText(this, categoryId+"", Toast.LENGTH_SHORT).show();

        GetCategoryData(categoryId, Filter.itemMaxPrice ,Filter.itemMinPrice,Filter.itemMaxArea,
               Filter.itemMinArea,Filter.itemMaxBedroom,Filter.itemMinBedroom,Filter.itemMaxBathroom,
               Filter.itemMinBathroom,Filter.locationOfSpinner
               );





    }






    private void GetCategoryData(String category, String maxPrice , String minPrice , String maxArea , String minArea , String maxBedrooms ,
                                 String minBedrooms , String maxBathrooms  , String minBathrooms , String locations
                                 )
    {
        JSONObject object = new JSONObject();
        try {
            object.put("category_id",category);
            object.put("max_price",maxPrice);
            object.put("min_price",minPrice);
            object.put("max_area",maxArea);
            object.put("min_area",minArea);
            object.put("max_badrooms",maxBedrooms);
            object.put("min_badrooms",maxBedrooms);
            object.put("max_bathrooms",maxBathrooms);
            object.put("min_bathrooms",maxBedrooms);
            object.put("locations",locations);

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
                      parentOfSearchResult.setVisibility(View.VISIBLE);
                     dialog1.dismiss();

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ModelArray array = gson.fromJson(response.toString(), ModelArray.class);
                        list = array.getProjects();
                        setRecyclerData(list);


                        Toast.makeText(SearchResult.this, list.get(0).getProduct_id()+"rfefwer", Toast.LENGTH_SHORT).show();

                        mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

                            @Override
                            public void intent_to_detales(int pos, ImageView imageView ) {
                                go_detales(pos, imageView);

                                getid.id = list.get(pos).getProduct_id();



                            }
                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(SearchResult.this, "connection field", Toast.LENGTH_SHORT).show();
                       dialog1.dismiss();

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


    private void cheakIfDataIsAll(){


        // why using try and catch ?    because when you intent to this activity and back by backbutton to search activity
        //  and int here again crash occurs

        try {
            if (categoryId.equals("0")){
                categoryId = "";
            }

            if (Filter.itemMaxPrice.equals("all")) {
                Filter.itemMaxPrice = "";

            }if(Filter.itemMinPrice.equals("all")){
                Filter.itemMinPrice = "";

            } if(Filter.itemMinPrice.equals("all")){
                Filter.itemMinPrice = "";

            }if(Filter.itemMaxArea.equals("all")){
                Filter.itemMaxArea = "";

            }else if(Filter.itemMinArea.equals("all")){
                Filter.itemMinArea = "";

            }if(Filter.itemMaxBedroom.equals("all")){
                Filter.itemMaxBedroom = "";

            } if(Filter.itemMinBedroom.equals("all")){
                Filter.itemMinBedroom = "";

            } if(Filter.itemMaxBathroom.equals("all")){
                Filter.itemMaxBathroom = "";

            } if(Filter.itemMinBathroom.equals("all")){
                Filter.itemMinBathroom = "";

            }if(Filter.locationOfSpinner.equals("all")) {
                Filter.locationOfSpinner = "";
            }

        }catch (Exception e){}

    }
}
