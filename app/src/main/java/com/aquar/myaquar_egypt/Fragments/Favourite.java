package com.aquar.myaquar_egypt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.aquar.myaquar_egypt.Adapter.FavouritList;
import com.aquar.myaquar_egypt.Model.ModelOfFavourits;
import com.aquar.myaquar_egypt.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class Favourite extends Fragment {


    ListView listView ;
    String names []  = {"dasdas","dasds" ,"kkkk"};

    int image2 []= {R.drawable.pashe1 , R.drawable.pashe2 , R.drawable.pashe2};
    FavouritList favouritList;

    public Favourite() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

              View v =  inflater.inflate(R.layout.fragment_favourite, container, false);

              listView = v.findViewById(R.id.favouriteList);

        ArrayList<ModelOfFavourits> list = new ArrayList <ModelOfFavourits> ();

        for (int x = 0 ; x < names.length ; x++ ){
            list.add(new ModelOfFavourits(  image2 [x] , names [x]  ));
        }

        favouritList = new FavouritList(getContext(), R.layout.item_of_favourite_list, list);

        listView.setAdapter(favouritList);

        return v ;

    }

}
