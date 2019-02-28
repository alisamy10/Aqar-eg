package com.aquar.android.myaquar_egypt.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.android.myaquar_egypt.Adapter.EventsAndNewsAdapter;
import com.aquar.android.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.android.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.android.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.android.myaquar_egypt.Model.ModelsOfEventAndNews.ModelArrayOfEventAndNews;
import com.aquar.android.myaquar_egypt.Model.ModelsOfEventAndNews.ModelOfEventAndNews;
import com.aquar.android.myaquar_egypt.Model.modle_home_fragment;
import com.aquar.android.myaquar_egypt.R;
import com.aquar.android.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

public class EventsAndNews extends AppCompatActivity {


    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ModelOfEventAndNews>  list  = new ArrayList<>();

    EventsAndNewsAdapter eventsAndNewsAdapter ;
    RecyclerView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news);

        listView = findViewById(R.id.listOfEventsAndNews);



        GetEventAndNews_Data();



    }

    private void GetEventAndNews_Data() {


        AndroidNetworking.get(ConstantsUrl.Eventandnews)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ModelArrayOfEventAndNews array = gson.fromJson(response.toString(), ModelArrayOfEventAndNews.class);
                        list = array.getProjects();
                        setRecyclerData(list);


                        Toast.makeText(EventsAndNews.this, list.get(0).getProject_name()+"", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(EventsAndNews.this, "connection field", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setRecyclerData(ArrayList<ModelOfEventAndNews> list) {


        eventsAndNewsAdapter = new EventsAndNewsAdapter(this, list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        listView.setAdapter(eventsAndNewsAdapter);
    }


}
