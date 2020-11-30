package com.tktpl.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.tktpl.helloworld.fragment.EmailChackerFragment;
import com.tktpl.helloworld.fragment.StopwatchFragment;
import com.tktpl.helloworld.fragment.WifiListerFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_test);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WifiListerFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_wifi_lister);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle  = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_email_checker:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EmailChackerFragment()).commit();
                break;
            case R.id.nav_stopwatch:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StopwatchFragment()).commit();
                break;
            case R.id.nav_wifi_lister:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WifiListerFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}