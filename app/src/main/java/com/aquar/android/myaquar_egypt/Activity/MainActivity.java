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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.aquar.android.myaquar_egypt.Adapter.ExpandListAdapter;
import com.aquar.android.myaquar_egypt.Fragments.Favourite;
import com.aquar.android.myaquar_egypt.Fragments.Profile_fragment;
import com.aquar.android.myaquar_egypt.Fragments.fragment_home;
import com.aquar.android.myaquar_egypt.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "Home_Activity_home";
    private Fragment fragment;
    private FragmentTransaction transaction;
    private Button buttonnavegation;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        prepareListData();
        listAdapter = new ExpandListAdapter(MainActivity.this, listDataHeader, listDataChild) ;
        expListView.setAdapter(listAdapter);

        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();

        buttonnavegation = (Button) findViewById(R.id.navegation_button_menue);
        buttonnavegation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.openDrawer(GravityCompat.START);
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (groupPosition==0)
                {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT)

                        .show();}
                        else
                {
                    Intent i=new Intent(getApplicationContext(),struct_activity.class);
                    startActivity(i);
                }


                return false;
            }
        });


        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition==0) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {



                    Intent i=new Intent(getApplicationContext(),struct_activity.class);
                    startActivity(i);
                }

            }

        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                if (groupPosition==0) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {


                    Intent i=new Intent(getApplicationContext(),struct_activity.class);
                    startActivity(i);
                }


            }
        });

        dl = (DrawerLayout) findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.project_id_nav:
                        Toast.makeText(MainActivity.this, "project", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.news_event_nav:
                        Toast.makeText(MainActivity.this, "news and enents", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_us_nav:
                        Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contact_us_nav:
                        Toast.makeText(MainActivity.this, "contact us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.terma_polic_nav:
                        Toast.makeText(MainActivity.this, "term and polices", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting_nav:
                        Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout_nav:
                        Toast.makeText(MainActivity.this, "log out", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });


    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Categories");

        List<String> top250 = new ArrayList<String>();
        top250.add("Residential");
        top250.add("Second home");
        top250.add("Commercial");
        top250.add("Medical");
        top250.add("Adminstrative");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data

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

            case R.id.Navigation_fav:


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

    @OnClick(R.id.searchBtn)
    public void onSearchClick(){
        startActivity(new Intent(MainActivity.this,Filter.class));
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void favourite(View view) {
        fragment = new Favourite();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Favourite");
        transaction.commitNow();
    }

    public void home(View view) {
        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();
    }

    public void profile(View view) {
        fragment = new Profile_fragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "profile");
        transaction.commitNow();
    }
}