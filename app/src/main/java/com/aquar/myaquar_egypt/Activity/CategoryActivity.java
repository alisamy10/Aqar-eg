package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.Fragments.homeFragment;
import com.aquar.myaquar_egypt.InternalStorage.Session;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class CategoryActivity extends AppCompatActivity {

    homeFragment getid = new homeFragment();
    private RecyclerView mRecyclerView;
    private TextView textOfHeader;
    private ImageView love_behind;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects> list = new ArrayList<>();
//    private AlertDialog dialog1;
    private Dialog dialog1;
    private LinearLayout parentOfCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroy);
        myUtils.setLocale(this);

        mRecyclerView = findViewById(R.id.recyclerView_categry);
        textOfHeader = findViewById(R.id.textOfCategory);

        parentOfCategory = findViewById(R.id.parentOfCategory);


//        dialog1 = new SpotsDialog.Builder().setContext(CategoryActivity.this).setTheme(R.style.Custom).build();
//        dialog1.setMessage("Please wait.....");
//        dialog1.show();

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

        //send id of category from nav to here
        MainActivity data = new MainActivity();
//        GetCategoryData(data.idForCategoryOfNav);
        GetCategoryData(data.idForCategoryOfNav);


        textOfHeader.setText(data.headerOfCategory);


    }


    private void GetCategoryData(String value) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", value);

        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.Category)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parentOfCategory.setVisibility(View.VISIBLE);
                        dialog1.dismiss();
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ModelArray array = gson.fromJson(response.toString(), ModelArray.class);
                        list = array.getProjects();
                        setRecyclerData(list);

                        mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

                            @Override
                            public void intent_to_detales(int pos, ImageView imageView) {
                                Session.getInstance().setTypesOfUnitID(String.valueOf(list.get(pos).getProductId()));
                                go_detales(pos, imageView);

//                                getid.id = list.get(pos).getProductId();

                            }

                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        myUtils.handleError(CategoryActivity.this, anError.getErrorBody(), anError.getErrorCode());

                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {

        mAdapter = new example_adapter_for_home_fragment(CategoryActivity.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(CategoryActivity.this, ProjectdetailsActivity.class);
        startActivity(intent);
    }



}
