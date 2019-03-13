package com.aquar.myaquar_egypt.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aquar.myaquar_egypt.R;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class PanoramaView extends AppCompatActivity {

    private VrPanoramaView vrPanoramaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama_view);


        vrPanoramaView=findViewById(R.id.panorama);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.newphoto);
        vrPanoramaView.loadImageFromBitmap(icon, new VrPanoramaView.Options());
        vrPanoramaView.setInfoButtonEnabled(false);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
    //
    @Override
    protected void onPause() {
        super.onPause();
    }
}
