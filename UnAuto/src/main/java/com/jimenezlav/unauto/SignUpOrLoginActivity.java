package com.jimenezlav.unauto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpOrLoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mUsername;
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_login);
        // Set up the login form.
        mUsername = (AutoCompleteTextView) findViewById(R.id.text_username);

        mPassword = (EditText) findViewById(R.id.text_password);
        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.button_email_sign_in);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button signUpButton = (Button) findViewById(R.id.button_sign_up);
        signUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpOrLoginActivity.this, SignUpActivity.class));
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mUsername.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.error_field_required));
            focusView = mUsername;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Set up a progress dialog
//            final ProgressDialog dlg = new ProgressDialog(SignUpOrLoginActivity.this);
//            dlg.setTitle("Please wait.");
//            dlg.setMessage("Logging in.  Please wait.");
//            dlg.show();

            // Call the Parse login method
            ParseUser.logInInBackground(username, password, new LogInCallback() {

                @Override
                public void done(ParseUser user, ParseException e) {
//                    dlg.dismiss();
                    if (e != null) {
                        // Show the error message
                        Toast.makeText(SignUpOrLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        // Start an intent for the dispatch activity
                        Intent intent = new Intent(SignUpOrLoginActivity.this, DispatchActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

