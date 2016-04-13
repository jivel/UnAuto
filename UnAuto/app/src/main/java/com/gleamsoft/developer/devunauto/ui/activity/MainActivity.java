package com.gleamsoft.developer.devunauto.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gleamsoft.developer.devunauto.R;
import com.gleamsoft.developer.devunauto.ui.controller.MiSitiosController;
import com.gleamsoft.developer.devunauto.util.DialogFactory;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.fab)
    FloatingActionButton fab_button;
    int PLACE_PICKER_REQUEST = 1;
    MiSitiosController guardarSitios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        guardarSitios = new MiSitiosController(this);
        fab_button.setOnClickListener(this);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
        } else {
            // show the signup or login screen
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

    @Override
    public void onClick(View v) {
        if (v == fab_button) {

            try {// lanzo el mapa places
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                DialogFactory.createSimpleErrorDialog(MainActivity.this).show();
            } catch (GooglePlayServicesNotAvailableException e) {
                DialogFactory.createSimpleErrorDialog(MainActivity.this).show();
            }


        }
    }

    //captura de datos seleccionados
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                guardarSitios.GuardarMiSitio(place);
            }
        }

    }

    private void User() {
        ParseUser user = new ParseUser();
        user.setUsername("esneyder");
        user.setPassword("alvarez123");
        user.setEmail("edesalla17@gmail.com");

// other fields can be set just like with ParseObject
        user.put("phone", "3124413714");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    private void login() {


    }

}
