package com.aquar.android.myaquar_egypt.Activity;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.android.myaquar_egypt.Adapter.ExpandListAdapter;
import com.aquar.android.myaquar_egypt.Fragments.Favourite;
import com.aquar.android.myaquar_egypt.Fragments.Profile_fragment;
import com.aquar.android.myaquar_egypt.Fragments.fragment_home;
import com.aquar.android.myaquar_egypt.Fragments.home_second_view_Fragment;
import com.aquar.android.myaquar_egypt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**/
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Home_Activity_home";
    private Button button;
    private Fragment fragment;
    private FragmentTransaction transaction;
    private Button buttonnavegation;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    final ArrayList<String> listDataHeader = new ArrayList<String>();
    final HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();


        BottomNavigationView Home_BottomNavi;
        Home_BottomNavi = findViewById(R.id.Home_BottomNavi);
        Home_BottomNavi.setOnNavigationItemSelectedListener(this);

        buttonnavegation = (Button) findViewById(R.id.navegation_button_menue);
        /////
        buttonnavegation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.openDrawer(GravityCompat.START);
            }
        });


        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();



        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);





    }
    @OnClick(R.id.searchBtn)
    public void onSearchClick(){
        startActivity(new Intent(MainActivity.this,Filter.class));
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.catog_nav:
//                Toast.makeText(MainActivity.this, "categories1", Toast.LENGTH_SHORT).show();
//
//                break;
//
//            case R.id.catog_nav1:
//                Toast.makeText(MainActivity.this, "categories1", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.catog_nav2:
//                Toast.makeText(MainActivity.this, "categories2", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.catog_nav3:
//                Toast.makeText(MainActivity.this, "categories3", Toast.LENGTH_SHORT).show();
//
//                break;
//            case R.id.project_id_nav:
//                Toast.makeText(MainActivity.this, "project", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.news_event_nav:
//                Toast.makeText(MainActivity.this, "news and enents", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.about_us_nav:
//                Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.contact_us_nav:
//                Toast.makeText(MainActivity.this, "contact us", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.terma_polic_nav:
//                Toast.makeText(MainActivity.this, "term and polices", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.setting_nav:
//                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.logout_nav:
//                Toast.makeText(MainActivity.this, "log out", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigation_menu,menu);
        return true;

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


            case R.id.Navigation_hom:
                Log.d(TAG, "Linear_Res" + "");
                fragment = new fragment_home();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_home, fragment, "Res_Data_Fragment");
                transaction.commitNow();
                return true;
//Lh;kjh
            case R.id.Navigation_fav:

                fragment = new Favourite();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_home, fragment, "Favourite");
                transaction.commitNow();
            /*
                Log.d(TAG, "Linear_Com" + "");
                 fragment = new //////name fragment(favorite)///////;
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.////name xml fragment////, fragment, "Com_Data_Fragment");
                transaction.commitNow();
                */
                return true;
            case R.id.Navigation_acc:


                Log.d(TAG, "Linear_Med" + "");
                fragment = new Profile_fragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
                transaction.commitNow();

                return true;


            default:
                Log.d(TAG, "Default" + "");
                break;

        }

        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    private void prepareListData(List<String> listDataHeader, Map<String,
            List<String>> listDataChild) {


        // Adding child data
        listDataHeader.add("catigare");


        // Adding child data
        List<String> top = new ArrayList<String>();
        top.add("x1");
        top.add("x2");
        top.add("x3");
        top.add("x4");
        top.add("x5");


        if (!listDataChild.isEmpty()) {
            listDataChild.put(listDataHeader.get(0), top); // Header, Child data

        }

    }


//    private void enableExpandableList() {
//
//
////        final ExpandableListView expListView = (ExpandableListView) findViewById(R.id.left_drawer);
//
//        prepareListData(listDataHeader, listDataChild);
//        final ExpandListAdapter listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "Group Clicked " + listDataHeader.get(groupPosition),
//                        Toast.LENGTH_SHORT).show();
////                 expListView.setAdapter(listAdapter);
//
//                return false;
//            }
//        });
//        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        // Listview on child click listener
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
//                // Temporary code:
//
//                // till here
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}