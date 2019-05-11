package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.Fragments.homeFragment;
import com.aquar.myaquar_egypt.InternalStorage.Session;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class SearchResultActivity extends AppCompatActivity {

    private homeFragment getid = new homeFragment();
    private RecyclerView mRecyclerView;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects> list = new ArrayList<>();
    private String categoryId;
    //    private AlertDialog dialog1;
    private Dialog dialog1;
    private LinearLayout parentOfSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        myUtils.setLocale(this);


        parentOfSearchResult = findViewById(R.id.parentOfSearchResult);

//        dialog1 = new SpotsDialog.Builder().setContext(SearchResultActivity.this).setTheme(R.style.Custom).build();
//        dialog1.setMessage("Please wait.....");
//        dialog1.show();

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

        mRecyclerView = findViewById(R.id.recyclerView_search_result);

        //for cast category id from int to string and check if it = 0 give it ""
        categoryId = String.valueOf(FilterActivity.radioBtn);


        cheakIfDataIsAll();


        GetCategoryData(categoryId, FilterActivity.itemMaxPrice, FilterActivity.itemMinPrice, FilterActivity.itemMaxArea,
                FilterActivity.itemMinArea, FilterActivity.itemMaxBedroom, FilterActivity.itemMinBedroom, FilterActivity.itemMaxBathroom,
                FilterActivity.itemMinBathroom, FilterActivity.locationOfSpinner, FilterActivity.itemType, FilterActivity.itemSearchKey
        );


    }

    private void GetCategoryData(String category, String maxPrice, String minPrice, String maxArea, String minArea, String maxBedrooms,
                                 String minBedrooms, String maxBathrooms, String minBathrooms, String locations,
                                 String itemType, String itemSearchKey) {
        JSONObject object = new JSONObject();
        try {
            object.put("category_id", category);
            object.put("type", itemType);
            object.put("keywords", itemSearchKey);
            object.put("max_price", maxPrice);
            object.put("min_price", minPrice);
            object.put("max_area", maxArea);
            object.put("min_area", minArea);
            object.put("max_badrooms", maxBedrooms);
            object.put("min_badrooms", minBedrooms);
            object.put("max_bathrooms", maxBathrooms);
            object.put("min_bathrooms", minBathrooms);
            object.put("locations", locations);
            Log.d("SearchDataObj", object.toString());


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
                        Log.d("SearchData", response.toString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ModelArray array = gson.fromJson(response.toString(), ModelArray.class);
                        list = array.getProjects();
                        setRecyclerData(list);


                        mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

                            @Override
                            public void intent_to_detales(int pos, ImageView imageView) {


                                Session.getInstance().setProjectID(list.get(pos).getProductId());
                                go_detales(pos, imageView);

//                                getid.id = list.get(pos).getProductId();


                            }

                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(SearchResultActivity.this, "No result", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                        myUtils.handleError(SearchResultActivity.this, anError.getErrorBody(), anError.getErrorCode());
                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {

        mAdapter = new example_adapter_for_home_fragment(SearchResultActivity.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(SearchResultActivity.this, ProjectTypesActivity.class);
        startActivity(intent);

    }


    private void cheakIfDataIsAll() {


        // why using try and catch ?    because when you intent to this activity and back by backbutton to search activity
        //  and int here again crash occurs

        try {
            if (categoryId.equals("0")) {
                categoryId = "";
            }

            if (FilterActivity.itemMaxPrice.equals("all")) {
                FilterActivity.itemMaxPrice = "";

            }
            if (FilterActivity.itemMinPrice.equals("all")) {
                FilterActivity.itemMinPrice = "";

            }
            if (FilterActivity.itemMinPrice.equals("all")) {
                FilterActivity.itemMinPrice = "";

            }
            if (FilterActivity.itemMaxArea.equals("all")) {
                FilterActivity.itemMaxArea = "";

            } else if (FilterActivity.itemMinArea.equals("all")) {
                FilterActivity.itemMinArea = "";

            }
            if (FilterActivity.itemMaxBedroom.equals("all")) {
                FilterActivity.itemMaxBedroom = "";

            }
            if (FilterActivity.itemMinBedroom.equals("all")) {
                FilterActivity.itemMinBedroom = "";

            }
            if (FilterActivity.itemMaxBathroom.equals("all")) {
                FilterActivity.itemMaxBathroom = "";

            }
            if (FilterActivity.itemMinBathroom.equals("all")) {
                FilterActivity.itemMinBathroom = "";

            }
            if (FilterActivity.locationOfSpinner.equals("all")) {
                FilterActivity.locationOfSpinner = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        FilterActivity.radioBtn = 0;
        startActivity(new Intent(SearchResultActivity.this, FilterActivity.class));
        finish();

    }

}
