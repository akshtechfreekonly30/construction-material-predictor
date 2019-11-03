package com.example.asmodeus.constcalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    String r,l,w,f,h,bud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_tv);

        String str=getIntent().getStringExtra("title");
        navUsername.setText(str);

        r=getIntent().getStringExtra("ticket_rm");
        l=getIntent().getStringExtra("ticket_L");
        w=getIntent().getStringExtra("ticket_W");
        f=getIntent().getStringExtra("ticket_flr");
        h=getIntent().getStringExtra("ticket_H");
        bud=getIntent().getStringExtra("ticket_bud");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new BaseFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager=getSupportFragmentManager();

        Bundle bundle=new Bundle();
        bundle.putString("ipLength",l);
        bundle.putString("ipWidth",w);
        bundle.putString("ipHeight",h);
        bundle.putString("ipFloors",f);
        bundle.putString("ipRooms",r);
        bundle.putString("ipBudget",bud);


        if (id == R.id.nav_first_layout) {
            FirstFragment firstFragment=new FirstFragment();
            firstFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_frame,firstFragment).commit();

        } else if (id == R.id.nav_second_layout) {
            SecondFragment secondFragment=new SecondFragment();
            secondFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_frame,secondFragment).commit();

        } else if (id == R.id.nav_third_layout) {
            ThirdFragment thirdFragment=new ThirdFragment();
            thirdFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_frame,thirdFragment).commit();
        } else if (id == R.id.nav_fourth_layout) {
            FourthFragment fourthFragment=new FourthFragment();
            fourthFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.content_frame,fourthFragment).commit();
        } else if (id == R.id.nav_logout) {

            Intent i=new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(i);

        }else if (id == R.id.nav_goto_ip) {

            Intent i=new Intent(HomeActivity.this,InputActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
