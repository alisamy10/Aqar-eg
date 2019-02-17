package com.aquar.android.myaquar_egypt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquar.android.myaquar_egypt.Adapter.Exampl_second_viwe;
import com.aquar.android.myaquar_egypt.Model.Model_second_view;
import com.aquar.android.myaquar_egypt.Model.modle_home_fragment;
import com.aquar.android.myaquar_egypt.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_second_view_Fragment extends Fragment {
    int x = 2;
    private ArrayList<modle_home_fragment> mExampleList;
    private RecyclerView mRecyclerView;
    private Exampl_second_viwe mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public home_second_view_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home_second_view, container, false);
        mRecyclerView = v.findViewById(R.id.home_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());


        ArrayList<Model_second_view> exampleList = new ArrayList<>();


        mAdapter = new Exampl_second_viwe(getActivity().getApplicationContext(),exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);








        return v;




    }

}
