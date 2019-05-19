package com.aquar.myaquar_egypt.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Activity.ProjectTypesActivity;
import com.aquar.myaquar_egypt.Adapter.AdapterForHomeFragment;
import com.aquar.myaquar_egypt.InternalStorage.Session;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {

    public static int id;
//    private AlertDialog dialog1;

    private Dialog dialog1;


    private RecyclerView mRecyclerView;
    private AdapterForHomeFragment mAdapter;


    private ArrayList<ModelObjects> list = new ArrayList<>();

    /* private ButtonsState buttonShowedState = ButtonsState.GONE;*/
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerView_fragment_home);


        myUtils.setLocale(getActivity());

         dialog1 = myUtils.LoadingDialog(getActivity());

        getData();


        return v;

    }

    private void getData() {
        if (!Objects.equals(Session.getInstance().getHomeArray(), null)) {
            dialog1.dismiss();
            list = Session.getInstance().getHomeArray();


            setRecyclerData(list);
            mAdapter.setOnItemClickListener(new AdapterForHomeFragment.OnItemClickListener() {

                @Override
                public void intent_to_detales(int pos, ImageView imageView) {
                    id = list.get(pos).getProductId();
                    Session.getInstance().setProjectID(list.get(pos).getProductId());
//                    Session.getInstance().setProjectID(String.valueOf(id));
                    go_detales(pos, imageView);

                    Log.d("Id5araC", String.valueOf(list.get(pos).getProductId()));
                }

                @Override
                public void make_love(int pos, ImageView img) {

                }
            });
        } else {
            GetHome_Data();
        }
    }


    private void GetHome_Data() {
        dialog1.show();

        AndroidNetworking.get(ConstantsUrl.NewHome)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog1.dismiss();
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ModelArray array = gson.fromJson(response.toString(), ModelArray.class);
                        list = array.getProjects();
                        Session.getInstance().setHomeArray(list);
                        setRecyclerData(list);



                        mAdapter.setOnItemClickListener(new AdapterForHomeFragment.OnItemClickListener() {

                            @Override
                            public void intent_to_detales(int pos, ImageView imageView) {
                                id = list.get(pos).getProductId();
                                Session.getInstance().setProjectID(list.get(pos).getProductId());
                                go_detales(pos, imageView);



                            }

                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        myUtils.handleError(getActivity(), anError.getErrorBody(), anError.getErrorCode());
                        Toast.makeText(getContext(), "connection failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {


        mAdapter = new AdapterForHomeFragment(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(getActivity(), ProjectTypesActivity.class);
        startActivity(intent);
    }


}