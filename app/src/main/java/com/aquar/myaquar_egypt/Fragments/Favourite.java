package com.aquar.myaquar_egypt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.aquar.myaquar_egypt.Activity.MainActivity;
import com.aquar.myaquar_egypt.Adapter.FavouritList;
import com.aquar.myaquar_egypt.Adapter.favouriteAdapter;
import com.aquar.myaquar_egypt.Model.Favouirtes.favouriteObjPOJO;
import com.aquar.myaquar_egypt.Model.Favouirtes.favouriteResPOJO;
import com.aquar.myaquar_egypt.Model.ModelOfFavourits;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Services.onFavouriteFinished;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class Favourite extends Fragment {

    @BindView(R.id.fragment_favouriteRV)
    RecyclerView fragment_favouriteRV;
    ListView listView;
    String names[] = {"dasdas", "dasds", "kkkk"};

    int image2[] = {R.drawable.pashe1, R.drawable.pashe2, R.drawable.pashe2};
    FavouritList favouritList;
    ArrayList<favouriteObjPOJO> favouriteObjPOJOS = new ArrayList<>();

    public Favourite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        ButterKnife.bind(getActivity());
        getFavouriteData();

        listView = v.findViewById(R.id.favouriteList);

        ArrayList<ModelOfFavourits> list = new ArrayList<ModelOfFavourits>();

        for (int x = 0; x < names.length; x++) {
            list.add(new ModelOfFavourits(image2[x], names[x]));
        }

        favouritList = new FavouritList(getContext(), R.layout.item_of_favourite_list, list);

        listView.setAdapter(favouritList);

        return v;

    }

    public void setUpFavouriteRecycler(ArrayList<favouriteObjPOJO> AllFavourites) {
//        fragment_favouriteRV
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        fragment_favouriteRV.setLayoutManager(manager);
        favouriteAdapter adapter = new favouriteAdapter(getContext(), AllFavourites);
        fragment_favouriteRV.setAdapter(adapter);
    }

    public void getFavouriteData() {
        try {
            setUpFavouriteRecycler(favouriteObjPOJOS);
            ((MainActivity) getActivity()).APIBindOnFavouriteFinished(new onFavouriteFinished() {
                @Override
                public void getAllFavourite(favouriteResPOJO favouriteResPOJO) {
                    favouriteObjPOJOS = new ArrayList<>();
                    favouriteObjPOJOS = favouriteResPOJO.getFavorites();
                    setUpFavouriteRecycler(favouriteObjPOJOS);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FavouriteERR",e.getMessage());
            Toast.makeText(getContext(), "Please SignIn First", Toast.LENGTH_SHORT).show();
        }
    }
}
