package com.aquar.android.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquar.android.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.android.myaquar_egypt.Model.ModelsOfEventAndNews.ModelOfEventAndNews;
import com.aquar.android.myaquar_egypt.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EventsAndNewsAdapter extends RecyclerView.Adapter<EventsAndNewsAdapter.ExampleViewHolder> {
    /////
    private ArrayList<ModelOfEventAndNews> mExampleList;
    private Context context;








    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        ImageView imageView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            /////
             textView = itemView.findViewById(R.id.text_of_events);
             imageView = itemView.findViewById(R.id.image_of_events);


        }
    }

    public EventsAndNewsAdapter(Context applicationContext, ArrayList<ModelOfEventAndNews> exampleList) {
        mExampleList = exampleList;
        context=applicationContext;
    }


    @NonNull
    @Override
    public EventsAndNewsAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_of_events,viewGroup,false);

        return new EventsAndNewsAdapter.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, final int i) {

        ModelOfEventAndNews currentitem = mExampleList.get(i);
////
//
        Glide.with(context).load(currentitem.getProject_img()).into(exampleViewHolder.imageView);
///
        exampleViewHolder.textView.setText( currentitem.getProject_name());





        ///


    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }


}


/*
    TextView textView = convertView.findViewById(R.id.text_of_events);
    ImageView imageView = convertView.findViewById(R.id.image_of_events);
    */