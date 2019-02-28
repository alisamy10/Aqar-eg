package com.aquar.myaquar_egypt.Activity;

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


import com.aquar.myaquar_egypt.Fragments.Favourite;
import com.aquar.myaquar_egypt.Fragments.Profile_fragment;
import com.aquar.myaquar_egypt.Fragments.fragment_home;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**/
public class MainActivity extends AppCompatActivity  {


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
        ButterKnife.bind(this);


        fragment = new fragment_home();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment, "Med_Data_Fragment");
        transaction.commitNow();



        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.catog_nav:

                        Toast.makeText(MainActivity.this, "categories", Toast.LENGTH_SHORT).show();

                        break;
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
                        mySharedPreference.setUserOBJ("");
                        startActivity(new Intent(MainActivity.this,Splash.class));
                        finish();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });


        buttonnavegation = findViewById(R.id.navegation_button_menue);
        buttonnavegation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.openDrawer(GravityCompat.START);
            }
        });


        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();



    }
    @OnClick(R.id.searchBtn)
    public void onSearchClick(){
        startActivity(new Intent(MainActivity.this,Filter.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navigation_menu,menu);
        return true;

    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
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