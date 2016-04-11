package com.jimenezlav.unauto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.parse.ParseUser;

/**
 * Created by jimenezlav on 24/01/16.
 */
public class DispatchActivity extends Activity {
    private static final String LOG = DispatchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Runnable runnableNextActivity = new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        };

        Handler handlerNextActivity = new Handler();
        handlerNextActivity.postDelayed(runnableNextActivity, 1000);
    }

    private void nextActivity() {
        ParseUser parseUser = ParseUser.getCurrentUser();
        Log.i(LOG, "usuario Parse: " + parseUser);
        // Check if there is current user info
        if (parseUser != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, WelcomeActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, SignUpOrLoginActivity.class));
        }
        finish();
    }
}
