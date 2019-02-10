package com.aquar.android.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.android.myaquar_egypt.Model.modle_home_fragment;
import com.aquar.android.myaquar_egypt.R;

import java.util.ArrayList;

public class example_adapter_for_home_fragment extends RecyclerView.Adapter<example_adapter_for_home_fragment.ExampleViewHolder> {
    /////
    private ArrayList<modle_home_fragment> mExampleList;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onItemClick(int position, ImageView imageView, int i,boolean k);

        void share(int position, ImageView imageView, int i);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    /////
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_1_2_type, textView_1_2_price, textView_1_2_number,
                textView_2_3_type, textView_2_3_price, textView_2_3_number;

        private ImageView imageView_1_2_share, imageView_1_2_love,
                imageView_2_3_share, imageView_2_3_love,
                imageView_one_one, imageView_two_four;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            /////
            textView_1_2_type = itemView.findViewById(R.id.type_of_project_one_two);
            textView_1_2_price = itemView.findViewById(R.id.price_home_one_two);
            textView_1_2_number = itemView.findViewById(R.id.number_Of_year_one_two);
//////
/////
            textView_2_3_type = itemView.findViewById(R.id.type_of_project_two_three);
            textView_2_3_price = itemView.findViewById(R.id.price_home_two_three);
            textView_2_3_number = itemView.findViewById(R.id.number_Of_year_two_three);
//////

            imageView_1_2_share = itemView.findViewById(R.id.share_id_home_one_two);
            imageView_1_2_love = itemView.findViewById(R.id.love_id_home_one_two);
            //////
            imageView_2_3_share = itemView.findViewById(R.id.share_id_home_two_three);
            imageView_2_3_love = itemView.findViewById(R.id.love_id_home_two_three);

            //////
            imageView_one_one = itemView.findViewById(R.id.image_one_one);
            imageView_two_four = itemView.findViewById(R.id.image_two_four);
            /////
            imageView_1_2_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.share(position, imageView_1_2_share, 2);

                        }
                    }

                }
            });


            imageView_1_2_love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

boolean i=false;
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, imageView_1_2_love, 1,true);

                        }
                    }

                }
            });

            imageView_2_3_love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean i=false;
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, imageView_2_3_love, 1,true);

                        }
                    }

                }
            });

            imageView_2_3_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.share(position, imageView_2_3_share, 3);

                        }
                    }

                }
            });


        }
    }

    public example_adapter_for_home_fragment(Context applicationContext, ArrayList<modle_home_fragment> exampleList) {
        mExampleList = exampleList;
        context=applicationContext;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_itme,viewGroup,false);

        return new ExampleViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder,final int i) {
        modle_home_fragment currentitem = mExampleList.get(i);
////
        exampleViewHolder.imageView_one_one.setImageResource(currentitem.getImageView_one_one());
        exampleViewHolder.imageView_two_four.setImageResource(currentitem.getImageView_two_four());
////

        exampleViewHolder.textView_1_2_type.setText((CharSequence) currentitem.getTextView_1_2_type());
        exampleViewHolder.textView_1_2_price.setText((CharSequence) currentitem.getTextView_1_2_price());
        exampleViewHolder.textView_1_2_number.setText((CharSequence) currentitem.getTextView_1_2_number());
        ///

        exampleViewHolder.textView_2_3_type.setText((CharSequence) currentitem.getTextView_2_3_type());
        exampleViewHolder.textView_2_3_price.setText((CharSequence) currentitem.getTextView_2_3_price());
        exampleViewHolder.textView_2_3_number.setText((CharSequence) currentitem.getTextView_2_3_number());
        ////

    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }


}
