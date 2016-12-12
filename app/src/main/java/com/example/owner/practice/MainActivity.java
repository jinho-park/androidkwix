package com.example.owner.practice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int MY_PERMISSION_REQUEST_INTERNET = 2;
    private static final String TAG = "Internet permission";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionCheck();
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.menu_item_mypage) {
            //fragmentReplace();
        }else if(id == R.id.menu_item_notice) {
            fragmentReplace(super.NOTICE);
        }else if(id == R.id.menu_item_setting){
            fragmentReplace(super.SETTING);
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.activity_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void PermissionCheck(){
        Log.d(TAG, "Permission Check");

        //if(Build.VERSION.SDK_INT>=23){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "INTERNET SUCCESS");
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSION_REQUEST_INTERNET);
                }else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSION_REQUEST_INTERNET);
                }
            }
        //}
    }
}
