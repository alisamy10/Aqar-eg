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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.EventsAndNewsAdapter;
import com.aquar.myaquar_egypt.Model.ModelOfNewsAndEvent.ModelArrayOfEventAndNews;
import com.aquar.myaquar_egypt.Model.ModelOfNewsAndEvent.ModelOfEventAndNews;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class EventsAndNews extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageView love_behind;
    private EventsAndNewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelOfEventAndNews>  list  = new ArrayList<>();
 //   public static int id;
    public static int id_event;
    private AlertDialog dialog1;
    private LinearLayout parentOfEventAndNews ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news);

       parentOfEventAndNews = findViewById(R.id.parentOfEventAndNews);
       mRecyclerView = findViewById(R.id.listOfEventsAndNews);

        dialog1 = new SpotsDialog.Builder().setContext(EventsAndNews.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();

        GetHome_Data();


    }




    private void GetHome_Data() {


        AndroidNetworking.get(ConstantsUrl.Event)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parentOfEventAndNews.setVisibility(View.VISIBLE);
                        dialog1.dismiss();

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ModelArrayOfEventAndNews array = gson.fromJson(response.toString(), ModelArrayOfEventAndNews.class);
                        list = array.getProjects();
                        setRecyclerData(list);

                        Toast.makeText(EventsAndNews.this, list.get(0).getDescription()+"", Toast.LENGTH_SHORT).show();


                        mAdapter.setOnItemClickListener(new EventsAndNewsAdapter.OnItemClickListener() {
                            @Override
                            public void intent_to_detales(int pos, RelativeLayout relativeLayout) {
                                go_detales( pos, relativeLayout);
                              id_event= list.get(pos).getProduct_id();
                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(EventsAndNews.this, "connection field", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelOfEventAndNews> list) {


        mAdapter = new EventsAndNewsAdapter (EventsAndNews.this, list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }




    public void go_detales(int pos, RelativeLayout relativeLayout) {
      //  Toast.makeText(this, "go to event and news"+pos, Toast.LENGTH_SHORT).show();
         startActivity(new Intent(this , EventsAndNewsDetails.class));
         finish();


    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(EventsAndNews.this, MainActivity.class));
        finish();

    }




}
