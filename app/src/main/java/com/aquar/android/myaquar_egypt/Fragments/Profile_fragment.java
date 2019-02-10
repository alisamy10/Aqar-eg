package com.aquar.android.myaquar_egypt.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aquar.android.myaquar_egypt.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_fragment extends Fragment {

    private Fragment fragment;

    //Layout
    @BindView(R.id.profile_FrameLayout)
    FrameLayout profile_FrameLayout;

    //Button
    @BindView(R.id.messages)
    Button messages;

    @BindView(R.id.personal_data)
    Button personal_data;
    //textview
    @BindView(R.id.message_button_click)
    TextView messagecolor;
    @BindView(R.id.data_button_click)
    TextView datacolor;

    public Profile_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Fragment f = new messages_fragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.profile_FrameLayout, f, "fra");
        ft.commit();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        return view;

    }

    @OnClick(R.id.personal_data)
    public void personaldataOnClick() {
        Fragment f = new personal_fragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.profile_FrameLayout, f, "personal fra");
        ft.commit();

        messagecolor.setBackgroundResource(R.color.White);
        datacolor.setBackgroundResource(R.color.Red);


    }

    @OnClick(R.id.messages)
    public void MessageOnClick() {
        Fragment f = new messages_fragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.profile_FrameLayout, f, "messages fra");
        ft.commit();

        messagecolor.setBackgroundResource(R.color.Red);
        datacolor.setBackgroundResource(R.color.White);

    }



}
