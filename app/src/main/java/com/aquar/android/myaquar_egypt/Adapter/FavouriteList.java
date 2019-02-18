package com.aquar.android.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aquar.android.myaquar_egypt.Model.FavouriteModelList;
import java.util.ArrayList;
import com.aquar.android.myaquar_egypt.R;

public class FavouriteList extends ArrayAdapter {
    int  o = 1  ;
    ArrayList<FavouriteModelList> mlist;

    public FavouriteList(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        mlist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.item_of_favourite_list, parent, false);

        TextView textNameProject = convertView.findViewById(R.id.name_project_of_favourit_list);
        ImageView imageProject = convertView.findViewById(R.id.image_of_favourite_list);
        TextView location = convertView.findViewById(R.id.location_project_of_favourite_list);

        final RelativeLayout footer = convertView.findViewById(R.id.footer);


        textNameProject.setText(mlist.get(position).nameProject);
        imageProject.setImageResource(mlist.get(position).imageProject);
        location.setText(mlist.get(position).locationProject);


        imageProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                o++;

                if (o%2== 0 ) {


                    footer.setVisibility(View.VISIBLE);


                }
                else

                {
                    footer.setVisibility(View.GONE);

                }
            }
        });




        return convertView;
    }
}
