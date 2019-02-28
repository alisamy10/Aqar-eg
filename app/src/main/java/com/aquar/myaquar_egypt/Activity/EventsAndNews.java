package com.aquar.myaquar_egypt.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.aquar.myaquar_egypt.Model.ModelOfEventsAndNews;
import com.aquar.myaquar_egypt.Adapter.EventsAndNewsAdapter;
import com.aquar.myaquar_egypt.R;

import java.util.ArrayList;

public class EventsAndNews extends AppCompatActivity {

    String names []  = {"dasdas","dasds" ,"kkkk"};

    int image2 []= {R.drawable.pashe1 ,R.drawable.pashe2 , R.drawable.pashe2};

    EventsAndNewsAdapter eventsAndNewsAdapter ;
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news);

        listView = findViewById(R.id.listOfEventsAndNews);


        ArrayList<ModelOfEventsAndNews> list = new ArrayList <ModelOfEventsAndNews> ();

        for (int x = 0 ; x < names.length ; x++ ){
            list.add(new ModelOfEventsAndNews(  image2 [x] , names [x]  ));
        }

        eventsAndNewsAdapter = new EventsAndNewsAdapter(this , R.layout.item_of_events, list);

        listView.setAdapter(eventsAndNewsAdapter);





    }
}
