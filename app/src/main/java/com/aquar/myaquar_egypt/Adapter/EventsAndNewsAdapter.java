package com.aquar.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquar.myaquar_egypt.Model.ModelOfEventsAndNews;
import com.aquar.myaquar_egypt.R;

import java.util.ArrayList;

public class EventsAndNewsAdapter extends ArrayAdapter {

    private ArrayList<ModelOfEventsAndNews> mlist;


    public EventsAndNewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        mlist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.item_of_events, parent, false);

        TextView textView = convertView.findViewById(R.id.text_of_events);
        ImageView imageView = convertView.findViewById(R.id.image_of_events);


        textView.setText(mlist.get(position).evntsText);
        imageView.setImageResource(mlist.get(position).eventsImage);


          return convertView ;
    }
}
