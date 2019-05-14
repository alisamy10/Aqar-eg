package com.aquar.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquar.myaquar_egypt.Model.OurServices.ourServicesObj;
import com.aquar.myaquar_egypt.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ourServicesAdapter extends RecyclerView.Adapter<ourServicesAdapter.MyView> {

    private Context myContext;
    private ArrayList<ourServicesObj> ourServicesObjs = new ArrayList<>();

    public ourServicesAdapter(Context myContext, ArrayList<ourServicesObj> ourServicesObjs) {
        this.myContext = myContext;
        this.ourServicesObjs = ourServicesObjs;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_our_service, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyView myView, int i) {
        ourServicesObj ourServicesObj = ourServicesObjs.get(i);

        Glide.with(myContext).load(ourServicesObj.getServiceImage()).into(myView.ServiceImageIV);
        myView.ServiceNameTV.setText(ourServicesObj.getServiceName());
    }

    @Override
    public int getItemCount() {
        return ourServicesObjs.size();
    }

    class MyView extends RecyclerView.ViewHolder {
        private TextView ServiceNameTV;
        private ImageView ServiceImageIV;

        MyView(@NonNull View itemView) {
            super(itemView);
            ServiceNameTV = itemView.findViewById(R.id.ServiceNameTV);
            ServiceImageIV = itemView.findViewById(R.id.ServiceImageIV);
        }
    }
}
