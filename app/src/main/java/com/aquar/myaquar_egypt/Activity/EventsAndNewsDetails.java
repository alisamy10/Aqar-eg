package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.EventandNewsDetailsModel.Model_array_of_Eventandnews;
import com.aquar.myaquar_egypt.Model.EventandNewsDetailsModel.Model_eventandnews_details;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ArrayModelOfProjectsDetails;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ModelObjectsOfProjectDetails;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.vr.sdk.proto.nano.CardboardDevice;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import dmax.dialog.SpotsDialog;


public class EventsAndNewsDetails extends AppCompatActivity {

    private Button Attend_btn;
    private SliderLayout Event_slider;
    private AlertDialog dialog1;
    private TextView event_description, titile, event_devolper;

    private Button share_btn, phone_btn;

    List<String> urlimage = new ArrayList<>();

    private  ArrayList<Model_eventandnews_details> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news_details);



        dialog1 = new SpotsDialog.Builder().setContext(EventsAndNewsDetails.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();

      //  Toast.makeText(this, ""+EventsAndNews.id_event, Toast.LENGTH_SHORT).show();
       GetCategoryData( EventsAndNews.id_event);


        Attend_btn = findViewById(R.id.Attend);

        Attend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        share_btn=findViewById(R.id.share_event);
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=com.youssef.maggy.aqartest");
                share.setType("textDes/plain");
                startActivity(share);

            }
        });
        phone_btn=findViewById(R.id.call_event);
        phone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contact = new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:01095488883"));
                startActivity(contact);

            }
        });


        Event_slider = findViewById(R.id.event_Slider);


        event_description=findViewById(R.id.description_event);
        event_devolper=findViewById(R.id.event_devolepor);
        titile=findViewById(R.id.Event_name);

    }

    private void GetCategoryData(int value) {


        AndroidNetworking.get(ConstantsUrl.EventandNews)
                .addQueryParameter("id", String.valueOf(value))
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        Model_array_of_Eventandnews array = gson.fromJson(response.toString(), Model_array_of_Eventandnews.class);
                        list = array.getProject();

                        for (int i = 0; i < list.get(0).getSlider_images().size(); i++) {
                            String x = list.get(0).getSlider_images().get(i).getImage_url();
                            urlimage.add(x);
                        }

                        DataOfSlider(urlimage);

                        seteventdata(list.get(0).getDescription(),list.get(0).getProject(),list.get(0).getTitle());

                        dialog1.dismiss();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(EventsAndNewsDetails.this, "Connection Error", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                    }
                });
    }
    private void DataOfSlider(List imageUrl) {
        HashMap<String, String> file_maps = new HashMap<String, String>();

        for (int i = 0; i < urlimage.size(); i++) {
            file_maps.put("Phase" + i + 1, imageUrl.get(i).toString());

        }
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            Event_slider.addSlider(textSliderView);
        }
        Event_slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Event_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Event_slider.setCustomAnimation(new DescriptionAnimation());
        Event_slider.setDuration(4000);
    }

    private void seteventdata(String dec, String event_dev, String name)
    {

        event_description.setText(dec);
        event_devolper.setText(event_dev);
        titile.setText(name);


    }


    private void sendEmail() {

        String[] TO = {"someone@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        String recepientEmail = ""; // either set to destination email or leave empty
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        startActivity(emailIntent);

    }

}
