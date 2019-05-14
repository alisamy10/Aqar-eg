package com.aquar.myaquar_egypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Adapter.AdapterOfListOfPaymentMethod;
import com.aquar.myaquar_egypt.Fragments.homeFragment;
import com.aquar.myaquar_egypt.InternalStorage.Session;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.Model.ModelOfListOfPaymentMethod;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ArrayModelOfProjectsDetails;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ModelObjectsOfProjectDetails;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ProjectdetailsActivity extends AppCompatActivity {
    private SliderLayout Product_Slider;
    private Button see_more_btn, like_btn, struct_btn, location_btn, call_btn, share_btn, go_youtube, send_email_btn;
    private TextView description, devolepor, project_name, textprice, texttype, textMinBedrooms, textmaxBedrooms, textMinBathroom,
            textMaxBathrooms, textMinArea, textMaxArea;
    private ScrollView sc;

    //    private AlertDialog dialog1;
    private Dialog dialog1;


    private String description_string;
    private LinearLayout parentOfProjectDetails;

    AdapterOfListOfPaymentMethod adapter;

    ArrayList<ModelOfListOfPaymentMethod> listpay = new ArrayList<>();

    List<String> urlimage = new ArrayList<>();

    ArrayList<ModelObjectsOfProjectDetails> list = new ArrayList<>();
    ModelObjectsOfProjectDetails objectsOfProjectDetails;
    //    ModelObjectsOfProjectDetails objectsOfProjectDetails ;
    ListView listPayment;


    homeFragment getId = new homeFragment();

    String x[] = {"40%", "20%"};
    String z[] = {"7 years", "8 years"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        myUtils.setLocale(this);


        listPayment = findViewById(R.id.list_of_payment_method);


        listpay.add(new ModelOfListOfPaymentMethod(x[1], z[1]));
//        listpay.add(new ModelOfListOfPaymentMethod(x[1], z[1]));


        adapter = new AdapterOfListOfPaymentMethod(this, R.layout.item_list_of_payment_method, listpay);
        listPayment.setAdapter(adapter);

        setListViewHeightBasedOnChildren(listPayment);


        parentOfProjectDetails = findViewById(R.id.parentOfProjectDetails);


//        dialog1 = new SpotsDialog.Builder().setContext(ProjectdetailsActivity.this).setTheme(R.style.Custom).build();
//        dialog1.setMessage("Please wait.....");
//        dialog1.show();

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();


        see_more_btn = findViewById(R.id.see_more_btn);
        description = findViewById(R.id.description);
        like_btn = findViewById(R.id.like);
        share_btn = findViewById(R.id.share);
//        location_btn = findViewById(R.id.go_location);
        struct_btn = findViewById(R.id.structure);
        call_btn = findViewById(R.id.call);
        textprice = findViewById(R.id.price);
        send_email_btn = findViewById(R.id.send_email);

        project_name = findViewById(R.id.project_name);

        devolepor = findViewById(R.id.devolepor);

        texttype = findViewById(R.id.type);

        textMinBedrooms = findViewById(R.id.bedroom);
        textmaxBedrooms = findViewById(R.id.maxBedroom);

        textMinBathroom = findViewById(R.id.bathroom);
        textMaxBathrooms = findViewById(R.id.maxBathroom);


        textMinArea = findViewById(R.id.area);
        textMaxArea = findViewById(R.id.maxArea);

        // here get id of item from home for get its detalis
//        homeFragment getid = new homeFragment();
//
//        int x = getid.id;

//
        if (Session.getInstance().getTypesOfUnitID() != null) {
            reciveDate(Integer.parseInt(Session.getInstance().getTypesOfUnitID()));
        }
//       else if (x != 0) {
//            reciveDate(x);
//        }
        //------------------------------------------------------------


        send_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();

            }
        });


        struct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProjectdetailsActivity.this, structActivity.class);
                startActivity(i);
            }
        });


        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
//                share.putExtra(Intent.EXTRA_TEXT,
//                        "https://play.google.com/store/apps/details?id=com.aswany.android.myaquar_eg");
                share.putExtra(Intent.EXTRA_TEXT,
                        ConstantsUrl.ShareLink + objectsOfProjectDetails.getId());
