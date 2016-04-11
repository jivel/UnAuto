package com.jimenezlav.unauto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.text_username)
    TextView mUsername;
    @Bind(R.id.text_edit_profile)
    TextView mEditProfile;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.my_location)
    FloatingActionButton floatingActionButton;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.navigation_drawer_my_sites);

        getCurrentUser();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getCurrentUser() {
        ParseUser parseUser = ParseUser.getCurrentUser();
        String username = parseUser.getUsername();
        String email = parseUser.getEmail();

        mUsername.setText(username);
        mEditProfile.setText(email);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*General*/
        if (id == R.id.nav_my_sites) {
            item.setChecked(true);
        } else if (id == R.id.nav_my_routes) {
            item.setChecked(true);
        } else if (id == R.id.nav_share) {
            item.setChecked(true);
        } else if (id == R.id.nav_notification) {
            item.setChecked(true);
        }
        /*Comunicate*/
        else if (id == R.id.nav_send) {
            item.setChecked(true);
        }
        /*support and configuration*/
        else if (id == R.id.nav_help) {
            item.setChecked(true);
        } else if (id == R.id.nav_setting) {
            item.setChecked(true);
        } else if (id == R.id.nav_sign_off) {
            signOff();
        }

        Snackbar.make(coordinatorLayout, String.format("%s pressed", item.getTitle()), Snackbar.LENGTH_LONG).show();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOff() {
        ParseUser.logOut();
        startActivity(new Intent(this, DispatchActivity.class));
        finish();
    }
}
