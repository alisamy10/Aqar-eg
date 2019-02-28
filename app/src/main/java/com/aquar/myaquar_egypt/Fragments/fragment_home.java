package com.aquar.myaquar_egypt.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Activity.Login;
import com.aquar.myaquar_egypt.Activity.MainActivity;
import com.aquar.myaquar_egypt.Activity.Projectdetails;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.Model.Login.userResPOJO;
import com.aquar.myaquar_egypt.Model.modle_home_fragment;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParsePosition;
import java.util.ArrayList;


public class fragment_home extends Fragment {
    int x = 0;
    int y = 0;


      public static int id  ;


    private int currnt = R.drawable.ic_favorite_normal_black_24dp;
    private ArrayList<modle_home_fragment> mExampleList;
    private RecyclerView mRecyclerView;
    private ImageView love_behind;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final float buttonWidth = 300;



    ArrayList<ModelObjects>  list  = new ArrayList<>();

    /* private ButtonsState buttonShowedState = ButtonsState.GONE;*/
    public fragment_home() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerView_fragment_home);

        GetHome_Data();

/////////


        //////////////////


        return v;

    }




    private void GetHome_Data() {


        AndroidNetworking.get(ConstantsUrl.Home)
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

                                id = list.get(pos).getProduct_id();

                                Toast.makeText(getContext(), id+"", Toast.LENGTH_SHORT).show();

                            }
                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(getContext(), "connection field", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {


        mAdapter = new example_adapter_for_home_fragment(getActivity(), list);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }



    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(getActivity(), Projectdetails.class);
        startActivity(intent);
    }








}