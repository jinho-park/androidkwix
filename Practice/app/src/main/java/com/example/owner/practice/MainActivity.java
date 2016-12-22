package com.example.owner.practice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);//올해
            int month = calendar.get(Calendar.MONTH);//이번달(10월이면 9를 리턴받는다. calendar는 0월부터 11월까지로 12개의월을 사용)
            int day = calendar.get(Calendar.DAY_OF_MONTH);//오늘날짜

            calendar.set(year, month, day, 12, 50);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10*60 * 1000, sender);
            //getSupportActionBar().hide();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
            fragmentReplace(super.MYPAGE);
        }else if(id == R.id.menu_item_notice) {
            fragmentReplace(super.NOTICE);
        }else if(id == R.id.menu_item_setting){
            fragmentReplace(super.SETTING);
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.activity_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
