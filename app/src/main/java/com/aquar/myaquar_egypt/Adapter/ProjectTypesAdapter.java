package com.aquar.myaquar_egypt.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aquar.myaquar_egypt.Model.ModelOfListOfPaymentMethod;
import com.aquar.myaquar_egypt.Model.UnitsModel.unitsModelTypes;
import com.aquar.myaquar_egypt.R;

import java.util.ArrayList;

/**
 * Created by aswany on 4/1/19.
 */

public class ProjectTypesAdapter extends ArrayAdapter {


    ArrayList<unitsModelTypes> projectTypesAdapters;
    Context mContext;

    public ProjectTypesAdapter(Context context, int resource, ArrayList projectTypesAdapters) {
        super(context, resource, projectTypesAdapters);

        this.projectTypesAdapters = projectTypesAdapters;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.item_list_of_payment_method, parent, false);

        TextView text1 = convertView.findViewById(R.id.Percentage);
        TextView text2 = convertView.findViewById(R.id.years);

//
//        text1.setText(projectTypesAdapters.get(position).);
//        text2.setText(projectTypesAdapters.get(position).);


        return convertView;
    }
}