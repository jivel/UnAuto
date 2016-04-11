package com.jimenezlav.unauto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    // UI references.
    @Bind(R.id.text_first_name)
    AutoCompleteTextView mFirstName;
    @Bind(R.id.text_last_name)
    AutoCompleteTextView mLastName;
    @Bind(R.id.text_email)
    AutoCompleteTextView mEmailAddress;
    @Bind(R.id.text_username)
    AutoCompleteTextView mUsername;
    @Bind(R.id.text_password)
    EditText mPassword;
    @Bind(R.id.text_phone)
    EditText mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        Button buttonCreateAccount = (Button) findViewById(R.id.button_create_account);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    void attemptLogin() {
        resetErrors();
        // Store values at the time of the login attempt.
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String emailAddress = mEmailAddress.getText().toString();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        String phone = mPhone.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid first name, if the user entered one.
        if (TextUtils.isEmpty(firstName)){
            mFirstName.setError(getString(R.string.error_field_required));
            focusView = mFirstName;
            cancel = true;
        }
        // Check for a valid last name, if the user entered one.
        if (TextUtils.isEmpty(lastName)){
            mLastName.setError(getString(R.string.error_field_required));
            focusView = mLastName;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(emailAddress)) {
            mEmailAddress.setError(getString(R.string.error_field_required));
            focusView = mEmailAddress;
            cancel = true;
        } else if (!isEmailValid(emailAddress)) {
            mEmailAddress.setError(getString(R.string.error_invalid_email));
            focusView = mEmailAddress;
            cancel = true;
        }
        // Check for a valid username, if the user entered one.
        if (TextUtils.isEmpty(username)){
            mUsername.setError(getString(R.string.error_field_required));
            focusView = mUsername;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }
        // Check for a valid phone number.
        if (TextUtils.isEmpty(phone)) {
            mPhone.setError(getString(R.string.error_field_required));
            focusView = mPhone;
            cancel = true;
        } else if (!isPhoneNumberValid(phone)) {
            mPhone.setError(getString(R.string.error_invalid_phone));
            focusView = mPhone;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt sign up and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Set up a progress dialog
            final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
            dlg.setTitle("Please wait.");
            dlg.setMessage("Signing up.  Please wait.");
            dlg.show();

            // Set up a new Parse user
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(emailAddress);
            user.put("phone", phone);


            // Call the Parse signup method
            user.signUpInBackground(new SignUpCallback() {

                @Override
                public void done(ParseException e) {
                    dlg.dismiss();
                    if (e != null) {
                        // Show the error message
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        // Start an intent for the dispatch activity
                        Intent intent = new Intent(SignUpActivity.this, DispatchActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    void resetErrors() {
        // Reset errors.
        mFirstName.setError(null);
        mLastName.setError(null);
        mEmailAddress.setError(null);
        mPassword.setError(null);
        mPhone.setError(null);
    }

    boolean isPhoneNumberValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length() > 9;
    }

    boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
