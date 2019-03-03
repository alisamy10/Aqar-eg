package com.aquar.myaquar_egypt.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
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

public class EventsAndNews extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageView love_behind;
    private EventsAndNewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelOfEventAndNews>  list  = new ArrayList<>();
    public static int id  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news);


       mRecyclerView = findViewById(R.id.listOfEventsAndNews);


        GetHome_Data();


    }




    private void GetHome_Data() {


        AndroidNetworking.get(ConstantsUrl.Event)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ModelArrayOfEventAndNews array = gson.fromJson(response.toString(), ModelArrayOfEventAndNews.class);
                        list = array.getProjects();
                        setRecyclerData(list);

                        Toast.makeText(EventsAndNews.this, list.get(0).getDescription()+"", Toast.LENGTH_SHORT).show();


                        mAdapter.setOnItemClickListener(new EventsAndNewsAdapter.OnItemClickListener() {
                            @Override
                            public void intent_to_detales(int pos, RelativeLayout relativeLayout) {
                                go_detales( pos, relativeLayout);
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
        Toast.makeText(this, "go to event and news"+pos, Toast.LENGTH_SHORT).show();
         startActivity(new Intent(this , EventsAndNewsDetails.class));


    }



}
