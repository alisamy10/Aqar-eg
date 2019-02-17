package com.aquar.android.myaquar_egypt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.aquar.android.myaquar_egypt.Adapter.FavouriteList;
import com.aquar.android.myaquar_egypt.Model.FavouriteModelList;
import com.aquar.android.myaquar_egypt.R ;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class Favourite extends Fragment {


    ListView listView ;
    String names []  = {"dasdas","dasds" ,"kkkk"};
    String locations [] = {"dd" , "dsd","lk"};
    int image2 []= {R.drawable.pashe1 , R.drawable.pashe2 , R.drawable.pashe2};
    FavouriteList favouriteList ;

    public Favourite() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

              View v =  inflater.inflate(R.layout.fragment_favourite, container, false);

              listView = v.findViewById(R.id.favouriteList);

        ArrayList<FavouriteModelList> list = new ArrayList <FavouriteModelList> ();

        for (int x = 0 ; x < names.length ; x++ ){
            list.add(new FavouriteModelList(  image2 [x] , names [x] ,locations[x] ));
        }

        favouriteList = new FavouriteList (getContext(),R.layout.item_of_favourite_list , list);

        listView.setAdapter(favouriteList);

        return v ;

    }

}
