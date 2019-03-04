package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import android.widget.TextView;

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

public class Category extends AppCompatActivity {

    fragment_home  getid = new fragment_home();
    private RecyclerView mRecyclerView;
    private TextView textOfHeader ;
    private ImageView love_behind;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects>  list  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroy);
        mRecyclerView = findViewById(R.id.recyclerView_categry);
        textOfHeader = findViewById(R.id.textOfCategory);





        //send id of category from nav to here
        MainActivity data = new MainActivity();
        GetCategoryData(data.idForCategoryOfNav);


        textOfHeader.setText(data.headerOfCategory);


    }



    private void GetCategoryData(String value) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", value);

        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.Category)
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


                    }
                });
    }
    private void setRecyclerData(ArrayList<ModelObjects> list) {

    mAdapter = new example_adapter_for_home_fragment(Category.this, list);
    LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(Category.this, Projectdetails.class);
        startActivity(intent);
    }
}
