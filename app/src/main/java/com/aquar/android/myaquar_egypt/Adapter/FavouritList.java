package com.aquar.android.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquar.android.myaquar_egypt.Model.ModelOfFavourits;
import com.aquar.android.myaquar_egypt.Model.ProjectModelList;
import com.aquar.android.myaquar_egypt.R;

import java.util.ArrayList;

public class FavouritList extends ArrayAdapter {

    ArrayList<ModelOfFavourits> mlist;

    int currnt = R.drawable.like ;

    public FavouritList(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        mlist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.item_of_favourite_list, parent, false);


        ImageView imageView = convertView.findViewById(R.id.image_of_favourite_list);
        TextView textView = convertView.findViewById(R.id.text_of_favourite);
        final Button like_btn = convertView.findViewById(R.id.like_btn_of_favourite_list);

        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currnt==R.drawable.like){
                    like_btn.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    currnt = R.drawable.ic_favorite_black_24dp;
                }else {
                    like_btn.setBackgroundResource(R.drawable.like);
                    currnt = R.drawable.like;
                }

            }
        });

        imageView.setImageResource(mlist.get(position).imageOfFavourite);
        textView.setText(mlist.get(position).nameCompoundOfFavourite);



        return convertView;
    }
}
