package com.aquar.myaquar_egypt.Activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aquar.myaquar_egypt.Adapter.customerImageViewPagerAdapter;
import com.aquar.myaquar_egypt.InternalStorage.Session;
import com.aquar.myaquar_egypt.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.ortiz.touchview.TouchImageView;

import java.util.List;


public class struct_activity extends AppCompatActivity {

    TouchImageView touchImageView;
    ViewPager viewPager;
    customerImageViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struct);
        List<String> protoTypeImages = Session.getInstance().getUrlimage();

        viewPager = findViewById(R.id.viewPagerStructure);
        adapter = new customerImageViewPagerAdapter(this, protoTypeImages);
        viewPager.setAdapter(adapter);

        for (int x = 0; x < protoTypeImages.size(); x++) {
            Log.d("Sliders", protoTypeImages.get(x));
        }
//        touchImageView.setMaxZoom(3);
//        touchImageView.setMinZoom(1);

    }


}