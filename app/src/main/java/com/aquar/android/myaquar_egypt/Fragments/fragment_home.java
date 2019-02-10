package com.aquar.android.myaquar_egypt.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.aquar.android.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.android.myaquar_egypt.Model.modle_home_fragment;
import com.aquar.android.myaquar_egypt.R;

import java.util.ArrayList;


public class fragment_home extends Fragment {

    private ArrayList<modle_home_fragment> mExampleList;
    private RecyclerView mRecyclerView;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_fragment_home, container, false);

        mRecyclerView = v.findViewById(R.id.recyclerView_fragment_home);
        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(getActivity());


        ArrayList<modle_home_fragment> exampleList = new ArrayList<>();

        ///

        exampleList.add(new  modle_home_fragment("LIFE PARK SHROUK","120,000,00", "2 years", "LIFE PARK SHROUK","120,000,00", "2 year",  R.drawable.ic_share_black_24dp, R.drawable.ic_favorite_normal_black_24dp, R.drawable.ic_share_black_24dp,R.drawable.ic_favorite_normal_black_24dp,  R.drawable.photo, R.drawable.phototwo )) ;


        exampleList.add(new  modle_home_fragment("LIFE PARK SHROUK","120,000,00", "2 years", "LIFE PARK SHROUK","120,000,00", "2 year",  R.drawable.ic_share_black_24dp, R.drawable.ic_favorite_normal_black_24dp, R.drawable.ic_share_black_24dp,R.drawable.ic_favorite_normal_black_24dp,  R.drawable.phototwo, R.drawable.photo )) ;


        exampleList.add(new  modle_home_fragment("LIFE PARK SHROUK","120,000,00", "2 years", "LIFE PARK SHROUK","120,000,00", "2 year",  R.drawable.ic_share_black_24dp, R.drawable.ic_favorite_normal_black_24dp, R.drawable.ic_share_black_24dp,R.drawable.ic_favorite_normal_black_24dp,  R.drawable.photo, R.drawable.phototwo )) ;

        mAdapter = new example_adapter_for_home_fragment(getActivity().getApplicationContext(),exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ImageView imageView, int i,boolean l) {

                make_love(position,imageView,i,l);



            }

            @Override
            public void share(int position, ImageView imageView, int i) {
                share_(position,imageView,i);
            }
        });



        return v;
    }

    public void make_love(int pos,ImageView img,int i,boolean l) {

        img.setImageResource(R.drawable.ic_favorite_black_24dp);

    }
    public void share_(int pos,ImageView img,int i) {

        Toast.makeText( getActivity(), "share"+i+"  "+pos, Toast.LENGTH_LONG).show();
    }

}
