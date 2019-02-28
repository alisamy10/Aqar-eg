package com.aquar.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquar.myaquar_egypt.Model.Model_second_view;
import com.aquar.myaquar_egypt.R;

import java.util.ArrayList;

public class Exampl_second_viwe extends RecyclerView.Adapter<Exampl_second_viwe.ExampleViewHolder> {
    /////
    private ArrayList<Model_second_view> mExampleList;
    private Context context;

    private Exampl_second_viwe.OnItemClickListener mListener;

    public interface OnItemClickListener {



    }

    public void setOnItemClickListener(Exampl_second_viwe.OnItemClickListener listener) {
        mListener = listener;
    }


    /////
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_type, textView_price, textView_number;

        private ImageView
                imageView_one_one,love1,share;




        public ExampleViewHolder(@NonNull View itemView, final Exampl_second_viwe.OnItemClickListener listener) {
            super(itemView);

            textView_type=itemView.findViewById(R.id.name_second_view);
            textView_price=itemView.findViewById(R.id.price_second_view);
            textView_number=itemView.findViewById(R.id.installments_second_view);
            imageView_one_one=itemView.findViewById(R.id.image_second_view);
            love1=itemView.findViewById(R.id.like_second_view);
            share=itemView.findViewById(R.id.share_second);


            /////
        }
    }

    public Exampl_second_viwe(Context applicationContext, ArrayList<Model_second_view> exampleList) {
        mExampleList = exampleList;
        context=applicationContext;
    }


    @NonNull
    @Override
    public Exampl_second_viwe.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view,viewGroup,false);

        return new Exampl_second_viwe.ExampleViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Exampl_second_viwe.ExampleViewHolder exampleViewHolder, final int i) {
        Model_second_view currentitem = mExampleList.get(i);
////
        exampleViewHolder.love1.setImageResource(currentitem.getLove());
        exampleViewHolder.share.setImageResource(currentitem.getShare());
        exampleViewHolder.imageView_one_one.setImageResource(currentitem.getImage());
        exampleViewHolder.textView_type.setText((CharSequence) currentitem.getName_secound());
        exampleViewHolder.textView_number.setText((CharSequence) currentitem.getNumber_second());
        exampleViewHolder.textView_price.setText((CharSequence) currentitem.getPrice_second());


    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }


}
