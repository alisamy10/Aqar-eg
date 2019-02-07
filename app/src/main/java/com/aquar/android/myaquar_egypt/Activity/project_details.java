package com.aquar.android.myaquar_egypt.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aquar.android.myaquar_egypt.R;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class project_details extends AppCompatActivity {
    private SliderLayout Product_Slider;
    private Button see_more_btn , like_btn;
    private TextView description;
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        see_more_btn =(Button)findViewById(R.id.see_more_btn);
        description=(TextView)findViewById(R.id.description);
        like_btn =(Button)findViewById(R.id.like);

        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liked_projects();
            }
        });

        see_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_all_description();
                see_more_btn.setVisibility(View.INVISIBLE);
            }
        });

        Product_Slider = (SliderLayout)findViewById(R.id.Product_Slider);
        DataOfSlider();

    }



    private void DataOfSlider() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Phase 1", R.drawable.pashe1);
        file_maps.put("Phase 2", R.drawable.pashe2);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            Product_Slider.addSlider(textSliderView);
        }
        Product_Slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Product_Slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Product_Slider.setCustomAnimation(new DescriptionAnimation());
        Product_Slider.setDuration(4000);
    }
    private void show_all_description()
    {
        String s=(String) description.getText();
        s+= getResources().getString(R.string.more_description);
        description.setText(s);

    }
    private void liked_projects ()
    {
        n++;
       if (n%2==0)
        like_btn.setBackgroundResource(R.drawable.like);
       else like_btn.setBackgroundResource(R.drawable.liked);
    }

}



