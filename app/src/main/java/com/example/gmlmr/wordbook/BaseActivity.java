package com.example.gmlmr.wordbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpNavigationDrawer();
    }

    private void setUpNavigationDrawer() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);//Menu items for navigation view defined in res/menu/activity_main_drawer.
        setSupportActionBar(toolbar);//Set toolbar as actionbar. Remember to set Theme in manifest to be of type 'NoActionBar' if using toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(getNavigationDrawerItemSelectListener());//Set navigation item select listener
    }

    private NavigationView.OnNavigationItemSelectedListener getNavigationDrawerItemSelectListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Handle navigation view item clicks here.
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_mywordlist:
                        //Handle My word list screen
                         Intent i = new Intent(BaseActivity.this,MyWordList.class);
                        startActivity(i);
                        //Toast.makeText(BaseActivity.this, "MyWord", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_favlist:

                         Intent i1 = new Intent(BaseActivity.this,FavListActivity.class);
                        startActivity(i1);

                        //Toast.makeText(BaseActivity.this, "FavList", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_add:

                        Intent i2 = new Intent(BaseActivity.this,AddInputActivity.class);
                        startActivity(i2);

                        //Toast.makeText(BaseActivity.this, "Input", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_word:

                         Intent i3 = new Intent(BaseActivity.this,WordofDay.class);
                        startActivity(i3);
                       // Toast.makeText(BaseActivity.this, "WordofDay", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
