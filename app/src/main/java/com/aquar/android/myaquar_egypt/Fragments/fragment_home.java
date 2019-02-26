package com.aquar.android.myaquar_egypt.Fragments;

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
import com.aquar.android.myaquar_egypt.Activity.MainActivity;
import com.aquar.android.myaquar_egypt.Activity.Projectdetails;
import com.aquar.android.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.android.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.android.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.android.myaquar_egypt.Model.modle_home_fragment;
import com.aquar.android.myaquar_egypt.R;
import com.aquar.android.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.text.ParsePosition;
import java.util.ArrayList;


public class fragment_home extends Fragment {
    int x = 0;
    int y = 0;
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

        mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

            @Override
            public void intent_to_detales(int pos, ImageView imageView) {
                go_detales(pos, imageView);
                Toast.makeText(getContext(), "fsnhoibfwn", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void make_love(int pos, ImageView img) {

            }
        });
        return v;

    }
    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(getActivity(), Projectdetails.class);
        startActivity(intent);
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

//                        Toast.makeText(getActivity(), list.get(0).getProduct_id()+"knk", Toast.LENGTH_SHORT).show();
                        Log.d("data", "onResponse: "+list.get(0).getProject_img());
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


    //////////
/*
initSwipe();


    }*/




/*
    public void make_love_2(int pos,ImageView img) {


    }

    public void make_love_(int pos,ImageView img) {
        x++;
        if (x % 2 != 0) {
            img.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else
            img.setImageResource(R.drawable.ic_favorite_normal_black_24dp);
        if (x > 100) {
            x = 0;
        }
    }
z

    public void share_(int pos, ImageView img, int i) {
        Toast.makeText(getActivity(), "share" + i + "  " + pos, Toast.LENGTH_LONG).show();
    }
*/

/*

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
                int postion=viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {

                    //make love
                    ImageView im= viewHolder.itemView.findViewById(R.id.love_button);



                    if(currnt==R.drawable.ic_favorite_normal_black_24dp){
                        im.setImageResource(R.drawable.ic_favorite_black_24dp);
                    }else{
                        im.setImageResource(R.drawable.ic_favorite_black_24dp);
                    }


                    mAdapter.notifyItemChanged(postion,im);



mAdapter.notifyItemChanged(postion);

                }
                else {
                   //make share
                    Toast.makeText(getActivity(), "make share", Toast.LENGTH_SHORT).show();
                }

            }

            Paint p;

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                    dX=dX/4;
                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height /3;

                    if (dX > 0) {
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                         icon = BitmapFactory.decodeResource(getResources(), R.drawable.share);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                        int postion=viewHolder.getAdapterPosition();


                    }
                    else {
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.liked);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                        ////


                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);



    }
    */
    /*
    public  int return_posation(int i){

        return i;

    }
*/

}