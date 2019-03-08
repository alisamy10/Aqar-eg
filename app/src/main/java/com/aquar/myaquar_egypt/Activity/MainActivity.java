package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.myaquar_egypt.Adapter.ExpandListAdapter;
import com.aquar.myaquar_egypt.Fragments.Favourite;
import com.aquar.myaquar_egypt.Fragments.Profile_fragment;
import com.aquar.myaquar_egypt.Fragments.fragment_home;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime ;
    private static final String TAG = "Home_Activity_home";
    private Fragment fragment;
    private FragmentTransaction transaction;
    private Button buttonnavegation;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    private ListView listView;

    private TextView nav_header_profile_name_TV, nav_header_profile_email_TV;

    public static String headerOfCategory;
    public static String idForCategoryOfNav;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] values = new String[]{
            "News and Events",
            "About us",
            "Contact us",
            "Terms and policies",
            "Log out "
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myUtils.setLocale(this);

        getUserData();
        //id
        dl = findViewById(R.id.activity_main);
        buttonnavegation = findViewById(R.id.navegation_button_menue);
        expListView = findViewById(R.id.lvExp);
        listView = findViewById(R.id.list_item);





        firstFragmentRun();
        listViewOfNavDrawer();
        prepareListData();
        expandableListViewForNavDrawer();


        buttonnavegation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(GravityCompat.START);
            }
        });

        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();


    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Categories");

        List<String> top250 = new ArrayList<String>();
        top250.add("Residential");
        top250.add("Holiday Home");
        top250.add("Commercial");
        top250.add("Medical");
        top250.add("Lunch Soon");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data

    }


