package com.aquar.myaquar_egypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.ourServicesAdapter;
import com.aquar.myaquar_egypt.Model.OurServices.ourServicesObj;
import com.aquar.myaquar_egypt.Model.OurServices.ourServicesRes;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class OurServicesActivity extends AppCompatActivity {
    private RecyclerView ourServicesRV;
    private ArrayList<ourServicesObj> ourServicesObjs = new ArrayList<>();
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services);

        ourServicesRV = findViewById(R.id.ourServicesRV);
        dialog = myUtils.LoadingDialog(this);
        dialog.show();
        GetData();

    }

    private void GetData() {
        AndroidNetworking.get(ConstantsUrl.OurServices)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        Log.d("OurServicesActivity", response.toString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ourServicesRes ourServicesRes = gson.fromJson(response.toString(), ourServicesRes.class);
                        ourServicesObjs = ourServicesRes.getOurServices();
                        setData(ourServicesObjs);

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.dismiss();
                        Log.d("OurServicesActivity", anError.toString());

                    }
                });
    }

    private void setData(ArrayList<ourServicesObj> ourServicesObjs) {
        ourServicesAdapter mAdapter = new ourServicesAdapter(OurServicesActivity.this, ourServicesObjs);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ourServicesRV.setLayoutManager(manager);
        ourServicesRV.setAdapter(mAdapter);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(OurServicesActivity.this, MainActivity.class));
        finish();
    }
}