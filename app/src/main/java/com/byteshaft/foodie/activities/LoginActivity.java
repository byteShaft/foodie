package com.byteshaft.foodie.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.byteshaft.foodie.R;
import com.byteshaft.foodie.utils.AppGlobals;
import com.byteshaft.foodie.utils.Helpers;

import java.io.IOException;
import java.net.HttpURLConnection;


public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView mLogin;
    private EditText mEmail;
    private EditText mPassword;
    private String getEmail;
    private String getPassword;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = (TextView) findViewById(R.id.login_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mLogin.setOnClickListener(this);
        mEmail = (EditText) findViewById(R.id.email_login);
        mPassword = (EditText) findViewById(R.id.password_login);
        getEmail = mEmail.getText().toString();
        getPassword = mPassword.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                new LoginTask().execute();
                break;
        }
    }

    class LoginTask extends AsyncTask<String, String, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
            mLogin.setClickable(false);
        }

        @Override
        protected Integer doInBackground(String... params) {
            int data = 0;
            if (Helpers.isNetworkAvailable() && Helpers.isInternetWorking()) {
                try {
                  data =   Helpers.authPostRequest(AppGlobals.BASE_URL, getEmail, getPassword);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            if (s == HttpURLConnection.HTTP_OK) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                mProgressBar.setVisibility(View.GONE);
                mLogin.setClickable(true);
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