//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (t.onOptionsItemSelected(item))
//            return true;
//
//        return super.onOptionsItemSelected(item);
//    }

    @OnClick(R.id.searchBtn)
    public void onSearchClick() {
        startActivity(new Intent(MainActivity.this, Filter.class));
    }



    private void listViewOfNavDrawer() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //news and events
                if (position == 0) {
                    Intent go = new Intent(MainActivity.this, EventsAndNews.class);
                    startActivity(go);
                    finish();
                }
                //about us
                else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, AboutUs.class));
                    finish();

                }
                //contact us
                else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, Contact_us.class));

                    finish();
                }
                //terms and policies
                else if (position == 3) {
                    startActivity(new Intent(MainActivity.this, TermsAndPolicies.class));
                    finish();

                }
                //logout
                else if (position == 4) {

                    mySharedPreference.setUserOBJ("");
                    startActivity(new Intent(MainActivity.this, Splash.class));
                    finish();

                }

            }
        });

        closeExpandableListWhenScroll();


    }


    private void closeExpandableListWhenScroll() {

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState != 0) {
                    expListView.collapseGroup(0);

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }


    private void expandableListViewForNavDrawer() {


        listAdapter = new ExpandListAdapter(MainActivity.this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                //Residential
                if (childPosition == 0) {
                    startActivity(new Intent(MainActivity.this, Category.class));
                    finish();
                    idForCategoryOfNav = String.valueOf(myUtils.Residential);
                    headerOfCategory = "Residential";

                }
                //Holiday Home
                else if (childPosition == 1) {
                    startActivity(new Intent(MainActivity.this, Category.class));
                    finish();
                    idForCategoryOfNav = String.valueOf(myUtils.HolidayHome);
                    headerOfCategory = "Holiday Home";

                }
                //commercial
                else if (childPosition == 2) {
                    startActivity(new Intent(MainActivity.this, Category.class));
                    finish();
                    idForCategoryOfNav = String.valueOf(myUtils.Commercial);
                    headerOfCategory = "Commercial";
                }
                //medical
                else if (childPosition == 3) {
                    startActivity(new Intent(MainActivity.this, Category.class));
                    finish();
                    idForCategoryOfNav = String.valueOf(myUtils.Medical);
                    headerOfCategory = "Medical";
                }
                //LunchSoon
                else if (childPosition == 4) {
                    startActivity(new Intent(MainActivity.this, Category.class));
                    finish();
                    idForCategoryOfNav = String.valueOf(myUtils.LunchSoon);
                    headerOfCategory = "Lunch Soon";


                }

                return false;
            }
        });

    }

    private void firstFragmentRun() {
        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();




        TextView textView1=findViewById(R.id.home_text_id);
        textView1.setTextColor(Color.parseColor("#FF0000"));

        ImageButton imageButton1=findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.home_red);


    }


    public void favourite(View view) {
        fragment = new Favourite();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Favourite");
        transaction.commitNow();



        TextView textView1=findViewById(R.id.favert_text_id);
        textView1.setTextColor(Color.parseColor("#FF0000"));

        TextView textView2=findViewById(R.id.home_text_id);
        textView2.setTextColor(Color.parseColor("#cccccc"));

        TextView textView3=findViewById(R.id.account_text_id);
        textView3.setTextColor(Color.parseColor("#cccccc"));




        ImageButton imageButton=findViewById(R.id.favert_icon_id);
        imageButton.setImageResource(R.drawable.fav_red);

        ImageButton imageButton1=findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.home_norm);

        ImageButton imageButton2=findViewById(R.id.account_icon_id);
        imageButton2.setImageResource(R.drawable.acc_norm);





    }

    public void home(View view) {
        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();


        TextView textView1=findViewById(R.id.home_text_id);
        textView1.setTextColor(Color.parseColor("#FF0000"));




        TextView textView2=findViewById(R.id.favert_text_id);
        textView2.setTextColor(Color.parseColor("#cccccc"));

        TextView textView3=findViewById(R.id.account_text_id);
        textView3.setTextColor(Color.parseColor("#cccccc"));



        ImageButton imageButton=findViewById(R.id.favert_icon_id);
        imageButton.setImageResource(R.drawable.favrote);

        ImageButton imageButton1=findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.home_red);

        ImageButton imageButton2=findViewById(R.id.account_icon_id);
        imageButton2.setImageResource(R.drawable.acc_norm);



    }

    public void profile(View view) {
        fragment = new Profile_fragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "profile");
        transaction.commitNow();






        TextView textView1=findViewById(R.id.favert_text_id);
        textView1.setTextColor(Color.parseColor("#cccccc"));

        TextView textView2=findViewById(R.id.home_text_id);
        textView2.setTextColor(Color.parseColor("#cccccc"));

        TextView textView3=findViewById(R.id.account_text_id);
        textView3.setTextColor(Color.parseColor("#FF0000"));




        ImageButton imageButton=findViewById(R.id.favert_icon_id);
        imageButton.setImageResource(R.drawable.favrote);

        ImageButton imageButton1=findViewById(R.id.home_icon_id);
        imageButton1.setImageResource(R.drawable.home_norm);

        ImageButton imageButton2=findViewById(R.id.account_icon_id);
        imageButton2.setImageResource(R.drawable.acc_red);


    }


    public void getUserData() {
        NavigationView navigationView =  findViewById(R.id.Navigation_drawer);
        View headerView = navigationView.getHeaderView(0);
        nav_header_profile_name_TV = headerView.findViewById(R.id.nav_header_profile_name_TV);
        nav_header_profile_email_TV = headerView.findViewById(R.id.nav_header_profile_email_TV);
        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {
//                Glide.with(this).load(userPOJO.getToken()).into(profile_photo);
                nav_header_profile_name_TV.setText(userPOJO.getUsername());
                nav_header_profile_email_TV.setText(userPOJO.getEmail());
            }


        } catch (Exception e) {
            e.printStackTrace();
            nav_header_profile_name_TV.setText("Please Sign In First");
            nav_header_profile_email_TV.setText("");
            nav_header_profile_name_TV.setTextColor(getResources().getColor(R.color.Red));

        }
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                finishAffinity();
            } else {
                Toast.makeText(this, "press again to exit ", Toast.LENGTH_SHORT).show();
            }

            backPressedTime = System.currentTimeMillis();
        }


    }

}
