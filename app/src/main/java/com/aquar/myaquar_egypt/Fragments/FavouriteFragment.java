package com.aquar.myaquar_egypt.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.FavouriteAdapter;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Favouirtes.favouriteObjPOJO;
import com.aquar.myaquar_egypt.Model.Favouirtes.favouriteResPOJO;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class FavouriteFragment extends Fragment {

    RecyclerView fragment_favouriteRV;
    TextView fragment_favouriteEmpty_TV;
    ArrayList<favouriteObjPOJO> favouriteObjPOJOS = new ArrayList<>();
    //    private AlertDialog dialog;
    private Dialog dialog;


    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        myUtils.setLocale(getActivity());

        fragment_favouriteEmpty_TV = v.findViewById(R.id.fragment_favouriteEmpty_TV);
        fragment_favouriteRV = v.findViewById(R.id.fragment_favouriteRV);
//        dialog = new SpotsDialog.Builder().setContext(getContext()).setTheme(R.style.Custom).build();
//        dialog.setMessage("Please wait.....");
        dialog = myUtils.LoadingDialog(getActivity());
        dialog.setCancelable(false);
        getData();


        return v;

    }

    public void setUpFavouriteRecycler(ArrayList<favouriteObjPOJO> AllFavourites) {

        fragment_favouriteRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        FavouriteAdapter adapter = new FavouriteAdapter(getContext(), AllFavourites);
        fragment_favouriteRV.setAdapter(adapter);
    }


    public void getData() {
        try {
            dialog.show();
            Gson gson = new Gson();
            UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);

            Log.d("Response", "user: " + String.valueOf(userPOJO.getUserId() + ""));
            AndroidNetworking.get(ConstantsUrl.allFavorites)
                    .addQueryParameter("user_id", String.valueOf(userPOJO.getUserId()))
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            favouriteResPOJO resPOJO = gson.fromJson(response.toString(), favouriteResPOJO.class);
                            favouriteObjPOJOS = new ArrayList<>();
                            favouriteObjPOJOS = resPOJO.getFavorites();
                            setUpFavouriteRecycler(favouriteObjPOJOS);
                            Log.d("ResponseDataFav", favouriteObjPOJOS.get(0).getDescription());
                            dialog.dismiss();
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("getError", anError.getErrorCode() + "");
                            Log.d("getError", "res: " + anError.getResponse() + "");
                            myUtils.handleError(getActivity(), anError.getErrorBody(), anError.getErrorCode());
                            dialog.dismiss();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            dialog.dismiss();
            fragment_favouriteEmpty_TV.setVisibility(View.VISIBLE);
            Log.d("getError", "err: " + e.getMessage() + "");

        }
    }
}