//                share.setType("textDes/plain");
                share.setType("text/html");
                startActivity(share);
            }
        });

//        location_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String geoUri = "http://maps.google.com/maps?q=loc:" + 30.145305 + "," + 31.630784 + " (" + "Shourok" + ")";
//                Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
//                startActivity(map);
//            }
//        });
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contact = new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:01033110330"));
                startActivity(contact);

            }
        });

        see_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_all_description();
                //  see_more_btn.setVisibility(View.INVISIBLE);
            }
        });

        Product_Slider = (SliderLayout) findViewById(R.id.Product_Slider);


    }


    private void show_all_description() {
        String x = see_more_btn.getText().toString();
        if (x.equals("See more")) {
            String s = (String) description.getText();
            s += description_string.substring(0, 90);
            description.setText(description_string);
            see_more_btn.setText("See Less");
        } else {
            //  String s = getResources().getString(R.string.some_description);
            description.setText(description_string.substring(0, 90));
            see_more_btn.setText("See more");
        }

    }


    private void sendEmail() {
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        String[] TO = {"info@myaquar-eg.com"};
        String recepientEmail = ""; // either set to destination email or leave empty
        String Subject;

        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {
                String Lines = "--------------------------------";
                Subject = "Enquiry regarding: " + objectsOfProjectDetails.getProject() + " , Project ID: "
                        + objectsOfProjectDetails.getId() + " , " + objectsOfProjectDetails.getMin_price() + " EGP";
                String Content = "Name              : " + userPOJO.getUsername()
                        + "\n" + "Mobile Phone : " + userPOJO.getPhone()
                        + "\n" + "Job Title         : " + userPOJO.getJobTitle();
                String Body = "\n" + "\n" + "\n" + "\n" + "\n" + Lines + "\n" + "\n" + Content + "\n" + "\n" + Lines;
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

                Log.d("developer: ", objectsOfProjectDetails.getDeveloper() + "");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, Body);

                startActivity(emailIntent);
            }

        } catch (Exception e) {
            Subject = "Enquiry regarding: " + objectsOfProjectDetails.getProject() + " , Project ID: "
                    + objectsOfProjectDetails.getId() + " , " + objectsOfProjectDetails.getMin_price() + " EGP";
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");

            startActivity(emailIntent);
        }


    }


    private void reciveDate(int idValue) {
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        final JSONObject object = new JSONObject();
        if (!Objects.equals(userPOJO, null)) {
            String UserId = String.valueOf(userPOJO.getUserId());
            Log.d("blog", UserId + "");
            try {
                object.put("id", idValue);
                object.put("user_id", UserId);

            } catch (JSONException e) {
                e.getStackTrace();
            }
        } else {
            try {
                Log.d("blog", idValue + "");
                object.put("id", idValue);

            } catch (JSONException e) {
                e.getStackTrace();
            }
        }
        AndroidNetworking.post(ConstantsUrl.SingleProject)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parentOfProjectDetails.setVisibility(View.VISIBLE);
                        dialog1.dismiss();
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ArrayModelOfProjectsDetails array = gson.fromJson(response.toString(), ArrayModelOfProjectsDetails.class);
//                        objectsOfProjectDetails = gson.fromJson(response.toString(), ModelObjectsOfProjectDetails.class);
                        list = array.getProject();
                        objectsOfProjectDetails = list.get(0);


                        //loop for image of slider
                        for (int i = 0; i < objectsOfProjectDetails.getSlider_images().size(); i++) {
                            String x = objectsOfProjectDetails.getSlider_images().get(i).getImage_url();
                            urlimage.add(x);
                        }

                        DataOfSlider(urlimage);
                        //Structure Images
                        Session.getInstance().setStructureImages(objectsOfProjectDetails.getStructure_images());

                        description_string = objectsOfProjectDetails.getDescription();
                        // for set all texts of details
                        setTdevoleporandproject(objectsOfProjectDetails.getDescription(), objectsOfProjectDetails.getDeveloper(), objectsOfProjectDetails.getProject(),
                                objectsOfProjectDetails.getMin_price()
                                , objectsOfProjectDetails.getType(), objectsOfProjectDetails.getMin_rooms(), objectsOfProjectDetails.getMax_rooms(),
                                objectsOfProjectDetails.getMin_bathsrooms()
                                , objectsOfProjectDetails.getMax_bathsrooms(), objectsOfProjectDetails.getMin_area(), objectsOfProjectDetails.getMax_area(), objectsOfProjectDetails.getPrice_label()

                        );
                        liked_projects(Boolean.valueOf(objectsOfProjectDetails.getFavorite()));
                        // for like button

                        like_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setFavourite(Boolean.valueOf(objectsOfProjectDetails.getFavorite()), objectsOfProjectDetails.getId());
                                Boolean Indicator = Boolean.valueOf(objectsOfProjectDetails.getFavorite());
                                Log.d("Liked", !Indicator + "");
                                liked_projects(Indicator);

                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {

                        dialog1.dismiss();
                        myUtils.handleError(ProjectdetailsActivity.this, anError.getErrorBody(), anError.getErrorCode());
                        Toast.makeText(ProjectdetailsActivity.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void DataOfSlider(List imageUrl) {
        HashMap<String, String> file_maps = new HashMap<String, String>();
        Session.getInstance().setUrlimage(urlimage);
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

            Product_Slider.addSlider(textSliderView);
        }
        Product_Slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Product_Slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Product_Slider.setCustomAnimation(new DescriptionAnimation());
        Product_Slider.setDuration(4000);
    }


    private void setTdevoleporandproject(String dec, String developer, String project, int price,
                                         String type, String minrooms, String maxrooms
            , String minBathrooms, String maxBathrooms, String minArea, String maxArea, String price_label) {

        description.setText(dec.substring(0, 90));
//        Log.d("Data: ",price+" : "+type+" : "+minArea+"-"+maxArea);
        devolepor.setText(developer);
        project_name.setText(project);

        textprice.setText(price + " " + price_label);

        texttype.setText(type);

        textMinBedrooms.setText(minrooms + "");
        textmaxBedrooms.setText(maxrooms + "");

        textMinBathroom.setText(minBathrooms + "");
        textMaxBathrooms.setText(maxBathrooms + "");

        textMinArea.setText(minArea + " " + "mq2");
        textMaxArea.setText(maxArea + " " + "mq2");

    }

    private void liked_projects(Boolean like) {
        if (!like) {
            like_btn.setBackgroundResource(R.drawable.like);
        } else {
            like_btn.setBackgroundResource(R.drawable.liked);
        }
    }

    public void setFavourite(Boolean like, int favouriteID) {
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        JSONObject object = new JSONObject();

        try {
            dialog1.show();
            object.put("user_id", String.valueOf(userPOJO.getUserId()));
            object.put("project_id", favouriteID);
            AndroidNetworking.post(ConstantsUrl.favorite)
                    .setPriority(Priority.MEDIUM)
                    .addJSONObjectBody(object)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (response.toString().contains("add")) {
                                Toast.makeText(ProjectdetailsActivity.this, "Add to favourite", Toast.LENGTH_SHORT).show();
                                liked_projects(true);
                            } else if (response.toString().contains("deleted")) {
                                Toast.makeText(ProjectdetailsActivity.this, "deleted from favourite", Toast.LENGTH_SHORT).show();
                                liked_projects(false);

                            }
                            dialog1.dismiss();
                        }

                        @Override
                        public void onError(ANError anError) {
                            myUtils.handleError(ProjectdetailsActivity.this, anError.getErrorBody(), anError.getErrorCode());
                            dialog1.dismiss();

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            dialog1.dismiss();
            Toast.makeText(this, "Please Sign in First", Toast.LENGTH_SHORT).show();


        }
    }


    // محدش يمسح الفانكشن ديه اللي هيمسحها هعوره
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;

        View.MeasureSpec m = new View.MeasureSpec();


        int desiredWidth = m.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) view.setLayoutParams(new
                    ViewGroup.LayoutParams(desiredWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProjectdetailsActivity.this, ProjectTypesActivity.class));
        finish();

    }


}



