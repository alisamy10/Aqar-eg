package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.aquar.myaquar_egypt.Adapter.ExpandListAdapter;
import com.aquar.myaquar_egypt.Fragments.Favourite;
import com.aquar.myaquar_egypt.Fragments.Profile_fragment;
import com.aquar.myaquar_egypt.Fragments.fragment_home;
import com.aquar.myaquar_egypt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  {


    private static final String TAG = "Home_Activity_home";
    private Fragment fragment;
    private FragmentTransaction transaction;
    private Button buttonnavegation;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ListView listView ;
   

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] values = new String[] {
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

        //id
        dl = findViewById(R.id.activity_main);
        buttonnavegation =  findViewById(R.id.navegation_button_menue);
        expListView =  findViewById(R.id.lvExp);
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
        top250.add("Second home");
        top250.add("Commercial");
        top250.add("Medical");
        top250.add("Adminstrative");

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


    private void listViewOfNavDrawer(){


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState!=0) {
                    expListView.collapseGroup(0);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
      private void expandableListViewForNavDrawer(){


          listAdapter = new ExpandListAdapter(MainActivity.this, listDataHeader, listDataChild) ;
          expListView.setAdapter(listAdapter);
          expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

              @Override
              public boolean onChildClick(ExpandableListView parent, View v,
                                          int groupPosition, int childPosition, long id) {
                  if (groupPosition==0)
                  {
                      Toast.makeText(
                              getApplicationContext(), listDataHeader.get(groupPosition)
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

      }

    private void firstFragmentRun(){
        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();
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