package com.aquar.android.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.android.myaquar_egypt.Adapter.ExpandListAdapter;
import com.aquar.android.myaquar_egypt.Fragments.Profile_fragment;
import com.aquar.android.myaquar_egypt.Fragments.fragment_home;
import com.aquar.android.myaquar_egypt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**/
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Home_Activity_home";

    private Fragment fragment;
    private FragmentTransaction transaction;
    private Button buttonnavegation;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();
        TextView textView1 = (TextView) findViewById(R.id.name_fragment);
        textView1.setText("Home");

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

//////////////////////////////////
        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
//kjk
        dl.addDrawerListener(t);
        t.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.catog_nav:
                        Toast.makeText(MainActivity.this, "categories", Toast.LENGTH_SHORT).show();


                    case R.id.project_id_nav:
                        Toast.makeText(MainActivity.this, "project", Toast.LENGTH_SHORT).show();
                    case R.id.news_event_nav:
                        Toast.makeText(MainActivity.this, "news and enents", Toast.LENGTH_SHORT).show();

                    case R.id.about_us_nav:
                        Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT).show();
                    case R.id.contact_us_nav:
                        Toast.makeText(MainActivity.this, "contact us", Toast.LENGTH_SHORT).show();
                    case R.id.terma_polic_nav:
                        Toast.makeText(MainActivity.this, "term and polices", Toast.LENGTH_SHORT).show();
                    case R.id.setting_nav:
                        Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();

                    case R.id.logout_nav:
                        Toast.makeText(MainActivity.this, "log out", Toast.LENGTH_SHORT).show();

                    default:
                        return true;
                }


            }
        });


    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Navigation_hom:
                TextView textView1 = (TextView) findViewById(R.id.name_fragment);

                textView1.setText("Home");

                Log.d(TAG, "Linear_Res" + "");
                fragment = new fragment_home();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_home, fragment, "Res_Data_Fragment");
                transaction.commitNow();
                return true;

            case R.id.Navigation_fav:
                TextView textView2 = (TextView) findViewById(R.id.name_fragment);
                textView2.setText("Favorite");
            /*
                Log.d(TAG, "Linear_Com" + "");
                 fragment = new //////name fragment(favorite)///////;
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.////name xml fragment////, fragment, "Com_Data_Fragment");
                transaction.commitNow();
                */
                return true;
            case R.id.Navigation_acc:

                TextView textView3 = (TextView) findViewById(R.id.name_fragment);
                textView3.setText("Account");


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
        listDataHeader.add("Product1");
        listDataHeader.add("product2");
        listDataHeader.add("Product3");

        // Adding child data
        List<String> top = new ArrayList<String>();
        top.add("x1");
        top.add("x2");
        top.add("x3");
        top.add("x4");
        top.add("x5");


        List<String> mid = new ArrayList<String>();
        mid.add("y1");
        mid.add("y2");
        mid.add("y3");

        List<String> bottom = new ArrayList<String>();
        bottom.add("z1");
        bottom.add("z2");
        bottom.add("z3");


        listDataChild.put(listDataHeader.get(0), top); // Header, Child data
        listDataChild.put(listDataHeader.get(1), mid);
        listDataChild.put(listDataHeader.get(2), bottom);
    }


    private void enableExpandableList() {


        final ArrayList<String> listDataHeader = new ArrayList<String>();
        final HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listDataChild);
        ExpandListAdapter listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                // Temporary code:

                // till here
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}